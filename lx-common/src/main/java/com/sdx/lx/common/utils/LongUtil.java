package com.sdx.lx.common.utils;

/**
 * Long工具类
 * 
 * @author xingjian
 *
 */
public class LongUtil {
	/**
	 * 如果为空返回0
	 * 
	 * @param value
	 * @return
	 */
	public static Long zeroIfNull(Long value) {
		return value == null ? 0L : value;
	}
}
