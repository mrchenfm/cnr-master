package com.ecut.cnr.customer.view.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.dto.sys.NewsSearchDto;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: INewsService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/23 20:43
 */
public interface INewsService {
    /**
     * 客户端查询新闻信息
     * @param newsSearchDto
     * @return
     */
    IPage<NewQueryBO> listAllNewsPage(NewsSearchDto newsSearchDto);

    /**
     * 获取所有新闻信息
     * @return
     */
    List<NewQueryBO> getAll();

    /**
     * 根据类型获取新闻信息
     * @param id
     * @return
     */
    List<NewQueryBO> getAllByType(String id);

    /**
     * 根据id获取新闻详情
     * @param id
     * @return
     */
    NewQueryBO getById(String id);
}
