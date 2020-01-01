package com.ecut.cnr.framework.entity.sys.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Classname SysUserDto
 * @Description
 * @Date 2019/12/28 22:08
 * @Create by fangming_chen
 */
@Data
@ToString
public class SysUserDto {
    private String id;

    private String username;

    private String password;

    private String salt;

    private String nickname;

    private Integer enabled;

    private String email;

    private String phone;

    private String userface;

    private Date createTime;

    private String roleNames;
}
