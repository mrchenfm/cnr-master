package com.ecut.cnr.framework.common.enums;

import lombok.Getter;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: AuditEnum
 * @Description: TODO(审核枚举类型)
 * @Author: fangming_chen
 * @Date: 2020/03/29 14:55
 */
@Getter
public enum AuditEnum {
    /**
     * 新闻信息
     */
    NEWS_NO_AUDIT(7,"未审核"),
    NEWS_AUDIT_PASS(8,"审核通过"),
    NEWS_AUDIT_FAIL_PASS(9,"被驳回"),
    /**
     * 新闻评论
     */
    NO_AUDIT_TYPE(0,"未审核"),
    AUTO_AUDIT_TYPE(1,"自动审核"),
    PSON_AUDIT_TYPE(2,"人工审核"),
    AUDIT_STATUS_AUTO_PASS(3,"自动审核通过"),
    AUDIT_STATUS_PSON_PASS(4,"人工审核通过"),
    AUDIT_STATUS_AUTO__FAIL_PASS(5,"自动审核未通过"),
    AUDIT_STATUS_PSON_FAIL_PASS(6,"人工审核未通过");


    private Integer key;

    private String value;



    AuditEnum(Integer key,String value){
        this.value = value;
        this.key = key;
    }

    public static String getValue(Integer code) {
        for (AuditEnum ele : values()) {
            if (ele.getKey().equals(code)) return ele.getValue();
        }
        return null;
    }

    /*public static void main(String[] args) {
        System.out.println(getValue(2));
    }*/

}
