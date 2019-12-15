package com.ecut.cnr.view.controller;

import com.ecut.cnr.framework.entity.sys.SysUser;
import com.ecut.cnr.view.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Classname LoginController
 * @Description
 * @Date 2019/12/15 10:46
 * @Create by fangming_chen
 */
@Controller
public class LoginController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/")
    //@ResponseBody
    public String toLogin(){
        return "/login";
    }

}
