package com.ecut.cnr.view.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecut.cnr.framework.bo.sys.FileSystemBO;
import com.ecut.cnr.framework.dto.sys.FileSearchDto;
import com.ecut.cnr.framework.fastdfs.FileSystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FileSystemMapper
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 12:01
 */
@Mapper
@Component
public interface FileSystemMapper extends BaseMapper<FileSystem> {
    /**
     * 查询文件列表
     * @param page
     * @param fileSearchDto
     * @return
     */
    IPage<FileSystemBO> listAllFile(Page<FileSystemBO> page, @Param("fileSearchDto") FileSearchDto fileSearchDto);
}
