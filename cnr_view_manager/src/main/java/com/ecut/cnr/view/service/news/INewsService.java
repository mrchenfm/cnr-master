package com.ecut.cnr.view.service.news;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.entity.news.NewsType;

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
}
