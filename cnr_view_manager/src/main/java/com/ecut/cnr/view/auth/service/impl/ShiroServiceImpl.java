package com.ecut.cnr.view.auth.service.impl;

import com.ecut.cnr.framework.common.constants.SysConstants;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.SysUserToken;
import com.ecut.cnr.view.auth.service.IShiroService;
import com.ecut.cnr.view.mapper.SysMenuMapper;
import com.ecut.cnr.view.mapper.SysUserMapper;
import com.ecut.cnr.view.service.ISysUserTokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Classname ShiroServiceImpl
 * @Description
 * @Date 2019/12/15 21:58
 * @Create by fangming_chen
 */
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private ISysUserTokenService sysUserTokenService;
    @Override
    public Set<String> getUserPermissions(String userId) {

        List<String> permissions;

        if(SysConstants.SUPER_ADMIN.equals(userId)){
            List<SysMenu> sysMenus = sysMenuMapper.selectList(null);
            permissions = new ArrayList<>(sysMenus.size());
            sysMenus.forEach(sysMenu -> permissions.add(sysMenu.getUrl()));
        }else {
            permissions = sysUserMapper.queryAllPerms(userId);
        }
        //返回用户权限列表
        return permissions.stream()
                // 过滤空置的字符串
                .filter(perms -> !StringUtils.isEmpty(perms))
                // 把小的list合并成大的list
                .flatMap(perms -> Arrays.stream(perms.split(",")))
                // 转换成set集合
                .collect(Collectors.toSet());
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return null;
    }

    @Override
    public SysUser queryUser(String userId) {
        return null;
    }

    @Override
    public void refreshToken(String userId, String accessToken) {

    }
}
