package com.sdx.lx.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.commons.collections4.CollectionUtils;

public class ValidatorMessageUtils {

    
    
    public static <T> GenericResult<String> getValidatorErrorMessages(Set<ConstraintViolation<T>> constraintViolations ){
        GenericResult<String> result = new GenericResult<String>();
        if(CollectionUtils.isNotEmpty(constraintViolations)){
            result.fail("has.errors","错误消息");
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage()).append(";");
            }
            result.setObject(sb.toString());
        }
        return result;
    }
}
