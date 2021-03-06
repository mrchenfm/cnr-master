package com.ecut.cnr.view.auth;

import com.ecut.cnr.framework.common.utils.JsonUtils;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import com.ecut.cnr.view.service.sys.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

/**
 * @Classname UserRealm
 * @Description
 * @Date 2019/12/25 20:58
 * @Create by fangming_chen
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private ISysUserService sysUserService;

    @Autowired
    @Lazy
    private ISysRoleService sysRoleService;

    @Autowired
    @Lazy
    private ISysMenuService sysMenuService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("---------------------------->授权认证：");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        try {

            UserInfoBO userInfoBO= (UserInfoBO) principalCollection.getPrimaryPrincipal();
            //查找角色标识
            Set<String> roles = sysRoleService.findRoleNamesByIds(userInfoBO.getRoleIds());
            //查找权限标识
            Set<String> permissions = sysMenuService.findOneByPersRoleIds(userInfoBO.getRoleIds());
            // 将角色名称组成的Set提供给授权info
            log.info("用户id={},角色有={},权限有={}",userInfoBO.getId(), JsonUtils.toJson(roles),JsonUtils.toJson(permissions));
            authorizationInfo.setRoles(roles);
            // 将权限名称组成的Set提供给info
            authorizationInfo.setStringPermissions(permissions);
        } catch (Exception e){

        }
        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("---------------------------->登陆验证:");
        String userName=(String)authenticationToken.getPrincipal();

        UserInfoBO userInfoBO = sysUserService.selectUserByUsername(userName);
        if(userInfoBO==null) {
            //用户不存在就抛出异常
            log.info("用户不存在！");
            throw new UnknownAccountException();
        }
        if("1".equals( userInfoBO.getEnabled())  ) {
            //用户被锁定就抛异常
            log.info("账号被禁用");
            throw new LockedAccountException();
        }
        //密码可以通过SimpleHash加密，然后保存进数据库。
        //此处是获取数据库内的账号、密码、盐值，保存到登陆信息info中
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userInfoBO,
                userInfoBO.getPassword(),
                ByteSource.Util.bytes(userInfoBO.getSalt()),
                getName());                   //realm name

        return authenticationInfo;
    }
}
