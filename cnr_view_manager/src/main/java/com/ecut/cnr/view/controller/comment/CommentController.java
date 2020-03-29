package com.ecut.cnr.view.controller.comment;

import com.ecut.cnr.framework.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 跳到评论列表页面
     * @return
     */
    @RequestMapping("/list/newComment")
    public String toCommentListPage(){
        return "/comment/listNewsComment";
    }

    /**
     * 跳到评论列表页面
     * @return
     */
    @RequestMapping("/list/commentReply")
    public String toCommentReplyPage(){
        return "/comment/listCommentReply";
    }
}
