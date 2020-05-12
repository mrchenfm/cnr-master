package com.ecut.cnr.view.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.enums.ErrorEnum;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.news.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsTypeController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 17:11
 */
@Controller
@Slf4j
@RequestMapping("/news")
public class NewsTypeController extends BaseController {

    @Autowired
    private INewsService newsService;

    /**
     * 转到新闻类别列表
     * @return
     */
    @RequestMapping("/newsTypeList")
    public String toNewsTypeList(){
        return "news/newsTypeList";
    }
    /**
     * 列出新闻类别
     * @param queryRequest
     * @return
     */
    @RequestMapping("/list/type")
    @ResponseBody
    public Result listAllType(QueryRequest queryRequest){
        IPage<NewsType> newsTypes = newsService.listAllTypesPage(queryRequest);
        Map<String, Object> dataTable = getDataTable(newsTypes);

        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);
        return result;
    }
    /**
     * 转到添加新闻类别页面
     * @return
     */
    @RequestMapping("/addNewsType")
    public String toAddNewsType(){
        return "news/addNewsType";
    }

    @RequestMapping("/updateType/page")
    public String toUpdatePage(Model model,@RequestParam("id") String id){

        NewsType newsType = newsService.findTypeById(id);
        model.addAttribute("newsType",newsType);
        return "news/newsTypeUpdate";
    }

    @RequestMapping("/delete/type")
    @ResponseBody
    public Result toUpdatePage(String id){

        try {
            newsService.deleteTypeById(id);
        } catch (Exception e) {
            log.error("删除新闻类型sql异常",e);
            return Result.error(ErrorEnum.SQL_ILLEGAL);
        }
        return new Result();
    }

    @RequestMapping("/update/type")
    @ResponseBody
    public Result updateType(@RequestBody NewsType newsType){

        try {
            newsService.updateNewsTypeById(newsType);
        } catch (Exception e) {
            log.error("修改新闻类型sql异常",e);
            return Result.error(ErrorEnum.SQL_ILLEGAL);
        }
        return new Result();
    }

}
