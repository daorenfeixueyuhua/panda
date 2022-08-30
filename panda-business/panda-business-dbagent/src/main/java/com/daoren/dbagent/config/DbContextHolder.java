package com.daoren.dbagent.config;

/**
 * 线程变量
 * @author peng_da
 * @date  2022/8/10 17:41
 */
public class DbContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    private DbContextHolder(){

    }
    public static void setDbType(String dbType){
        CONTEXT_HOLDER.set(dbType);
    }
    public static String getDbType(){
        return CONTEXT_HOLDER.get();
    }
    public static void clearDbType(){
        CONTEXT_HOLDER.remove();
    }

}
