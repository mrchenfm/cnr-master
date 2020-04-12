package com.ecut.cnr.view.service.comment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.bo.comment.CommentsInfoBo;
import com.ecut.cnr.framework.entity.comment.CommentsInfo;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.mapper.comment.CommentInfoMapper;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentInfoService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/29 18:39
 */
public interface ICommentInfoService extends IService<CommentsInfo> {
    /**
     * 分页查询新闻评论信息
     * @param queryRequest
     * @return
     */
    IPage<CommentsInfoBo> getListPage(QueryRequest queryRequest);
}
