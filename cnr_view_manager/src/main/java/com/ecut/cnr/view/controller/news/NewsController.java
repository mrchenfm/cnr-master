package com.ecut.cnr.view.controller.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsController
 * @Description: TODO(新闻管理控制类)
 * @Author: fangming_chen
 * @Date: 2020/02/14 13:23
 */
@Controller
@RequestMapping("/admin")
public class NewsController {

    @RequestMapping("/newsList")
    public String listNews(){
        return "news/listNews";
    }

    @RequestMapping("/addNews")
    public String addNews(){
        return "news/addNews";
    }
}
