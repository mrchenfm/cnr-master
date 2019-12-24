package com.ecut.cnr.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.view.mapper.SysRoleMapper;
import com.ecut.cnr.view.mapper.SysUserMapper;
import com.ecut.cnr.view.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname SysUserServicempl
 * @Description
 * @Date 2019/12/15 13:23
 * @Create by fangming_chen
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserInfoBO selectUserByUsername(String username){
        SysUser sysUser = sysUserMapper.selectByUsername(username);
        List<String> roleIds = sysRoleMapper.selectByUserId(sysUser.getId());
        UserInfoBO userInfoBO = new UserInfoBO();
        BeanUtils.copyProperties(sysUser,userInfoBO);
        userInfoBO.setRoleIds(roleIds);
        return userInfoBO;
    }

}
