package com.ecut.cnr.view.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.view.mapper.SysMenuMapper;
import com.ecut.cnr.view.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Classname SysMenuServiceImpl
 * @Description
 * @Date 2019/12/26 20:22
 * @Create by fangming_chen
 */
@Service
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public Set<String> finByPersRoleIds(List<String> roleIds) {
        return sysMenuMapper.selectByRoleIds(roleIds);
    }
}
