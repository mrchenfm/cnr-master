package com.ecut.cnr.framework.entity.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentReply
 * @Description: TODO(评论回复实体对象)
 * @Author: fangming_chen
 * @Date: 2020/03/29 14:02
 */
@Data
public class CommentReply implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    /**
     * 评论类型
     */
    private Integer type;
    /**
     * 评论人的id
     */
    private String ownerId;
    private String formName;
    /**
     * 评论者的头像
     */
    private String fromAvatar;
    /**
     * 评论人的id
     */
    private String toId;
    private String toName;
    /**
     * 评论主表Id
     */
    private String cotentId;
    /**
     * 评论者的头像
     */
    private String toAvatar;

    /**
     * 内容
     */
    private String content;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核人id
     */
    private String auditId;

    /**
     * 审核状态
     */
    private Integer auditStatus;
    /**
     * 审核类型
     */
    private Integer auditType;
    /**
     * 评论时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
