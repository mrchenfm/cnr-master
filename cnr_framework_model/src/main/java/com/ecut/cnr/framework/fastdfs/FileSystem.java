package com.ecut.cnr.framework.fastdfs;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FileSystem
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/01 19:01
 */
@Data
@Slf4j
@TableName(value = "t_sys_file")
public class FileSystem {

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

    private Date createTime;

    private Date updateTime;

}
