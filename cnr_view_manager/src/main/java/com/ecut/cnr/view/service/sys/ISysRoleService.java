package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.sys.SysRole;
import com.ecut.cnr.framework.entity.sys.bo.RoleInfoBO;
import com.ecut.cnr.framework.entity.sys.request.QueryRequest;

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
}
