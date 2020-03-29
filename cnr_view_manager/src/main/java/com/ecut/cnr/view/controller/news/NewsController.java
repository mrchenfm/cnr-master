package com.ecut.cnr.view.controller.news;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.log.SysLog;
import com.ecut.cnr.framework.entity.news.NewsContext;
import com.ecut.cnr.framework.entity.news.NewsTitle;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.news.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
public class NewsController extends BaseController {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private INewsService newsService;

    @RequestMapping("/newsList")
    public String listNews(){
        return "news/listNews";
    }

    /**
     * 转到新闻类别列表
     * @return
     */
    @RequestMapping("/newsTypeList")
    public String toNewsTypeList(){
        return "news/newsTypeList";
    }

    /**
     * 转到添加新闻类别页面
     * @return
     */
    @RequestMapping("/addNewsType")
    public String toAddNewsType(){
        return "news/addNewsType";
    }

    /**
     * 新闻添加
     * @param newsBO
     * @return
     */
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

    /**
     * 转到新闻添加页面
     * @param model
     * @return
     */
    @RequestMapping("/addNews")
    public String toAddNews(Model model){
        List<NewsType> typeList = newsService.listAllTypes();
        //log.info("【新闻类别】：{}", JSONUtils.toJSONString(typeList));
        model.addAttribute("types",typeList);
        return "news/addNews";
    }

    /**
     * 删除新闻类别byID
     * @param id
     * @return
     */
    @RequestMapping("/deleteTypeById")
    @ResponseBody
    public Result deleteTypeById(String id){
        return new Result();
    }

    /**
     * 通过id修改新闻类别信息
     * @param id
     * @return
     */
    @RequestMapping("/updateType")
    @ResponseBody
    public Result updatTypeById(String id){
        return new Result();
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

    @RequestMapping("/add/type")
    @ResponseBody
    public Result addType(@RequestBody NewsType newsType){
        newsType.setId(String.valueOf(idUtils.nextId()));
        int count = newsService.saveNewsType(newsType);
        if(count<1){
            return Result.error("新闻类别添加失败");
        }
        return new Result();
    }

    /**
     * 查询所有新闻信息
     * @return
     */
    @RequestMapping("/list/all")
    @ResponseBody
    public Result listAllNews(QueryRequest queryRequest){
        IPage<NewQueryBO> allSystemLogs = newsService.listAllNewsPage(queryRequest);
        Map<String, Object> dataTable = getDataTable(allSystemLogs);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }

    /**
     * 查询所有新闻信息
     * @return
     */
    @RequestMapping("/show")
    public String showContext(Model model,@RequestParam("id") String id){
        //model.addAttribute("context",context);
        List<NewsContext> contexts = newsService.findContextByTitleId(id);
        if(!ObjectUtils.isEmpty(contexts)){
            model.addAttribute("context",contexts.get(0));
        }
        return "news/showContext";
    }

    @RequestMapping("/audit/reject")
    @ResponseBody
    public Result auditReject(@RequestParam("id") String id){
        return new Result();
    }

    @RequestMapping("/audit/sure")
    public String toAuditPage(Model model , @RequestParam("id") String id){

        return "/news/newsAudit";
    }


}
