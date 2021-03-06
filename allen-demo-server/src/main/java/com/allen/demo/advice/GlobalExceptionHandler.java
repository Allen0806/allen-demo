package com.allen.demo.advice;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.allen.tool.exception.CustomBusinessException;
import com.allen.tool.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller层异常通用处理类
 *
 * @author luoxuetong
 * @date 2021年5月19日
 * @since 1.0.0
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 处理约束异常
	 * 
	 * @param e 异常对象
	 * @return 异常转换结果
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public BaseResult<List<String>> handleConstraintViolationException(ConstraintViolationException e) {
		List<String> errorMessages = new ArrayList<String>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			errorMessages.add(violation.getMessage());
		}
		LOGGER.error("参数校验失败，失败信息：" + errorMessages, e);
		return BaseResult.paramError(errorMessages);
	}

	/**
	 * 处理参数校验异常
	 * 
	 * @param e 异常对象
	 * @return 异常转换结果
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public BaseResult<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> errorMessages = new ArrayList<String>();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errorMessages.add(fieldError.getField() + "-" + fieldError.getDefaultMessage());
		}
		LOGGER.error("参数校验失败，失败信息：" + errorMessages, e);
		return BaseResult.paramError(errorMessages);
	}

	/**
	 * 处理自定义公共业务异常
	 * 
	 * @param e 异常对象
	 * @return 异常转换结果
	 */
	@ExceptionHandler(CustomBusinessException.class)
	public BaseResult<Object> handleCustomBusinessException(CustomBusinessException e) {
		LOGGER.error("发生业务异常，异常编码：{}，异常信息：{}", e.getStatusCode(), e.getMessage(), e);
		return new BaseResult<Object>(e.getStatusCode(), e.getMessage());
	}

	/**
	 * 处理未知异常
	 * 
	 * @param e 异常对象
	 * @return 异常转换结果
	 */
	@ExceptionHandler(Exception.class)
	public BaseResult<Object> handleUnknownException(Exception e) {
		LOGGER.error("发生系统未知异常", e);
		return BaseResult.systemError((Object) null);
	}

}