package com.ecut.cnr.view.mapper.comment;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.comment.CommentsInfoBo;
import com.ecut.cnr.framework.entity.comment.CommentsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentInfoMapper
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/29 18:44
 */
@Mapper
@Component
public interface CommentInfoMapper extends BaseMapper<CommentsInfo> {
    /**
     * 分页查询新闻评论
     * @param all
     * @param commentsInfo
     * @return
     */
    IPage<CommentsInfoBo> findAllComments(Page<CommentsInfoBo> all, CommentsInfo commentsInfo);
}
