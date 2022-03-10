package com.allen.demo.dao;

import com.allen.demo.model.DemoDO;

/**
 * DAO层接口
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 */
public interface DemoDAO {

    /**
     * 新增信息
     *
     * @param demoDO 信息
     * @return 新增数量
     */
    int save(DemoDO demoDO);

    /**
     * 更新信息
     *
     * @param demoDO 信息
     * @return 更新数量
     */
    int update(DemoDO demoDO);

    /**
     * 根据主键删除信息
     *
     * @param id
     * @return
     */
    int remove(Long id);

    /**
     * 根据主键ID查询信息
     *
     * @param id 主键ID
     * @return 信息
     */
    DemoDO get(Long id);


}
