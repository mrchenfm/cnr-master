package com.ecut.cnr.view.auth;

import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.SysUserToken;
import com.ecut.cnr.view.auth.service.IShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Classname OAuth2Realm
 * @Description
 * @Date 2019/12/15 20:07
 * @Create by fangming_chen
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private IShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuh2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUser user = (SysUser)principalCollection.getPrimaryPrincipal();
        String userId = user.getId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String accessToken = (String) authenticationToken.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserToken sysUserToken = shiroService.queryByToken(accessToken);

        if(sysUserToken == null){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

        //查询用户信息
        SysUser user = shiroService.queryUser(sysUserToken.getUserId());

        if(user.getEnabled() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        // 续期
        shiroService.refreshToken(sysUserToken.getUserId(),accessToken);

        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
