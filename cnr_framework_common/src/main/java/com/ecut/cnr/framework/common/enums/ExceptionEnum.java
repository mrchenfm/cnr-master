package com.ecut.cnr.framework.common.enums;

/**
 * @version v1.0
 * @ProjectName: eos_master
 * @ClassName: ExceptionEnum
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/29 21:12
 */
public enum ExceptionEnum {
    SUCCESS(200),
    RESOURCE_NOT_FOUND(404),
    ARGUMENTS_INVALID(401),
    BUSINESS_ERROR(400),
    SERVER_ERROR(500);

    private ExceptionEnum(int code) {
        this.code = code;
    }

    private ExceptionEnum() {
    }

    // 成员变量
    private int code;

    public int getCode() {
        return this.code;
    }
}

