package com.ecut.cnr.view.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Classname SysMenuMapper
 * @Description
 * @Date 2019/12/17 22:15
 * @Create by fangming_chen
 */
@Component
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    Set<String> selectByRoleIds(List<String> roleIds);
}
