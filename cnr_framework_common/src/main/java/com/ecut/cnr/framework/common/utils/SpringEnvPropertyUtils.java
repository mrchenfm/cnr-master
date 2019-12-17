package com.ecut.cnr.framework.common.utils;

import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * @Classname SpringEnvPropertyUtils
 * @Description
 * @Date 2019/12/17 20:55
 * @Create by fangming_chen
 */
public class SpringEnvPropertyUtils {

    public static String getPropertyStringValue(Environment environment, String key, String defaultValue) {
        String value = environment.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            return value;
        }
        return defaultValue;
    }

    public static Long getPropertyLongValue(Environment environment, String key, Long defaultValue) {
        String value = environment.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }

    public static Integer getPropertyIntegerValue(Environment environment, String key, Integer defaultValue) {
        String value = environment.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public static Boolean getPropertyBooleanValue(Environment environment, String key, Boolean defaultValue) {
        String value = environment.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            return Boolean.valueOf(value);
        }
        return defaultValue;

    }
}
