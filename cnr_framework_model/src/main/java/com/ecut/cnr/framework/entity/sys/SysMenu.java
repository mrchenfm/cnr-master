package com.ecut.cnr.framework.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname SysMenu
 * @Description
 * @Date 2019/12/15 22:00
 * @Create by fangming_chen
 */
@Data
@ApiModel(value="SysMenu对象", description="菜单管理")
@TableName(value = "t_sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "父菜单ID，一级菜单为0")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单URL")
    private String url;

    @ApiModelProperty(value = "授权(多个用逗号分隔，如：sys:list,sys:create)")
    @TableField(strategy= FieldStrategy.IGNORED)
    private String perms;

    @ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
    private Integer type;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer orderNum;

    /**
     * z-tree属性
     */
    private Integer visable;

    @ApiModelProperty(value = "删除标志")
    private Integer delFlag;

}
