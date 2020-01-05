package com.ecut.cnr.framework.bo.sys;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Classname RoleInfoBO
 * @Description
 * @Date 2020/01/01 12:59
 * @Create by fangming_chen
 */
@Data
@ToString
public class RoleInfoBO {

    private String id;

    private String roleName;

    private String roleIdentity;

    private String remark;

    private String createUserId;

    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private List<String> privileges;
}
