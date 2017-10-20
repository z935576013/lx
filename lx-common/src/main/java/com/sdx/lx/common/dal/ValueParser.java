package com.sdx.lx.common.dal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;

public class ValueParser {

	public static Map<String, Object> parser(Object entity) {
		Map<String, Object> values = new HashMap<String, Object>();

		Method[] methods = entity.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Column.class)) {
				String key = method.getName();
				Object value = null;
				try {
					value = method.invoke(entity, new Object[0]);
				} catch (Exception localException) {
					value = null;
				}
				values.put(key, value);
			}
		}

		return values;
	}

	public static Map<String, Object> parserWithoutNull(Object entity) {
		Map<String, Object> values = new HashMap<String, Object>();

		Method[] methods = entity.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Column.class)) {
				String key = method.getName();
				Object value = null;
				try {
					value = method.invoke(entity, new Object[0]);
				} catch (Exception localException) {
					value = null;
				}
				if (value != null) {
					values.put(key, value);
				}
			}
		}

		return values;
	}
}