package com.ecut.cnr.view.mapper;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * @Classname Test
 * @Description
 * @Date 2019/12/22 14:26
 * @Create by fangming_chen
 */
public class Test {

    public static void main(String[] args){
        System.out.println(new Sha256Hash("123456","aaa").toHex());
    }
}
