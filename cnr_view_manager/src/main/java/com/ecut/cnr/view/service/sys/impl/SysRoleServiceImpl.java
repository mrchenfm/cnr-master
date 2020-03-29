package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.common.utils.JsonUtils;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.bo.sys.RoleInfoBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.mapper.sys.SysRoleMapper;
import com.ecut.cnr.view.service.sys.ISysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
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
        Page<UserInfoBO> page = new Page<>(queryRequest.getPage(), queryRequest.getLimit());
        //this.baseMapper.findAllRoles(page);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertRoleAndPerms(RoleInfoBO roleInfoBO) {
        long count = 0L;
        System.out.println(TransactionAspectSupport.currentTransactionStatus());
        if(roleInfoBO != null){
            count = sysRoleMapper.saveRole(roleInfoBO);
            if(count>0 && CollectionUtils.isNotEmpty(roleInfoBO.getPrivileges())){
                for (String priId: roleInfoBO.getPrivileges()) {
                    sysRoleMapper.savePrivilege(roleInfoBO.getId(),priId);
                }

            }
        }
        return count;
    }

    @Override
    public RoleInfoBO findAllById(String id) {
        //查找角色基本信息
        RoleInfoBO roleInfoBO = new RoleInfoBO();
        SysRole sysRole = sysRoleMapper.selectById(id);
        BeanUtils.copyProperties(sysRole,roleInfoBO);
        //查找角色权限信息
        if(roleInfoBO != null){
           List<String> permsId = sysRoleMapper.findPermsByRoleId(roleInfoBO.getId());
           log.info("权限id{}", JsonUtils.toJson(permsId));
           if(CollectionUtils.isNotEmpty(permsId)){
               roleInfoBO.setPrivileges(permsId);
           }
        }
        return roleInfoBO;
    }

    @Override
    @Transactional
    public Long updateRoleById(RoleInfoBO roleInfoBO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleInfoBO,sysRole);
        List<String> privilegesNew = roleInfoBO.getPrivileges();

        sysRole.setUpdateTime(new Date());
        long l = this.baseMapper.updateById(sysRole);
        sysRoleMapper.deleteRoleMenuByRoleIdAndMenuId(roleInfoBO.getId());
        if(CollectionUtils.isNotEmpty(privilegesNew)){
            for (String menuId : privilegesNew) {
                sysRoleMapper.savePrivilege(roleInfoBO.getId(),menuId);
            }
        }

        return l;
    }

}
