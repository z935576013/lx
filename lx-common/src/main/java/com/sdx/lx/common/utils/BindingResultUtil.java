package com.sdx.lx.common.utils;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * BindingResultUtil工具类<br>
 * 可以字符串化BindingResultUtil,用于日志输出等功能
 * 
 * @author lifei
 */
public class BindingResultUtil {
    /**
     * 字符串化BindingResultUtil: <br>
     * [{字段名,值,校验失败默认信息},{字段名,值,校验失败默认信息},{字段名,值,校验失败默认信息}] 仅显示字段错误
     * 
     * @param br
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getBindingResultAsString(BindingResult br) {
        StringBuilder toString = new StringBuilder();
        toString.append("[");
        if (br.hasFieldErrors()) {
            List<FieldError> fes = br.getFieldErrors();
            for (FieldError fe : fes) {
                toString.append("{").append(fe.getField()).append(",").append(fe.getRejectedValue()).append(",")
                        .append(fe.getDefaultMessage()).append("}");
            }
        }
        return toString.append("]").toString();
    }
    
    /**
     * 字符串化BindingResultUtil: <br>
     * [{校验失败默认信息}{校验失败默认信息}{校验失败默认信息}] 仅显示字段错误
     * 
     * @param br
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getBindingResultMessageAsString(BindingResult br) {
        StringBuilder toString = new StringBuilder();
        toString.append("[");
        if (br.hasFieldErrors()) {
            List<FieldError> fes = br.getFieldErrors();
            for (FieldError fe : fes) {
                toString.append("{") .append(fe.getDefaultMessage()).append("}");
            }
        }
        return toString.append("]").toString();
    }
    
    
    
    
}
