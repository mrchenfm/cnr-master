package com.ecut.cnr.view.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.MenuUtils;
import com.ecut.cnr.framework.entity.sys.SysMenu;
import com.ecut.cnr.framework.dto.sys.SysMenuDto;
import com.ecut.cnr.view.service.sys.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IdUtils idUtils;

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
    @GetMapping("/menuAdd")
    public String toMenuAdd(){
        return "sys/menu/menuAdd";
    }

    @GetMapping("/menuAddChildren")
    public String toMenuAddChildren(Model model,String id){
        SysMenu menu = this.sysMenuService.getById(id);
        model.addAttribute("sysMenu",menu);
        return "sys/menu/menuAddChildren";
    }

    @GetMapping("/menuUpdate")
    public String toMenuUpdate(Model model,String id){
        SysMenu menu = this.sysMenuService.getById(id);
        model.addAttribute("sysMenu",menu);
        model.addAttribute("icon", JSONArray.toJSON(menu.getIcon()));
        return "sys/menu/menuUpdate";
    }

    @PostMapping("/save/menu")
    @ResponseBody
    public Result saveMenu(@RequestBody SysMenu sysMenu){
        sysMenu.setId(String.valueOf(idUtils.nextId()));
        boolean b = this.sysMenuService.saveOrUpdate(sysMenu);
        if(!b){
            return Result.error("添加失败");
        }
        return new Result();
    }

    @PostMapping("/update/menu")
    @ResponseBody
    public Result updateMenu(@RequestBody SysMenu sysMenu){
        boolean b = this.sysMenuService.saveOrUpdate(sysMenu);
        if(!b){
            return Result.error("修改失败");
        }
        return new Result();
    }

    @PostMapping("/delete/menu")
    @ResponseBody
    public Result deleteMenu(String id){
        boolean b = this.sysMenuService.deleteById(id);
        if(!b){
            return Result.error("删除失败");
        }
        return new Result();
    }



}
