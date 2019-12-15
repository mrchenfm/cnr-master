package com.ecut.cnr.view.auth.service.impl;

import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.SysUserToken;
import com.ecut.cnr.view.auth.service.IShiroService;

import java.util.Set;

/**
 * @Classname ShiroServiceImpl
 * @Description
 * @Date 2019/12/15 21:58
 * @Create by fangming_chen
 */
public class ShiroServiceImpl implements IShiroService {
    @Override
    public Set<String> getUserPermissions(Integer userId) {
        return null;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return null;
    }

    @Override
    public SysUser queryUser(Integer userId) {
        return null;
    }

    @Override
    public void refreshToken(Integer userId, String accessToken) {

    }
}
