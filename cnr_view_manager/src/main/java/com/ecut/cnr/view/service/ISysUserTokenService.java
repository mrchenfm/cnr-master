package com.ecut.cnr.view.service;

import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.entity.sys.SysUserToken;

/**
 * @Classname ISysUserTokenService
 * @Description
 * @Date 2019/12/17 22:16
 * @Create by fangming_chen
 */
public interface ISysUserTokenService {

    /**
     * 生成Token
     * @param userId
     * @return
     */
    Result createToken(Integer userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 退出登录
     * @param userId
     */
    void logout(String userId);

    /**
     * 续期
     * @param userId
     * @param token
     */
    void refreshToken(String userId, String token);
}
