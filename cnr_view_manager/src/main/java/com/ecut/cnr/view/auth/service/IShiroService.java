package com.ecut.cnr.view.auth.service;

import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.SysUserToken;

import java.util.Set;

/**
 * @Classname IShiroService
 * @Description service接口类
 * @Date 2019/12/15 21:50
 * @Create by fangming_chen
 */
public interface IShiroService {
    /**
     * 获取用户的所有权限
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Integer userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    SysUser queryUser(Integer userId);

    /**
     * 续期
     * @param userId
     * @param accessToken
     */
    void refreshToken(Integer userId, String accessToken);
}
