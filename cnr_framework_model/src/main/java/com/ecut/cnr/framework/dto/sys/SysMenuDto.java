package com.ecut.cnr.framework.dto.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SysMenuDto
 * @Description
 * @Date 2019/12/28 9:53
 * @Create by fangming_chen
 */
@Data
public class SysMenuDto implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    private String menuName;

    private String url;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    /**
     * z-tree属性
     */
    private Integer visable;

    private Integer delFlag;

    private List<SysMenuDto> menuDtos = new ArrayList<>();
}
