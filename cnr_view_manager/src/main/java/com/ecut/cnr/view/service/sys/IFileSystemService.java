package com.ecut.cnr.view.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ecut.cnr.framework.bo.sys.FileSystemBO;
import com.ecut.cnr.framework.dto.sys.FileSearchDto;
import com.ecut.cnr.framework.fastdfs.FileSystem;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: IFileSystemService
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 11:59
 */
public interface IFileSystemService extends IService<FileSystem> {

    /**
     * 查询文件列表
     * @param fileSearchDto
     * @return
     */
    IPage<FileSystemBO> listAllFilePage(FileSearchDto fileSearchDto);
}
