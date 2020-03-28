package com.ecut.cnr.customer.view.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.entity.news.NewsType;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: INewsTypeService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/26 20:22
 */
public interface INewsTypeService extends IService<NewsType> {

    /**
     * 查询有新闻类型
     * @return
     */
    List<NewsType> getAll();
}
