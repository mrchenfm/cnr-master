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

    List<NewQueryBO> getAll();
}
