package com.ecut.cnr.framework.common.base;

import com.ecut.cnr.framework.entity.sys.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @Classname BaseController
 * @Description
 * @Date 2019/12/20 22:49
 * @Create by fangming_chen
 */
public class BaseController {

    protected SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected String getUserId(){
        return getUser().getId();
    }
}
