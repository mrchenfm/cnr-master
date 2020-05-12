package com.ecut.cnr.view.controller.comment;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ecut.cnr.framework.bo.comment.CommentsInfoBo;
import com.ecut.cnr.framework.common.Result;
import com.ecut.cnr.framework.common.base.BaseController;
import com.ecut.cnr.framework.request.sys.QueryRequest;
import com.ecut.cnr.view.service.comment.ICommentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/29 13:54
 */
@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController {

    @Autowired
    private ICommentInfoService commentInfoService;
    /**
     * 跳到评论列表页面
     * @return
     */
    @RequestMapping("/list/newComment")
    public String toCommentListPage(){
        return "comment/listNewsComment";
    }

    /**
     * 跳到评论列表页面
     * @return
     */
    @RequestMapping("/list/commentReply")
    public String toCommentReplyPage(){
        return "comment/listCommentReply";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/newsCommentsInfo")
    @ResponseBody
    public Result listNewsComments(QueryRequest queryRequest){
        IPage<CommentsInfoBo> allUsers = commentInfoService.getListPage(queryRequest);
        Map<String, Object> dataTable = getDataTable(allUsers);
        return new Result().addMap(dataTable);
    }
}
