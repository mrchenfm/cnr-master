package com.ecut.cnr.view.fastdfs;

import com.ecut.cnr.view.fastdfs.pool.ConnectionPool;
import com.github.tobato.fastdfs.proto.storage.DownloadFileWriter;
import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;

/**
 * @Auther: fangming_chen
 * @Date: 2020/4/3 09:27
 * @Description:
 */
public class FastDFSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSService.class);
    /** 连接池 */
    private ConnectionPool connectionPool = null;
    @Resource
    private FastDFSFile fastDFSFile;

    /**
     * 初始化线程池
     */
    public void init() {
        //初始化fastDfs的相关参数
        fastDFSFile.init();
        connectionPool = new ConnectionPool(fastDFSFile);
    }


    public String uploadFile(String filePath, String extName) throws MyException {
        String logId = System.currentTimeMillis()+"";
        /** 封装文件信息参数 */
        NameValuePair[] metaList = new NameValuePair[] { new NameValuePair( "fileName", "") };
        TrackerServer trackerServer = null;
        try {

            /** 获取fastdfs服务器连接 */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
            /** 以文件字节的方式上传 */
            String[] results = client1.upload_file(filePath, extName, metaList);
            /** results[0]:组名，results[1]:远程文件名 */
            if (results != null && results.length == 2) {
                return results[0] + "/" + results[1];
            } else {
                /** 文件系统上传返回结果错误 */
                throw new MyException("文件系统上传返回结果错误");
            }
        } catch (SocketTimeoutException e) {
            throw new MyException("等待空闲连接超时");
        } catch (Exception e) {
            LOGGER.error("[ fastDSF 上传文件][{}]失败，filePath={}", logId, filePath, e);
            connectionPool.drop(trackerServer, logId);
            throw new MyException("系统错误");
        }finally {
            if(trackerServer!=null){
                /** 上传完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
            }
        }

    }

    /**
     * @param groupName 组名，默认group1，后新增了group2
     * @param fileBytes 文件字节数组
     * @param extName 文件扩展名：如png
     * @return 图片上传成功后地址
     * @throws MyException
     *
     */
    public String uploadFile(String groupName, byte[] fileBytes, String extName) throws MyException {
        String logId =System.currentTimeMillis()+"";
        /** 封装文件信息参数 */
        NameValuePair[] metaList = new NameValuePair[] { new NameValuePair( "fileName", "") };
        TrackerServer trackerServer = null;
        try {

            /** 获取fastdfs服务器连接 */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

            /** 以文件字节的方式上传 */
            String[] results;
            if (StringUtils.isNotBlank(groupName)) {
                results = client1.upload_file(groupName, fileBytes, extName, metaList);
            } else {
                results = client1.upload_file(fileBytes, extName, metaList);
            }

            /** results[0]:组名，results[1]:远程文件名 */
            if (results != null && results.length == 2) {
                return results[0] + "/" + results[1];
            } else {
                /** 文件系统上传返回结果错误 */
                throw new MyException("文件系统上传返回结果错误");
            }
        } catch (SocketTimeoutException e) {
            LOGGER.error("[ fastDSF 上传文件][{}]失败，filePath={}", logId, "", e);
            throw new MyException("等待空闲连接超时");
        } catch (Exception e) {
            LOGGER.error("[ fastDSF 上传文件][{}]失败，filePath={}", logId, "", e);
            connectionPool.drop(trackerServer, logId);
            throw new MyException("系统错误");
        }finally {
            if(trackerServer!=null){
                /** 上传完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
            }
        }
    }

    /**
     * @param fileBytes 文件字节数组
     * @param extName 文件扩展名：如png
     * @return 图片上传成功后地址
     * @throws MyException
     */
    public String uploadFile(byte[] fileBytes, String extName) throws MyException {
        return this.uploadFile(null, fileBytes, extName);
    }

    public void deleteFile(String fileId)  throws MyException {
        String logId = System.currentTimeMillis()+"";
        LOGGER.trace("[ 删除文件（deleteFile）][" + logId + "][parms：fileId="  + fileId + "]");
        TrackerServer trackerServer = null;

        try {
            /** 获取可用的tracker,并创建存储server */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);
            /** 删除文件,并释放 trackerServer */
            int result = client1.delete_file1(fileId);
            /** 0:文件删除成功，2：文件不存在 ，其它：文件删除出错 */
            if (result == 2) {
                throw new MyException("文件不存在");
            } else if (result != 0) {
                throw new MyException("删除文件返回结果错误");
            }

        } catch (MyException e) {
            LOGGER.error("[ fastDSF 删除文件][{}]失败，fileId={}", logId, fileId, e);
            throw e;
        } catch (SocketTimeoutException e) {
            LOGGER.error("[ fastDSF 删除文件][{}]失败，fileId={}", logId, fileId, e);
            throw new MyException("等待空闲连接超时");
        } catch (Exception e) {
            LOGGER.error("[ fastDSF 删除文件][{}]失败，fileId={}", logId, fileId, e);
            connectionPool.drop(trackerServer, logId);
            throw new MyException("系统错误");
        }finally {
            if(trackerServer!=null){
                /** 上传完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
            }
        }
    }

    public  byte[] downloadFile(String fileId) throws MyException {
        String logId = System.currentTimeMillis()+"";
        LOGGER.trace("[ 下载文件（downloadFile）][" + logId + "][parms：fileId= {}]", fileId);
        TrackerServer trackerServer = null;
        try {
            StorageServer storageServer = null;
            /** 获取可用的tracker,并创建存储server */
            trackerServer = connectionPool.checkout(logId);
            StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
            /** 下载文件,并释放 trackerServer */
            byte[] result = client1.download_file1(fileId);
            return result;
        } catch (MyException e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            throw e;
        } catch (SocketTimeoutException e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            throw new MyException("等待空闲连接超时");
        } catch (Exception e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            connectionPool.drop(trackerServer, logId);
            throw new MyException("系统错误");
        }finally {
            if(trackerServer!=null){
                /** 下载完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
            }
        }
    }

    public void downloadFileToPath(String fileId, String outFile) throws MyException {
        String logId = System.currentTimeMillis()+"";
        LOGGER.trace("[ 下载文件（downloadFile）][" + logId + "][parms：fileId= {}, outFile = {}]", fileId, outFile);
        TrackerServer trackerServer = null;

        try {
            /** 获取可用的tracker,并创建存储server */
            trackerServer = connectionPool.checkout(logId);
            StorageServer storageServer = null;
            StorageClient1 client1 = new StorageClient1(trackerServer,
                    storageServer);
            /** 下载文件,并释放 trackerServer */
            int result = client1.download_file1(fileId, outFile);

            /** 下载完毕及时释放连接
             connectionPool.checkin(trackerServer, logId);
             */
            /** 0:文件删除成功，2：文件不存在 ，其它：文件删除出错 */
            if (result == 2) {
                throw new MyException("文件不存在");
            } else if (result != 0) {
                throw new MyException("下载文件返回结果错误");
            }
        } catch (MyException e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            throw e;
        } catch (SocketTimeoutException e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            throw new MyException("等待空闲连接超时");
        } catch (Exception e) {
            LOGGER.error("[ fastDSF 下载文件 ][{}]失败，fileId={}", logId, fileId, e);
            connectionPool.drop(trackerServer, logId);
            throw new MyException("系统错误");
        }finally {
            if(trackerServer!=null){
                /** 下载完毕及时释放连接 */
                connectionPool.checkin(trackerServer, logId);
            }
        }
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
