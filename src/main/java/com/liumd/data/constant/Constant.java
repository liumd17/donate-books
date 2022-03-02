package com.liumd.data.constant;

public final class Constant {
    public static final Integer YES = 1;
    public static final Integer NO = 0;

    /**
     * Token
     */
    //用户登录Token
    public static final String USER_MAILBOX = "UserMailbox";

    // 过期时间30秒
    public static final int EXPIRE_TIME_30_SECD = 1*30*1000;
    // 过期时间1分钟
    public static final int EXPIRE_TIME_1_MINS = 1*60*1000;
    // 过期时间3分钟
    public static final int EXPIRE_TIME_3_MINS = 3*60*1000;
    // 过期时间5分钟
    public static final int EXPIRE_TIME_5_MINS = 5*60*1000;
    // 过期时间30分钟
    public static final int EXPIRE_TIME_30_MINS = 30*60*1000;
    // 过期时间3天
    public static final int EXPIRE_TIME_3 = 3*24*60*60*1000;
    // 过期时间30天
    public static final long EXPIRE_TIME_30 = 30*24*60*60*1000L;

    /**
     * 异常编码
     */
    //空指针异常
    public static final int NULL_ERROR = 401;
    //数据错误
    public static final int DATA_ERROR = 402;

    public Constant() {
    }
}
