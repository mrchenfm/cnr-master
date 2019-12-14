package com.ecut.cnr.framework.common.constants;

/**
 * @Classname RedisNameConstants
 * @Description
 * @Date 2019/12/14 11:49
 * @Create by fangming_chen
 */
public class RedisNameConstants {
    private final static String PROFIX = "DBBLOG:";

    /**
     * 文章缓存空间名称
     */
    public final static String ARTICLE = PROFIX + "ARTICLE";
    /**
     * 图书缓存空间名称
     */
    public final static String BOOK = PROFIX + "BOOK";

    /**
     * 笔记缓存空间名称
     */
    public final static String BOOKNOTE = PROFIX + "BOOKNOTE";

    /**
     * 友情链接列表
     */
    public final static String LINK = PROFIX + "LINK";

    /**
     * 推荐列表
     */
    public final static String RECOMMEND = PROFIX + "RECOMMEND";

    /**
     * 标签列表
     */
    public final static String TAG = PROFIX + "TAG";

    /**
     * 分类列表
     */
    public final static String CATEGORY = PROFIX +"CATEGORY";

    /**
     * 时光轴
     */
    public static final String TIMELINE =  PROFIX +"TIMELINE";
}
