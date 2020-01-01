package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.sys.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @Classname ISysMenuService
 * @Description
 * @Date 2019/12/26 20:20
 * @Create by fangming_chen
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据roleId查找权限
     * @param roleIds
     * @return
     */
    Set<String> findOneByPersRoleIds(List<String> roleIds);

    List<SysMenu> findByPersRoleIds(List<String> roleIds);
}