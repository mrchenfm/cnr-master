package com.ecut.cnr.view.config.fastdfs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FdfsConfig
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/19 21:13
 */
@Component
@Data
public class FdfsConfig {

    @Value("${fdfs.resHost}")
    private String resHost;

    @Value("${fdfs.storagePort}")
    private String storagePort;


}
