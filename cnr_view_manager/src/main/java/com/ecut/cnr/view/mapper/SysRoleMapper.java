package com.ecut.cnr.view.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname SysRoleMapper
 * @Description
 * @Date 2019/12/19 22:40
 * @Create by fangming_chen
 */
@Mapper
@Component
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户id查找roleId
     * @param userId
     * @return
     */
    List<String> selectByUserId(@Param("userId") String userId);
}
