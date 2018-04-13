package com.thanos.common.masterslave;

/**
 * Created by Kevin5_Li on 2017/5/26.
 */
public class DynamicDataSourceHolder {

    //写库对应的数据源key
    private static final String MASTER = "master";

    //读库对应的数据源key
    private static final String SLAVE = "slave";

    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 设置数据源key
     * @param key
     */
    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }

    /**
     * 标记写库
     */
    public static void markMaster(){
//        putDataSourceKey(MASTER);
        clearDataSource();
    }

    /**
     * 标记读库
     */
    public static void markSlave(){
        putDataSourceKey(SLAVE);
    }

    /**
     * 清除ThreadLocal
     */
    public static void clearDataSource() {
        holder.remove();
    }

}
