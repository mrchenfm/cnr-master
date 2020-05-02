package com.ecut.cnr.framework.dto.sys;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String deptId;

    private String username;

    private String password;

    private String salt;

    private String nickname;

    private Integer enabled;

    private String email;

    private String phone;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private String userface;

    private Date createTime;

    private String roleNames;
}
