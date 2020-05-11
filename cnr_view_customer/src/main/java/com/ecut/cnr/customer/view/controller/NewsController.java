package com.ecut.cnr.customer.view.controller;

import com.ecut.cnr.customer.view.service.INewsService;
import com.ecut.cnr.customer.view.service.INewsTypeService;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsSeeController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/04 14:26
 */
@Controller
@RequestMapping("/new")
public class NewsController {

    @Autowired
    private INewsTypeService newsTypeService;

    @Autowired
    private INewsService newsService;

    @RequestMapping("/detail")
    public String toDetail(Model model,String id){
        List<NewsType> newsTypes = newsTypeService.getAll();
        model.addAttribute("newsTypes",newsTypes);
        return "details";
    }


}
