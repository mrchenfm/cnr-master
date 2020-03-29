package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.bo.sys.RoleInfoBO;
import com.ecut.cnr.framework.request.sys.QueryRequest;

import java.util.List;
import java.util.Set;

/**
 * @Classname ISysRoleService
 * @Description
 * @Date 2019/12/26 20:20
 * @Create by fangming_chen
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 通过id查询角色名称
     * @param roleIds
     * @return
     */
    Set<String> findRoleNamesByIds(List<String> roleIds);

    /**
     * 查询角色列表信息
     * @return
     */
    IPage<RoleInfoBO> findAllRoles(QueryRequest queryRequest);

    /**
     * 根据角色id删除角色
     * @param id
     * @return
     */
    Integer deleteById(String id);

    /**
     * 插入角色和角色拥有的权限
     * @param roleInfoBO
     * @return
     */
    Long insertRoleAndPerms(RoleInfoBO roleInfoBO);

    /**
     * 查询所有角色所有信息
     * @param id
     * @return
     */
    RoleInfoBO findAllById(String id);

    /**
     * 根据id修改角色信息
     * @param roleInfoBO
     * @return
     */
    Long updateRoleById(RoleInfoBO roleInfoBO);

}
