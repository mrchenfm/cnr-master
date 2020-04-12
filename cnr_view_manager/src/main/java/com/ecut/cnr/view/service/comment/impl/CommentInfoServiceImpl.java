package com.ecut.cnr.view.service.comment.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.bo.comment.CommentsInfoBo;
import com.ecut.cnr.framework.common.enums.AuditEnum;
import com.ecut.cnr.framework.common.enums.CommentsMenu;
import com.ecut.cnr.framework.entity.comment.CommentsInfo;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.mapper.comment.CommentInfoMapper;
import com.ecut.cnr.view.service.comment.ICommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentInfoServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/29 18:40
 */
@Service
public class CommentInfoServiceImpl extends ServiceImpl<CommentInfoMapper, CommentsInfo> implements ICommentInfoService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Override
    public IPage<CommentsInfoBo> getListPage(QueryRequest queryRequest) {
        Page<CommentsInfoBo> all =  new Page<>(queryRequest.getPage(), queryRequest.getLimit());
        IPage<CommentsInfoBo> allComments = commentInfoMapper.findAllComments(all, null);
        for(CommentsInfoBo commentsInfoBo:allComments.getRecords()){
            commentsInfoBo.setAuditStatusName(AuditEnum.getValue(commentsInfoBo.getAuditStatus()));
            commentsInfoBo.setTypeName(CommentsMenu.getValue(commentsInfoBo.getType()));
            commentsInfoBo.setAuditTypeName(AuditEnum.getValue(commentsInfoBo.getAuditType()));
        }
        return allComments;
    }
}
