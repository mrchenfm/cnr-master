package com.ecut.cnr.customer.view.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.entity.news.NewsContext;
import com.ecut.cnr.framework.entity.news.NewsTitle;
import com.ecut.cnr.framework.entity.news.NewsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsAddMapper
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 21:18
 */
@Component
@Mapper
public interface NewsMapper {

    /**
     * 分页查询新闻信息
     * @param page
     * @param o
     * @return
     */
    IPage<NewQueryBO> selectAllPage(Page<NewQueryBO> page, Object o);

    /**
     * 查询所有新闻信息
     * @return
     */
    List<NewQueryBO> findAll();
}
