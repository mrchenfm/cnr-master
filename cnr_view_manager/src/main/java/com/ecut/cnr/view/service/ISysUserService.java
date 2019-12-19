package com.ecut.cnr.view.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;

/**
 * @Classname ISysUserServcie
 * @Description
 * @Date 2019/12/15 13:23
 * @Create by fangming_chen
 */
public interface ISysUserService extends IService<SysUser> {

    UserInfoBO selectUserByUsername(String username);

}
