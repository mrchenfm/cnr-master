package com.ecut.cnr.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname LoginController
 * @Description
 * @Date 2019/12/15 10:46
 * @Create by fangming_chen
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    //@ResponseBody
    public String toLogin(){
        return "/login";
    }

}
