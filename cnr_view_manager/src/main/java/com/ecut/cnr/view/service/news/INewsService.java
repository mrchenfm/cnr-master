package com.ecut.cnr.view.service.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.entity.news.NewsContext;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.framework.request.sys.QueryRequest;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: INewsAddService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 21:16
 */
public interface INewsService {
    /**
     * 添加新闻
     * @param newsBO
     * @return
     */
    int addNews(NewsBO newsBO);

    /**
     * 查找所有新闻类型
     * @return
     */
    List<NewsType> listAllTypes();

    IPage<NewsType> listAllTypesPage(QueryRequest queryRequest);

    /**
     * 添加新闻类别
     * @param newsType
     * @return
     */
    int saveNewsType(NewsType newsType);

    /**
     * 查询所有新闻信息
     * @param queryRequest
     * @return
     */
    IPage<NewQueryBO> listAllNewsPage(QueryRequest queryRequest);

    /**
     * 根据id查询context
     * @param id
     * @return
     */
    List<NewsContext> findContextByTitleId(String id);
}
