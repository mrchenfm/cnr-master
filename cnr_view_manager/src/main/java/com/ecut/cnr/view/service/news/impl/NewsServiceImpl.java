package com.ecut.cnr.view.service.news.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.bo.news.NewsBO;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.entity.news.NewsContext;
import com.ecut.cnr.framework.entity.news.NewsTitle;
import com.ecut.cnr.framework.entity.news.NewsType;
import com.ecut.cnr.view.mapper.news.NewsMapper;
import com.ecut.cnr.view.service.news.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsAddServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/20 21:17
 */
@Service
@Slf4j
public class NewsServiceImpl implements INewsService {

    @Autowired
    private IdUtils idUtils;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addNews(NewsBO newsBO) {
        try {
            NewsTitle newsTitle = generateNewsTitl(newsBO);
            NewsContext newsContext = generateNewsContext(newsBO);
            log.info("新闻数据：NewsTitle->{},NewsContext>{}",newsTitle,newsContext);
            int count1 = newsMapper.addNewsTitle(newsTitle);
            int count2 = newsMapper.addNewsContext(newsContext);
            return count1+count1;
        } catch (Exception e) {
            log.error("新闻添加异常：{}",e);
            return 0;
        }
    }

    @Override
    public List<NewsType> listAllTypes() {
        return newsMapper.getAllType();
    }

    private NewsContext generateNewsContext(NewsBO newsBO) {
        NewsContext newsContext = NewsContext.builder().context(newsBO.getContext())
                .id(String.valueOf(idUtils.nextId()))
                .titleId(newsBO.getTitleId()).build();
        return newsContext;
    }

    private NewsTitle generateNewsTitl(NewsBO newsBO) {
        NewsTitle newsTitle = NewsTitle.builder().id(newsBO.getTitleId())
                .typeId(newsBO.getTypeId())
                .userId(newsBO.getUserId())
                .title(newsBO.getTitle())
                .newsPic(newsBO.getNewsPic())
                .commentTimes(0)
                .readTimes(0)
                .pubTime(new Date()).build();
        return newsTitle;
    }
}
