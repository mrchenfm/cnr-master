package com.ecut.cnr.framework.entity.sys.bo;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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

    private Long createUserId;

    private String username;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
