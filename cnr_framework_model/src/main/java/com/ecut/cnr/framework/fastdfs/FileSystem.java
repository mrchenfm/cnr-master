package com.ecut.cnr.framework.fastdfs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
public class FileSystem {

    private String src;
    private String filePath;

    private Long fileSize;

    private String fileName;

    private String fileType;

}
