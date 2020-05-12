package com.ecut.cnr.view.controller.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.bo.sys.UserInfoBO;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.enums.AuditEnum;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.dto.sys.NewsSearchDto;
import com.ecut.cnr.framework.entity.news.NewsContext;
import com.ecut.cnr.framework.entity.news.NewsTitle;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.news.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @RequestMapping("/my/pub")
    public String listMyNews(){
        return "news/myNewsList";
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
    public Result listAllNews(@RequestBody NewsSearchDto newsSearchDto){

        IPage<NewQueryBO> allSystemLogs = newsService.listAllNewsPage(newsSearchDto);
        Map<String, Object> dataTable = getDataTable(allSystemLogs);
        //Object roleJson = JSONArray.toJSON(dataTable);
        Result result = Result.addMap(dataTable);

        return result;
    }

    /**
     * 查询所有新闻信息
     * @return
     */
    @RequestMapping("/list/personal/all")
    @ResponseBody
    public Result listAllMyNews(@RequestBody NewsSearchDto newsSearchDto){
        Subject subject = SecurityUtils.getSubject();
        UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
        newsSearchDto.setUserId(userInfoBO.getId());
        IPage<NewQueryBO> allSystemLogs = newsService.listAllNewsPage(newsSearchDto);
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
    /**
     * 驳回新闻呢
     * @return
     */
    @RequestMapping("/audit/reject/page")
    public String auditRejectPage(Model model,@RequestParam("id") String id){
        List<NewsContext> contexts = newsService.findContextByTitleId(id);
        if(!ObjectUtils.isEmpty(contexts)){
            model.addAttribute("context",contexts.get(0));
        }
        return "news/newsReject";
    }

    /**
     * 驳回新闻呢
     * @param newsTitle
     * @return
     */
    @RequestMapping("/audit/reject")
    @ResponseBody
    public Result auditReject(@RequestBody NewsTitle newsTitle){
        try {
            //构造驳回参数
            Subject subject = SecurityUtils.getSubject();
            UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
            newsTitle.setAuditId(userInfoBO.getId());
            newsTitle.setAuditTime(new Date());
            newsTitle.setAuditStatus(AuditEnum.NEWS_AUDIT_FAIL_PASS.getKey());
            newsService.updateByAuditReject(newsTitle);
        } catch (Exception e) {
            log.error("新闻驳回报错：{}",e);
            return Result.error("系统报错了！");
        }
        return new Result();
    }

    /**
     * 跳转新闻审核页面
     * @param newsTitle
     * @return
     */
    @PostMapping("/audit/sure")
    @ResponseBody
    public Result auditNews( @RequestBody NewsTitle newsTitle){
        try {
            //构造驳回参数
            Subject subject = SecurityUtils.getSubject();
            UserInfoBO userInfoBO = (UserInfoBO) subject.getPrincipal();
            newsTitle.setAuditId(userInfoBO.getId());
            newsTitle.setAuditTime(new Date());
            newsTitle.setAuditStatus(AuditEnum.NEWS_AUDIT_PASS.getKey());
            newsService.updateByAuditReject(newsTitle);
        } catch (Exception e) {
            log.error("新闻审核通过报错：{}",e);
            return Result.error("系统报错了！");
        }
        return new Result();
    }

    /**
     *修改页面跳转
     * @return
     */
    @RequestMapping("/update/page")
    public String toUpdateNewsPage(Model model,@RequestParam("id") String id){
        NewsBO byTitleId = newsService.findByTitleId(id);
        List<NewsType> typeList = newsService.listAllTypes();
        //log.info("【新闻类别】：{}", JSONUtils.toJSONString(typeList));
        model.addAttribute("types",typeList);
        model.addAttribute("news",byTitleId);
        return "news/updateNews";
    }

    /**
     * 修改新闻信息
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateNews(@RequestBody NewsBO newsBO){
        try {
            if (!ObjectUtils.isEmpty(newsBO.getNewsPic())) {
                newsBO.setNewsPic(CnrContants.BASE_URL_UPLOAD+newsBO.getNewsPic());
            }
            newsService.updateByTtileId(newsBO);
        } catch (Exception e) {
            return  Result.error(e.getMessage());
        }
        return  new Result();
    }

}
