package com.lg.mycommunity.util;

public class RedisKeyUtil {


    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";

    //被关注的对象
    private static final String PREFIX_FOLLOWEE = "followee";

    //粉丝
    private static final String PREFIX_FOLLOWER = "follower";

    //验证码
    private static final String PREFIX_KAPTCHA = "kaptcha";

    //登录凭证
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER = "user";


    //某个实体收到的赞，如帖子，评论
    //like:entity:entityType:entityId -> set(userId) 对应set，存入userId
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + entityType + SPLIT + entityId;
    }

    //某个用户收到的总赞数
    //like:user:userId ->int
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    //某个用户关注的实体
    //followee:userId:entityType ->zset(entityId,date),用有序集合存，存的是关注的哪个实体，分数是当前时间。
    //为了后期统计方便，统计关注了哪些，进行排序列举
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    //某个实体拥有的粉丝，实体可能是用户，或者是帖子
    //follower:entityType:entityId ->zset(userId,date)，存入用户Id
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    //登录验证码
    //owner是指随机生成的uuid
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    // 登录的凭证
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    // 用户
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

}