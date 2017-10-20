package com.sdx.lx.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;

public class EvaluatorUtil {

	
	/**
	 * 表达式运算
	 * @param exp
	 * @param env
	 * @return
	 */
	public static Object execute(String exp, Map<String , Object> env) {

		Object obj = AviatorEvaluator.execute(exp, env, true);

		return obj;

	}
	
	/**
	 * 计算表达式中只包含一个x变量的数学运算,小数点后四舍五入4位有效数字
	 * @param exp
	 * @param value
	 * @return
	 */
	public static BigDecimal executeX(String exp ,BigDecimal value) 
	{
		
		Map<String,Object> env = new HashMap<String, Object>();
		
		env.put("x", value) ;
		
		Object obj = EvaluatorUtil.execute(exp, env);
		
		BigDecimal convertValue = new BigDecimal(String.valueOf(obj)) ;
		
		// TODO 每次装载动态类 吃内存、耗cpu 临时用简单乘法代替
		// 0.6213712*x
//		String m = exp.substring(0, exp.indexOf("*"));
//		BigDecimal convertValue = value.multiply(new BigDecimal(m));
		
		convertValue = convertValue.setScale(4, BigDecimal.ROUND_HALF_UP);

		return convertValue;
	}

}
