/**
 * 
 */
package com.sdg.security.core.validate.code.processor.impl;

import java.util.Map;

import com.sdg.security.core.Enum.MessageEnum;
import com.sdg.security.core.exception.ValidateCodeException;
import com.sdg.security.core.validate.code.entity.Code;
import com.sdg.security.core.validate.code.generator.ValidateCodeGenerator;
import com.sdg.security.core.validate.code.processor.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author zhailiang
 *
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends Code> implements ValidateCodeProcessor {
	/**
	 * 发送校验码，由子类实现
	 *
	 * @param request
	 * @param code
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C code) throws Exception;



	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


	/**
	 * 收集系统中所有的 {@link } 接口的实现。
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

	/*
	 * Controller层调用的方法
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C code = generate(request);
		save(request, code);
		send(request, code);
	}

	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getValidateCodeType(request);
		String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
		//log.info("验证码生成器，type = {},instanse = {}",type,validateCodeGenerator);
		if (validateCodeGenerator == null) {
			throw new ValidateCodeException("验证码生成器" + generatorName + "不存在");
		}
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 * 
	 * @param request
	 * @param code
	 */
	private void save(ServletWebRequest request, C code) {
		sessionStrategy.setAttribute(request, getSessionKey(request), code);
	}

	/**
	 * 构建验证码放入session时的key
	 * 
	 * @param request
	 * @return
	 */
	private String getSessionKey(ServletWebRequest request) {
		return SESSION_KEY_PREFIX + getValidateCodeType(request).toUpperCase();
	}



	/**
	 * 根据请求的url获取校验码的类型
	 * 
	 * @param request
	 * @return
	 */
	private String getValidateCodeType(ServletWebRequest request) {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor").toLowerCase();
		return type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(ServletWebRequest request) {

		String type = getValidateCodeType(request);
		String paramName = type+"Code";
		String sessionKey = getSessionKey(request);

		C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),paramName);
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException(MessageEnum.CODE_ACCESS_FAILURE);
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException(MessageEnum.CODE_CAN_NOT_NULL);
		}

		if (codeInSession == null) {
			throw new ValidateCodeException(MessageEnum.CODE_NOT_EXIST);
		}

		if (codeInSession.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionKey);
			throw new ValidateCodeException(MessageEnum.CODE_IS_EXPIRED);
		}

		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
			throw new ValidateCodeException(MessageEnum.CODE_IS_WRANG);
		}
		sessionStrategy.removeAttribute(request, sessionKey);
	}

}
