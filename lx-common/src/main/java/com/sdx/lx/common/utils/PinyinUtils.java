package com.sdx.lx.common.utils;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 拼音工具
 * 
 * @author zhuliang
 */
public class PinyinUtils {

	public static String getFirstLetter(String source) {
		String result = "";
		if (StringUtils.isNotEmpty(source)) {
			String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(source.charAt(0));
			if (pinyin == null) {
				result = String.valueOf(source.charAt(0));
			} else {
				result = String.valueOf(pinyin[0].charAt(0));
			}
		}
		return result.toUpperCase();
	}
}
