package com.ecut.cnr.framework.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentsMenu
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/29 19:10
 */
@Getter
public enum  CommentsMenu {

    COMMENT_NEWS(1,"文章评论"),
    COMMENT_REPLAY(1,"评论回复");
    private Integer key;

    private String value;



    CommentsMenu(Integer key,String value){
        this.value = value;
        this.key = key;
    }

    public static String getValue(Integer code) {
        for (CommentsMenu ele : values()) {
            if (ele.getKey().equals(code)) return ele.getValue();
        }
        return null;
    }
}
