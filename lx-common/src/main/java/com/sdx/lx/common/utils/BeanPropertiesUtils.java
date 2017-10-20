package com.sdx.lx.common.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;


/**
 * java bean 转换类
 *
 * @author lifei 
 */
public class BeanPropertiesUtils {

    //装换器
    private final static Mapper mapper = new DozerBeanMapper();
    
    /**
     * 类拷贝属性
     * @param <T>
     * @param sourceObject 源对象
     * @param clazz 目标类型
     * @return
     */
    public static <T> T copyProperties(Object sourceObject,Class<T> clazz){
        return  mapper.map(sourceObject,clazz);
    }
    
    /**
     * 实例拷贝属性
     * @param sourceObject 源对象
     * @param destObject 目标对象
     */
    public static void copyProperties(Object sourceObject,Object destObject){
        mapper.map(sourceObject, destObject);
    }
    
}
