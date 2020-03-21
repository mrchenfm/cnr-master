package com.ecut.cnr.view.controller.news;

import com.alibaba.druid.support.json.JSONUtils;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.news.NewsTitle;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.view.service.news.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsController
 * @Description: TODO(新闻管理控制类)
 * @Author: fangming_chen
 * @Date: 2020/02/14 13:23
 */
@Controller
@RequestMapping("/news")
@Slf4j
public class NewsController {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private INewsService newsService;

    @RequestMapping("/newsList")
    public String listNews(){
        return "news/listNews";
    }

    @RequestMapping("/add/new")
    @ResponseBody
    public Result addNews(@RequestBody NewsBO newsBO){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        newsBO.setUserId(userInfoBO.getId());
        newsBO.setNewsPic(CnrContants.BASE_URL_UPLOAD+newsBO.getNewsPic());
        log.info("【新闻添加入参】：{}",true);
        newsBO.setTitleId(String.valueOf(idUtils.nextId()));
        int i = newsService.addNews(newsBO);
        if(i<1){
            return Result.error();
        }
        return new Result();
    }

    @RequestMapping("/addNews")
    public String toAddNews(Model model){
        List<NewsType> typeList = newsService.listAllTypes();
        //log.info("【新闻类别】：{}", JSONUtils.toJSONString(typeList));
        model.addAttribute("types",typeList);
        return "news/addNews";
    }

    @RequestMapping("/add/new")
    @ResponseBody
    public Result deleteTypeById(String id){
        return new Result();
    }

    @RequestMapping("/updateType")
    @ResponseBody
    public Result updatTypeById(String id){
        return new Result();
    }

    @RequestMapping("/list/type")
    @ResponseBody
    public Result listAllType(String id){
        return new Result();
    }



}
