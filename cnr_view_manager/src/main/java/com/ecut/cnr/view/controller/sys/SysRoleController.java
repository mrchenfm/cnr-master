package com.ecut.cnr.view.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname SysRoleController
 * @Description
 * @Date 2019/12/28 18:49
 * @Create by fangming_chen
 */
@Controller
@RequestMapping("/admin")
public class SysRoleController {

    @RequestMapping("/rolelist")
    public String userList(){
        return "sys/rolelist";
    }
}
