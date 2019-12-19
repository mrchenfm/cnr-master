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
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}