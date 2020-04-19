package com.ecut.cnr.view.service.sys.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ecut.cnr.framework.bo.news.NewQueryBO;
import com.ecut.cnr.framework.bo.sys.FileSystemBO;
import com.ecut.cnr.framework.common.utils.DateUtils;
import com.ecut.cnr.framework.dto.sys.FileSearchDto;
import com.ecut.cnr.framework.fastdfs.FileSystem;
import com.ecut.cnr.view.mapper.sys.FileSystemMapper;
import com.ecut.cnr.view.service.sys.IFileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: FileSystemServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/04/19 12:00
 */
@Service
public class FileSystemServiceImpl extends ServiceImpl<FileSystemMapper, FileSystem> implements IFileSystemService {

    @Autowired
    private FileSystemMapper fileSystemMapper;

    @Override
    public IPage<FileSystemBO> listAllFilePage(FileSearchDto fileSearchDto) {
        if(!ObjectUtils.isEmpty(fileSearchDto.getCreateTime())){
            fileSearchDto.setStart(DateUtils.convert(fileSearchDto.getCreateTime().split(" - ")[0],DateUtils.DATE_FORMAT));
            fileSearchDto.setEnd(DateUtils.convert(fileSearchDto.getCreateTime().split(" - ")[1],DateUtils.DATE_FORMAT));
        }
        Page<FileSystemBO> page = new Page<>(fileSearchDto.getPage(), fileSearchDto.getLimit());
        IPage<FileSystemBO> files = fileSystemMapper.listAllFile(page,fileSearchDto);
        return files;
    }
}
