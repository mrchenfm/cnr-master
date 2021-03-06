package com.ecut.cnr.framework.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname SysUser
 * @Description
 * @Date 2019/12/15 13:20
 * @Create by fangming_chen
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "系统用户对象",description = "用户")
@TableName("t_sys_user")
public class SysUser implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    private String deptId;
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "别名")
    private String nickname;

    @ApiModelProperty(value = "是否激活")
    private Integer enabled;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户头像地址")
    private String userface;

    @ApiModelProperty(value = "最后登入时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public SysUser(String id, int enabled) {
        this.id =id;
        this.enabled = enabled;
    }
}
