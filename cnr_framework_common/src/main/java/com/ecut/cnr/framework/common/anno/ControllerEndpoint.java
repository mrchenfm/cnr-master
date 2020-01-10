package com.ecut.cnr.framework.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname ControllerEndpoint
 * @Description
 * @Date 2020/01/05 22:15
 * @Create by fangming_chen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default "";
    String exceptionMessage() default "新闻发布系统系统内部异常";
}