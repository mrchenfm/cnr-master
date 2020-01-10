package com.ecut.cnr.framework.bo.sys;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

/**
 * @Classname UserInfoBO
 * @Description
 * @Date 2019/12/19 22:29
 * @Create by fangming_chen
 */
@Data
@ToString
public class UserInfoBO {

    private String id;

    private String username;

    private String password;

    private String salt;

    private String nickname;

    private Integer enabled;

    private String email;

    private String phone;

    private String userface;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private Date createTime;

    private Date updateTime;

    private List<String> roleIds;

}
