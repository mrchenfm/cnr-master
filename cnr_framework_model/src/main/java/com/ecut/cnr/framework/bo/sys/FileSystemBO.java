package com.ecut.cnr.framework.bo.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FileSystemBO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 12:44
 */
@Data
public class FileSystemBO implements Serializable {
    private String id;
    /**
     * 文件目录
     */
    private String src;
    /**
     * 文件地址
     */
    private String filePath;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类别
     */
    private String fileType;

    /**
     * 上传用户
     */
    private String userId;

    private String userName;

    private Date createTime;

    private Date updateTime;
}
