package com.ecut.cnr.view.mapper.news;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.dto.sys.NewsSearchDto;
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
     * 添加新闻标题
     * @return
     * @param newsTitle
     */
    int addNewsTitle(@Param("newsTitle") NewsTitle newsTitle);

    /**
     * 添加新闻内容
     * @param newsContext
     * @return
     */
    int addNewsContext(@Param("newsContext")NewsContext newsContext);

    /**
     * 获取所有新闻类型
     * @return
     */
    List<NewsType> getAllType();

    /**
     * 分页查询新闻信息
     * @param page
     * @param newsSearchDto
     * @return
     */
    IPage<NewQueryBO> selectAllPage(Page<NewQueryBO> page, @Param("newsSearchDto") NewsSearchDto newsSearchDto);

    /**
     * 根据titleId查询context
     * @param id
     * @return
     */
    List<NewsContext> findContextByTitleId(@Param("id") String id);

    /**
     * 修改新闻信息（审核）
     * @param newsTitle
     */
    void updateNewsTitle(@Param("newsTitle") NewsTitle newsTitle);
}
