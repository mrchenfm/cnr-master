package com.ecut.cnr.framework.entity.sys.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

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

    private String createTime;

    private String roleNames;
}
