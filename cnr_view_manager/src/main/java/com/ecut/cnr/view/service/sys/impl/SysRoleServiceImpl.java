package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.view.mapper.sys.SysMenuMapper;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Classname SysRoleServiceImpl
 * @Description
 * @Date 2019/12/26 20:21
 * @Create by fangming_chen
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Set<String> findRoleNamesByIds(List<String> roleIds) {
        return sysRoleMapper.findRoleNamesByIds(roleIds);
    }
}
