package com.ecut.cnr.view.fastdfs.pool;


import com.ecut.cnr.view.fastdfs.BusinessException;
import com.ecut.cnr.view.fastdfs.FastDFSFile;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * @Auther: fangming_chen
 * @Date: 2020/4/3 09:29
 * @Description:fastDFS连接池
 */
@Slf4j
public class ConnectionPool {
    /** 空闲的连接池 */
    private LinkedBlockingQueue<TrackerServer> idleConnectionPool = null;
    /** 连接池默认最小连接数 */
    private long minPoolSize = 10;
    /** 连接池默认最大连接数 */
    private long maxPoolSize = 30;
    /** 当前创建的连接数 */
    private volatile long nowPoolSize = 0;
    /** 默认等待时间（单位：秒） */
    private long waitTimes = 200;
    /** fastdfs客户端创建连接默认1次 */
    private static final int COUNT = 1;
    FastDFSFile fastDFSFile;
    /**
     * 默认构造方法
     */
    public ConnectionPool(FastDFSFile fastDFSFile) {
        String logId = UUID.randomUUID().toString();
        log.info("[连接池构造方法(ConnectionPool)][logId={}] 默认参数：minPoolSize={},maxPoolSize={},waitTimes={}]",logId,minPoolSize, maxPoolSize, waitTimes);
        this.fastDFSFile = fastDFSFile;
        this.minPoolSize = fastDFSFile.getMinPoolSize();
        this.maxPoolSize = fastDFSFile.getMaxPoolSize();
        this.waitTimes = fastDFSFile.getWaitTimes();
        /** 初始化连接池 */
        poolInit(logId);
        if(idleConnectionPool!=null){
            log.info("logId:{},fastdfs连接池创建成功，size:{}",logId,idleConnectionPool.size());
        }
        /** 注册心跳 */
        HeartBeat beat = new HeartBeat(this);
        beat.beat();
    }

    /**
     * 连接池初始化 (在加载当前ConnectionPool时执行)
     * 1).加载配置文件
     * 2).空闲连接池初始化；
     * 3).创建最小连接数的连接，并放入到空闲连接池；
     */
    private void poolInit(String logId) {
        try {
            /** 加载配置文件 */
            initClientGlobal();
            /** 初始化空闲连接池 */
            idleConnectionPool = new LinkedBlockingQueue<>();
            /** 往线程池中添加默认大小的线程 */
            for (int i = 0; i < minPoolSize; i++) {
                createTrackerServer(logId, COUNT);
            }
        } catch (Exception e) {
            log.error("[FASTDFS初始化(init)--异常][logId={}][异常:{}]",logId, e);
        }
    }

    /**
     * 创建TrackerServer,并放入空闲连接池
     */
    public void createTrackerServer(String logId, int flag) {
        log.trace("[创建TrackerServer(createTrackerServer)][logId={}]",logId);
        TrackerServer trackerServer = null;
        try {
            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            while (trackerServer == null && flag < 5) {
                log.info("[创建TrackerServer(createTrackerServer)][logId={}]第{}次重建", logId, flag);
                flag++;
                initClientGlobal();
                trackerServer = trackerClient.getConnection();
            }
            if (trackerServer != null) {
                boolean conn = org.csource.fastdfs.ProtoCommon.activeTest(trackerServer.getSocket());
                idleConnectionPool.add(trackerServer);
                log.info("logId:{},连接池加入一个fastdfs连接，trackerServer:{},socket state:{}",logId,trackerServer,conn);
                /** 同一时间只允许一个线程对nowPoolSize操作 **/
                synchronized (this) {
                    nowPoolSize++;
                }
            }else {
                log.error("创建fastdfs连接失败，trackerServer为null");
            }
        } catch (Exception e) {
            log.error("[创建TrackerServer(createTrackerServer)][{}][异常：{}]", logId, e);
        } finally {
            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    log.error("[创建TrackerServer(createTrackerServer)--关闭trackerServer异常][{}][异常：{}]",logId, e);
                }
            }
        }
    }

    /**
     * 释放繁忙连接
     * 1.如果空闲池的连接小于最小连接值，就把当前连接放入idleConnectionPool；
     * 2.如果空闲池的连接等于或大于最小连接值，就把当前释放连接丢弃；
     *
     */
    public void checkin(TrackerServer trackerServer, String logId) {
        log.info("[释放当前连接(checkin)][logId={}][prams]", logId, trackerServer);
        if (trackerServer != null) {
            if (idleConnectionPool.size() < minPoolSize) {
                idleConnectionPool.add(trackerServer);
            } else {
                synchronized (this) {
                    if (nowPoolSize != 0) {
                        nowPoolSize--;
                    }
                }
            }
        }

    }

    /**
     * 获取空闲连接
     * 1).在空闲池（idleConnectionPool)中弹出一个连接；
     * 2).把该连接放入忙碌池（busyConnectionPool）中;
     * 3).返回 connection
     * 4).如果没有idle connection, 等待 wait_time秒, and check again
     *
     */
    public TrackerServer checkout(String logId) {

        log.info("[获取空闲连接(checkout)][logId={}]", logId);
        TrackerServer trackerServer = idleConnectionPool.poll();

        if (trackerServer == null) {
            if (nowPoolSize < maxPoolSize) {
                log.info("logId:{},fastdfs当前连接数：[{}]，最大连接数：[{}]，开始创建新的链接,", logId, nowPoolSize, maxPoolSize);
                createTrackerServer(logId, COUNT);
                try {
                    trackerServer = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
                } catch (Exception e) {
                    throw new BusinessException("[获取空闲连接(checkout)-error][\" + logId + \"][error:获取连接超时:{}]",e);
                }
            }
            if (trackerServer == null) {
                throw new BusinessException("[获取空闲连接(checkout)-error][" + logId  + "][error:获取连接超时（" + waitTimes + "s）]");
            }
        }


        log.info("logId:{} 返回fastdfs连接：{}",logId,trackerServer);
        return trackerServer;
    }

    /**
     * 删除不可用的连接，并把当前连接数减一（调用过程中trackerServer报异常，调用一般在finally中）
     * @param trackerServer
     */
    public void drop(TrackerServer trackerServer, String logId) {
        log.trace("[删除不可用连接方法(drop)][logId={}][parms:{}] ", logId, trackerServer);
        if (trackerServer != null) {
            try {
                synchronized (this) {
                    if (nowPoolSize != 0) {
                        nowPoolSize--;
                    }
                }
                trackerServer.close();
            } catch (IOException e) {
                log.trace("[删除不可用连接方法(drop)--关闭trackerServer异常][{}][异常：{}]",logId, e);
            }
        }
    }

    private void initClientGlobal() {
        fastDFSFile.init();
    }

    public LinkedBlockingQueue<TrackerServer> getIdleConnectionPool() {
        return idleConnectionPool;
    }

    public long getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(long minPoolSize) {
        if (minPoolSize != 0) {
            this.minPoolSize = minPoolSize;
        }
    }

    public long getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(long maxPoolSize) {
        if (maxPoolSize != 0) {
            this.maxPoolSize = maxPoolSize;
        }
    }

    public long getWaitTimes() {
        return waitTimes;
    }

    public void setWaitTimes(int waitTimes) {
        if (waitTimes != 0) {
            this.waitTimes = waitTimes;
        }
    }
}

