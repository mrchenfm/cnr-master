package com.ecut.cnr.framework.bo.comment;

import com.ecut.cnr.framework.common.enums.AuditEnum;
import com.ecut.cnr.framework.common.enums.CommentsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommentsInfoBo
 * @Description: TODO(新闻评论BO)
 * @Author: fangming_chen
 * @Date: 2020/03/29 18:26
 */
@Data
public class CommentsInfoBo implements Serializable {
    private String id;
    /**
     * 评论类型
     */
    private Integer type;

    private String typeName;
    /**
     * 评论人的id
     */
    private String ownerId;
    private String fromName;
    /**
     * 评论者的头像
     */
    private String fromAvatar;
    /**
     * 点赞数
     */
    private Integer likeNum;
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
    private String auditStatusName;
    /**
     * 审核类型
     */
    private Integer auditType;

    private String auditTypeName;
    /**
     * 评论时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
