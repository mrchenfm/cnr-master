package com.ecut.cnr.view.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.SysUser;
import org.apache.ibatis.annotations.Mapper;
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
    SysUser selectByUsername(String username);
}
