package com.ecut.cnr.customer.view.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.customer.view.mapper.NewsMapper;
import com.ecut.cnr.customer.view.service.INewsService;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.common.enums.AuditEnum;
import com.ecut.cnr.framework.dto.sys.NewsSearchDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: NewsServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/23 20:43
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public IPage<NewQueryBO> listAllNewsPage(NewsSearchDto newsSearchDto) {
        Page<NewQueryBO> page = new Page<>(newsSearchDto.getPage(), newsSearchDto.getLimit());
        //this.baseMapper.findAllUsersPage(page);
        IPage<NewQueryBO> allTypes = newsMapper.selectAllPage(page,newsSearchDto);
        for(NewQueryBO newQueryBO : allTypes.getRecords()){
            newQueryBO.setAuditStatusName(AuditEnum.getValue(newQueryBO.getAuditStatus()));
        }
        return allTypes;
    }

    @Override
    public List<NewQueryBO> getAll() {
        return newsMapper.findAll();
    }
}
