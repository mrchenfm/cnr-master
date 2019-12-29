package com.ecut.cnr.view.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Classname SysRoleMapper
 * @Description
 * @Date 2019/12/28 21:34
 * @Create by fangming_chen
 */
@Mapper
@Component
public   interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查找roleId
     * @param userId
     * @return
     */
    List<String> selectByUserId(@Param("userId") String userId);

    /**
     * 查询就角色名称
     * @param roleIds
     * @return
     */
    Set<String> findRoleNamesByIds(@Param("roleIds") List<String> roleIds);

    /**
     * 根据用户id查询所有角色
     * @param id
     * @return
     */
    List<String> findBuUserId(@Param("id") String id);

    /**
     * 添加用户角色
     * @param userId
     * @param roleId
     */
    void insertUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 根据id删除用户拥有的角色
     * @param id
     */
    void deleteUserRole(@Param("id") String id);
}
