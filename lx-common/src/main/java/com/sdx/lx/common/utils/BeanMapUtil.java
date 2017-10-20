package com.sdx.lx.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanMapUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BeanMapUtil.class);
	
	
	/**
	 * bean 转map
	 * @param obj
	 * @param isIncludeNull  是否包含null到map中 ， true包含null值，false不包含null
	 * @return
	 */
    public static Map<String, Object> transBean2Map(Object obj , boolean isIncludeNull) {  
  
        if(obj == null){  
            return null;  
        }          
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
            for (PropertyDescriptor property : propertyDescriptors) {  
                String key = property.getName();  
  
                // 过滤class属性  
                if (!key.equals("class")) {  
                    // 得到property对应的getter方法  
                    Method getter = property.getReadMethod();  
                    Object value = getter.invoke(obj);  
  
                    if(value != null || isIncludeNull)
                    {
                    	map.put(key, value);  
                    }
                    
                }  
  
            }  
        } catch (Exception e) {  
        	logger.error("transBean2Map Error " + e);  
        }  
  
        return map;  
  
    }  
    
    public static Map<String, Object> transBean2MapWithoutNull(Object obj ) { 
    	
    	return transBean2Map(obj, false) ;
    }

}
