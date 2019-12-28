package com.ecut.cnr.framework.common.utils;

import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.dto.SysMenuDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname MenuUtils
 * @Description 目录分级
 * @Date 2019/12/28 10:08
 * @Create by fangming_chen
 */
public class MenuUtils {
    public static List<SysMenuDto> getMenuTree(List<SysMenu> menus){
        List<SysMenuDto> sysMenuDtos = new ArrayList<>();
        for(Iterator iterator = menus.iterator();iterator.hasNext();){
            SysMenu sysMenu= (SysMenu) iterator.next();
            if(sysMenu.getParentId().equals("0")){
                SysMenuDto sysMenuDto = new SysMenuDto();
                BeanUtils.copyProperties(sysMenu,sysMenuDto);
                sysMenuDtos.add(sysMenuDto);
            }
        }
        sysMenuDtos = getMoreChirden(sysMenuDtos,menus);
        return sysMenuDtos;
    }

    private static List<SysMenuDto> getMoreChirden(List<SysMenuDto> sysMenuDtos, List<SysMenu> menus) {
        if (!CollectionUtils.isEmpty(sysMenuDtos)) {
            for(Iterator iterator = sysMenuDtos.iterator();iterator.hasNext();){
                SysMenuDto sysMenuDto= (SysMenuDto) iterator.next();
                for(Iterator itr = menus.iterator();itr.hasNext();){
                    SysMenu sysMenu= (SysMenu) itr.next();
                    if(sysMenu.getParentId().equals(sysMenuDto.getId())){
                        SysMenuDto sysMenuDto1 = new SysMenuDto();
                        BeanUtils.copyProperties(sysMenu,sysMenuDto1);
                        sysMenuDto.getMenuDtos().add(sysMenuDto1);
                       }
                }
                List<SysMenuDto> moreChirden = getMoreChirden(sysMenuDto.getMenuDtos(), menus);
                sysMenuDto.setMenuDtos(moreChirden);
            }
        }
        return sysMenuDtos;
    }



}
