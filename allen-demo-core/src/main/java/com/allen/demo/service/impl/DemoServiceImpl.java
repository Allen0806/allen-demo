package com.allen.demo.service.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.allen.demo.constant.ResultStatuses;
import com.allen.demo.constant.BaseConstant;
import com.allen.demo.model.DemoDTO;
import com.lczq.tool.exception.CustomBusinessException;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.allen.demo.dao.DemoDAO;
import com.allen.demo.model.DemoDO;
import com.allen.demo.service.DemoService;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 服务层接口实现类
 *
 * @author luoxuetong
 * @date 2021年5月20日
 * @since 1.0.0
 */
@Service
public class DemoServiceImpl implements DemoService {

    /**
     * 日志纪录器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    /**
     * 线程池(Spring 线程池)
     */
    @Autowired
    @Qualifier("commonExecutor")
    private ThreadPoolTaskExecutor commonExecutor;

    /**
     * RocketMQ生产者实例
     */
    @Autowired
    private DemoDAO demoDAO;

    /**
     * Redisson客户端实例
     */
    @Autowired
    private RedissonClient redissonClient;

    @Transactional
    @Override
    public void save(@NotNull(message = "信息对象不能为空") @Valid DemoDTO demoDTO) {
        DemoDO demoDO = toDemoDO(demoDTO);
        demoDAO.save(demoDO);
        LOGGER.info("保存成功");
    }

    @Transactional
    @Override
    public void update(@NotNull(message = "信息对象不能为空") @Valid DemoDTO demoDTO) {
        DemoDO demoDO = toDemoDO(demoDTO);
        demoDAO.update(demoDO);
        LOGGER.info("修改成功");
    }

    @Transactional
    @Override
    public void remove(@NotNull(message = "主键不能为空") @Valid Long id) {
        evictCache(id);
        demoDAO.remove(id);
        LOGGER.info("删除成功");
    }

    @Override
    public DemoDTO get(@NotNull(message = "主键不能为空") @Valid Long id) {
        // 优先从缓存里获取
        DemoDTO demoDTO = getFromCache(id);
        if (demoDTO != null) {
            return demoDTO;
        }
        String lockKey = BaseConstant.CACHE_NAME_PREFIX + "::" + id;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(3, 3, TimeUnit.SECONDS)) {
                try {
                    // 再次从缓存里获取，二次检查
                    demoDTO = getFromCache(id);
                    if (demoDTO != null) {
                        return demoDTO;
                    }

                    DemoDO demoDO = demoDAO.get(id);
                    if (demoDO == null) {
                        return null;
                    }
                    demoDTO = toDemoDTO(demoDO);
                    cacheable(demoDTO);
                    return demoDTO;
                } catch (Exception e) {
                    LOGGER.error("获取信息异常，主键ID：{}", e);
                    throw new CustomBusinessException(ResultStatuses.LD_0103, e);
                } finally {
                    lock.unlock();
                }
            }
        } catch (Exception e) {
            LOGGER.error("锁处理异常，消息ID：" + id, e);
            throw new CustomBusinessException(ResultStatuses.LD_0103, e);
        }
        return null;
    }

    /**
     * DTO转DO
     *
     * @param demoDTO
     * @return
     */
    private DemoDO toDemoDO(DemoDTO demoDTO) {
        DemoDO demoDO = new DemoDO();
        demoDO.setId(demoDTO.getId());
        demoDO.setName(demoDTO.getName());
        return demoDO;
    }

    /**
     * DO转DTO
     *
     * @param demoDO
     * @return
     */
    private DemoDTO toDemoDTO(DemoDO demoDO) {
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setId(demoDO.getId());
        demoDTO.setName(demoDO.getName());
        return demoDTO;
    }

    /**
     * 清除缓存
     *
     * @param id 主键ID
     */
    private void evictCache(Long id) {
        String cacheKey = BaseConstant.CACHE_NAME_PREFIX + "::" + id;
        RBucket<DemoDTO> bucket = redissonClient.getBucket(cacheKey);
        bucket.getAndDelete();
    }

    /**
     * 从缓存中获取信息
     *
     * @param id 主键ID
     * @return
     */
    private DemoDTO getFromCache(Long id) {
        String cacheKey = BaseConstant.CACHE_NAME_PREFIX + "::" + id;
        RBucket<DemoDTO> bucket = redissonClient.getBucket(cacheKey);
        return bucket.get();
    }

    /**
     * 将信息设置到缓存中
     *
     * @param demoDTO
     */
    private void cacheable(DemoDTO demoDTO) {
        String cacheKey = BaseConstant.CACHE_NAME_PREFIX + "::" + demoDTO.getId();
        RBucket<DemoDTO> bucket = redissonClient.getBucket(cacheKey);
        bucket.set(demoDTO);
    }

}
