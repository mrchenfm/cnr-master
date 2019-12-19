package com.ecut.cnr.view.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Classname SysRoleMapper
 * @Description
 * @Date 2019/12/19 22:40
 * @Create by fangming_chen
 */
@Mapper
@Component
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
