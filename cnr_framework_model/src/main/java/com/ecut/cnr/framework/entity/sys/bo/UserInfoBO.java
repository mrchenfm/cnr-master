package com.ecut.cnr.framework.entity.sys.bo;

import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysUser;
import lombok.Data;
import lombok.ToString;

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

    private Date createTime;

    private Date updateTime;

    private List<String> roleIds;

    private Set<String> menus;

}
