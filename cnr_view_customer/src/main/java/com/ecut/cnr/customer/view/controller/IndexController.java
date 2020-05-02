package com.ecut.cnr.customer.view.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.customer.view.service.INewsService;
import com.ecut.cnr.customer.view.service.INewsTypeService;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.dto.sys.NewsSearchDto;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private INewsTypeService newsTypeService;

    @Autowired
    private INewsService newsService;

    /**
     * 跳到首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"","/index"})
    public String index(Model model){

        List<NewsType> newsTypes = newsTypeService.getAll();
       List<NewQueryBO>  newQueryBOS=  newsService.getAll();
        model.addAttribute("newsTypes",newsTypes);
        model.addAttribute("news",newQueryBOS);
        return "index";
    }

    /**
     * 查询所有新闻信息
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result listAllNews(@RequestBody NewsSearchDto newsSearchDto){
        IPage<NewQueryBO> allSystemLogs = newsService.listAllNewsPage(newsSearchDto);
        Map<String, Object> dataTable = getDataTable(allSystemLogs);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }
}
