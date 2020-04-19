package com.ecut.cnr.view.utils;

import com.ecut.cnr.framework.common.constants.CnrContants;
import com.ecut.cnr.framework.common.utils.DateUtils;
import com.ecut.cnr.framework.common.utils.IdUtils;
import com.ecut.cnr.framework.fastdfs.FileSystem;
import com.ecut.cnr.view.config.fastdfs.FdfsConfig;
import com.ecut.cnr.view.service.sys.IFileSystemService;
import com.github.tobato.fastdfs.domain.MateData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

/**
 * @version v1.0
 * @ProjectName: cnr_master
 * @ClassName: CommonFileUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: fangming_chen
 * @Date: 2020/03/19 21:19
 */
@Component
@Slf4j
public class CommonFileUtil {

    @Autowired
    IdUtils idUtils;
    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private IFileSystemService fileSystemService;


    /**
     *	MultipartFile类型的文件上传ַ
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file,String id) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        FileSystem fileSystem = new FileSystem();
        generateSysFileInfo(file, id, storePath, fileSystem);
        fileSystemService.save(fileSystem);
        return getResAccessUrl(storePath);
    }

    private void generateSysFileInfo(MultipartFile file, String id, StorePath storePath, FileSystem fileSystem) {
        fileSystem.setId(String.valueOf(idUtils.nextId()));
        fileSystem.setSrc(getResAccessUrl(storePath));
        fileSystem.setFilePath(CnrContants.BASE_URL_UPLOAD+getResAccessUrl(storePath));
        fileSystem.setFileSize(file.getSize());
        fileSystem.setFileType(file.getContentType());
        fileSystem.setFileName(storePath.getPath());
        fileSystem.setUserId(id);
        fileSystem.setUpdateTime(new Date());
        fileSystem.setCreateTime(new Date());
    }

    /**
     * 普通的文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = storageClient.uploadFile(inputStream, file.length(),
                FilenameUtils.getExtension(file.getName()), null);
        return getResAccessUrl(path);
    }

    /**
     * 带输入流形式的文件上传
     *
     * @param is
     * @param size
     * @param fileName
     * @return
     */
    public String uploadFileStream(InputStream is, long size, String fileName) {
        StorePath path = storageClient.uploadFile(is, size, fileName, null);
        return getResAccessUrl(path);
    }

    /**
     * 将一段文本文件写到fastdfs的服务器上
     *
     * @param content
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath path = storageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(path);
    }

    /**
     * 返回文件上传成功后的地址名称ַ
     * @param storePath
     * @return
     */
    private String getResAccessUrl(StorePath storePath) {
        String fileUrl = storePath.getFullPath();
        return fileUrl;
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            log.warn(e.getMessage());
        }
    }

    public String upfileImage(InputStream is, long size, String fileExtName, Set<MateData> metaData) {
        StorePath path = storageClient.uploadImageAndCrtThumbImage(is, size, fileExtName, metaData);
        return getResAccessUrl(path);
    }
}
