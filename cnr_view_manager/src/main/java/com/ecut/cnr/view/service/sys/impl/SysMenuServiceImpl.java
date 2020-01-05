package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.view.mapper.sys.SysMenuMapper;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
    public Set<String> findOneByPersRoleIds(List<String> roleIds) {
        return sysMenuMapper.selectByRoleIds(roleIds);
    }

    @Override
    public List<SysMenu> findByPersRoleIds(List<String> roleIds) {
        return sysMenuMapper.selectMenuByRoleIds(roleIds);
    }

    @Override
    public boolean deleteById(String id) {
        List<String> sysMenus = sysMenuMapper.findByParentId(id);
        if(CollectionUtils.isEmpty(sysMenus)){
            int i = this.baseMapper.deleteById(id);
            if(i<1){
                return false;
            }
            return true;
        }
        sysMenus.add(id);
        int i = this.baseMapper.deleteBatchIds(sysMenus);
        if(i<1){
            return false;
        }
        return true;
    }
}
