package com.ecut.cnr.view.service;

import java.util.List;
import java.util.Set;

/**
 * @Classname ISysRoleService
 * @Description
 * @Date 2019/12/26 20:20
 * @Create by fangming_chen
 */
public interface ISysRoleService {

    /**
     * 通过id查询角色名称
     * @param roleIds
     * @return
     */
    Set<String> findRoleNamesByIds(List<String> roleIds);
}
