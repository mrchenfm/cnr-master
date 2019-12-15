package com.ecut.cnr.view.auth;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @Classname TokeGenerator
 * @Description 生成token
 * @Date 2019/12/15 20:08
 * @Create by fangming_chen
 */
@Slf4j
public class TokeGenerator {

    private final static String CODE_VALUE = "0123456789abcdef";

    public static String generateValue(){
        return generateValue(UUID.randomUUID().toString());
    }
    private static final char[] hexCode = CODE_VALUE.toCharArray();

    private static String toHexString(byte[] data){
        if(data == null){
            return null;
        }
        StringBuilder sb = new StringBuilder(data.length*2);
        for(byte b : data){
            sb.append(hexCode[(b>>4) & 0xF]);
            sb.append(hexCode[b & 0xF]);
        }

        return sb.toString();
    }

    private static String generateValue(String param) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(param.getBytes());
            byte[] digest = messageDigest.digest();

            return toHexString(digest);

        } catch (NoSuchAlgorithmException e) {
            log.error("Token信息生成报错:{}",e);
        }
        return null;
    }

}
