package com.ecut.cnr.view.mapper;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @Classname Test
 * @Description
 * @Date 2019/12/22 14:26
 * @Create by fangming_chen
 */
public class Test {

    public static void main(String[] args){
        System.out.println(new Md5Hash("123456","aaa",2).toHex());
    }
}
