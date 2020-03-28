package com.ecut.cnr.customer.view.controller;

import com.ecut.cnr.customer.view.service.INewsTypeService;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private INewsTypeService newsTypeService;

    /**
     * 跳到首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"","/index"})
    public String index(Model model){

        List<NewsType> newsTypes = newsTypeService.getAll();
        model.addAttribute("newsTypes",newsTypes);
        return "index";
    }
}
