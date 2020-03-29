package com.ecut.cnr.framework.common.enums;

import lombok.Getter;

/**
 * @Classname MenuTypeEnum
 * @Description 目录枚举
 * @Date 2019/12/19 22:51
 * @Create by fangming_chen
 */
@Getter
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0,"菜单"),
    /**
     * 菜单
     */
    MENU(1,"目录"),
    /**
     * 按钮
     */
    BUTTON(2,"按钮");

    private int key;
    private String value;

    MenuTypeEnum(int key,String value) {
        this.value = value;
        this.key = key;
    }

}