package com.sdx.lx.web.admin.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.sdx.lx.common.constants.ErrorMessage;

public class ResultBean {

	/**
	 * 成功标示 T成功
	 */
	public static String SUCCESS = "T";
	/**
	 * 成功标示F失败
	 */
	public static String FAILURE = "F";
	/**
	 * 成功标示 T成功；F失败
	 */
	private static String IS_SUCCESS = "isSuccess";

	/**
	 * 错误编码
	 */
	private static String ERROR_CODE = "errorCode";
	/**
	 * 错误描述
	 */
	private static String ERROR_MSG = "errorMsg";

	// /**
	// * 日期格式
	// */
	// private static String datePattern = "yyyy-MM-dd HH:mm:ss";

	private static SerializerFeature[] features = {
			SerializerFeature.WriteNullNumberAsZero,
			SerializerFeature.WriteNullBooleanAsFalse,
			SerializerFeature.WriteNullStringAsEmpty };

	private static ValueFilter filter = new ValueFilter() {

		@Override
		public Object process(Object object, String name, Object value) {
			try {
				if (value == null) {
					Field filed = object.getClass().getDeclaredField(name);
					if (filed != null) {
						Class<?> clazz = filed.getType();
						if (Number.class.isAssignableFrom(clazz)) {
							return 0;
						} else {
							return "";
						}
					}

				}
			} catch (Exception e) {
				return value;
			}
			return value;
		}
	};
	/**
	 * 返回报文体
	 */
	private Map<String, Object> body;

	/**
	 * 
	 */
	public ResultBean() {
		body = new HashMap<String, Object>();
		body.put(IS_SUCCESS, SUCCESS);
	}

	/**
	 * 
	 */
	public ResultBean(Map<String, Object> body) {
		if (body == null) {
			this.body = new HashMap<String, Object>();
		} else {
			this.body = body;
		}
		this.body.put(IS_SUCCESS, SUCCESS);
	}

	/**
	 * 
	 */
	public ResultBean(ErrorMessage errorMessage) {
		body = new HashMap<String, Object>();
		body.put(IS_SUCCESS, FAILURE);
		body.put(ERROR_CODE, errorMessage.getErrorCode());
		body.put(ERROR_MSG, errorMessage.getErrorMessage());
	}
	
	/**
	 * 
	 */
	public ResultBean(ErrorMessage errorMessage, String param) {
		body = new HashMap<String, Object>();
		body.put(IS_SUCCESS, FAILURE);
		body.put(ERROR_CODE, errorMessage.getErrorCode());
		body.put(ERROR_MSG, replaceParam(errorMessage.getErrorMessage(), param));
	}

	/**
	 * 
	 * @param message
	 * @param param
	 * @return
	 */
	public String replaceParam(String message, String param) {
		return message.replace("?", param);
	}
	
	/**
	 * 
	 */
	public ResultBean(String errorCode, String errorMsg) {
		body = new HashMap<String, Object>();
		body.put(IS_SUCCESS, FAILURE);
		body.put(ERROR_CODE, errorCode);
		body.put(ERROR_MSG, errorMsg);
	}

	/**
	 * 
	 */
	public ResultBean addAttribute(String key, Object value) {
		body.put(key, value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return JSON.toJSONString(body, filter, features);
	}
}
