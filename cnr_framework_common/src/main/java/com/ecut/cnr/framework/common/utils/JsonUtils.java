package com.ecut.cnr.framework.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Classname JsonUtils
 * @Description json转化工具
 * @Date 2019/12/14 11:39
 * @Create by fangming_chen
 */
@Slf4j
public class JsonUtils {
    private final static ObjectMapper objMapper = new ObjectMapper();

    /**
     * Json字符串转化成对象
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T toObj(String jsonString, Class<T> clazz) {
        objMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return objMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error("Json字符串转化成对象出错",e);
        }
        return null;
    }

    /**
     * javaBean 转化成json字符串
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJson(Object obj) {
        if(obj instanceof Integer || obj instanceof Long || obj instanceof Float ||
                obj instanceof Double || obj instanceof Boolean || obj instanceof String){
            return String.valueOf(obj);
        }
        try {
            return objMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("转化成json字符串",e);
        }
        return null;
    }
}
