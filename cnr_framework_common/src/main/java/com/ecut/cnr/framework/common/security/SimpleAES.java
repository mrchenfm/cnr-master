package com.ecut.cnr.framework.common.security;

import com.ecut.cnr.framework.common.utils.SecurityUtil;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/1 11:08
 * @Description:
 */
public class SimpleAES {

    private static final String Algorithm = "AES";


    /**
     * 加密
     * @param plainText 明文
     * @return
     */
    @SneakyThrows
    public static String encrypt(String plainText,String password) {
        return SecurityUtil.toHex(encrypt(plainText.getBytes("UTF-8"),password));
    }

    @SneakyThrows
    public static String decrypt(String cipherText,String password){
        byte[] bytes = decrypt(SecurityUtil.hexTobytes(cipherText),password);
        return new String(bytes,"UTF-8");
    }


    /**
     * 加密
     * @param key 密钥
     * @param data 原文
     * @return 密文
     * @throws Exception
     */
    public static byte[] encode(byte[] key, byte[] data) throws Exception {
        // 参数验证
        if (key == null) {
            throw new NullPointerException("The key for encrypt can't be null");
        }
        if (data == null) {
            throw new NullPointerException("The data encrypted can't be null");
        }
        // 加密
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param key 密钥
     * @param data 密文
     * @return 原文
     * @throws Exception
     */
    public static byte[] decode(byte[] key, byte[] data) throws Exception {
        // 参数验证
        if (key == null) {
            throw new NullPointerException("The key for decrypt can't be null");
        }
        if (data == null) {
            throw new NullPointerException("The data decrypted can't be null");
        }
        // 解密
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }


    @SneakyThrows
    public static byte[] encrypt(byte[] byteS,String pwd) {
        Cipher cipher = Cipher.getInstance(Algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(byteS);
    }
    @SneakyThrows
    public static byte[] decrypt(byte[] byteD,String pwd) {
        Cipher cipher= Cipher.getInstance(Algorithm);
        SecretKeySpec keySpec = new SecretKeySpec(getKey(pwd), "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return  cipher.doFinal(byteD);
    }

    private static byte[] getKey(String password) throws UnsupportedEncodingException {
        // 使用256位密码
        if(password.length() > 16){
            password = password.substring(0, 16);
        }
        else if(password.length() < 16){
            int count = (16 - password.length());
            for(int i=0;i<count;i++){
                password+="0";
            }
        }

        return password.getBytes("UTF-8");
    }

    public static void main(String[] args){
        String aaaa = encrypt("123456", "aaaa");
        System.out.println(aaaa);
        System.out.println(decrypt("8515eef579d888ecd45129cfff5ffcd7","aaaa"));
    }

}
