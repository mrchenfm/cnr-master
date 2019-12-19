package com.ecut.cnr.framework.entity.sys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname SysUserToken
 * @Description
 * @Date 2019/12/15 21:53
 * @Create by fangming_chen
 */
@Data
@ApiModel(value="SysUserToken对象", description="系统用户Token")
public class SysUserToken implements Serializable{

    private static final long serialVersionUID = 1L;

    private String userId;

    private String token;
}
