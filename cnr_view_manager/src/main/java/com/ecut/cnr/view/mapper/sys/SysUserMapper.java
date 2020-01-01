package com.ecut.cnr.view.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.framework.entity.sys.bo.UserInfoBO;
import com.ecut.cnr.framework.entity.sys.dto.SysUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname SysUserMapper
 * @Description
 * @Date 2019/12/15 13:19
 * @Create by fangming_chen
 */
@Mapper
@Component
public interface SysUserMapper extends BaseMapper<SysUser>{


    /**
     * 查询用户
     * @param username
     * @return
     */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * 通过id修改账号状态
     * @param sysUser
     * @return
     */
    Integer updateStatusById(@Param("sysUser") SysUser sysUser);

    /**
     * 分页查询
     * @param page
     * @return
     */
    IPage<SysUserDto> findAllUsers(Page<UserInfoBO> page);
}
