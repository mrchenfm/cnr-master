package com.ecut.cnr.view.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname SysMenuController
 * @Description
 * @Date 2019/12/28 18:50
 * @Create by fangming_chen
 */
@Controller
@RequestMapping("/admin")
public class SysMenuController {

    @RequestMapping("/menulist")
    public String menuList(){
        return "sys/menulist";
    }
}
