package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.entity.sys.bo.RoleInfoBO;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.request.QueryRequest;
import com.ecut.cnr.view.mapper.sys.SysMenuMapper;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public IPage<RoleInfoBO> findAllRoles(QueryRequest queryRequest) {
        Page<UserInfoBO> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
        this.baseMapper.findAllRoles(page);
        return this.baseMapper.findAllRoles(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(String id) {
        SysRole role = sysRoleMapper.selectById(id);
        int count = 0;
        if(role !=null){
            sysRoleMapper.deleteUserRoleByRoleId(id);
            sysRoleMapper.deleteRoleMenuByRoleId(id);
           count = sysRoleMapper.deleteById(id);
        }
        return count;
    }
}
