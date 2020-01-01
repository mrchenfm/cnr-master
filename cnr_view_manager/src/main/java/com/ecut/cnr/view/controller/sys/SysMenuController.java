package com.ecut.cnr.view.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.utils.JsonUtils;
import com.ecut.cnr.framework.common.utils.MenuUtils;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.entity.sys.dto.SysMenuDto;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Classname SysMenuController
 * @Description
 * @Date 2019/12/28 18:50
 * @Create by fangming_chen
 */
@Controller
@RequestMapping("/admin")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;
    @RequestMapping("/menuList")
    public String menuList(){
        return "sys/menu/menuList";
    }

    @RequestMapping("/menu/menuTree")
    @ResponseBody
    public Result menuTree(){
        List<SysMenu> list = sysMenuService.list(null);
        /*List<SysMenuDto> menuTree = MenuUtils.getMenuTree(list);*/
        Object toJSON = com.alibaba.fastjson.JSONArray.toJSON(list);
        return new Result().put("data",toJSON);
    }

    @RequestMapping("/menu/tree")
    @ResponseBody
    public Result finMenu(){
        List<SysMenu> list = sysMenuService.list(null);
        List<SysMenuDto> menuTree = MenuUtils.getMenuTree(list);
        Object toJSON = com.alibaba.fastjson.JSONArray.toJSON(menuTree);
        return new Result().put("data",toJSON);
    }
}
