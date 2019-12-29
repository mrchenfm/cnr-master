package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.dto.SysUserDto;

import java.util.List;

/**
 * @Classname ISysUserServcie
 * @Description
 * @Date 2019/12/15 13:23
 * @Create by fangming_chen
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 通过用户名查询管理员信息
     * @param username
     * @return
     */
    UserInfoBO selectUserByUsername(String username);

    /**
     * 查询所有管理员
     * @return
     */
    List<SysUserDto> selectAll();

    Long insertUsersAndRole(SysUser sysUser, List<String> roleIds);

    /**
     * 根据id删除管理员及他的权限
     * @param id
     * @return
     */
    Integer deleteById(String id);

    /**
     * 根据用户id查询管理员信息
     * @param id
     * @return
     */
    UserInfoBO findByUserId(String id);

    /**
     * 通过id修改管理员信息
     * @param sysUser
     * @return
     */
    Integer updateManagerById(SysUser sysUser);

    /**
     * 修改账号状态
     * @param sysUser
     * @return
     */
    Integer updateStatusBuId(SysUser sysUser);
}

