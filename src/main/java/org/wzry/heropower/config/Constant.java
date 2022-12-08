package org.wzry.heropower.config;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: Constant
 * Description: 插件常量
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public interface Constant {

    // ================================ 触发关键词 ================================ //

    /**
     * 查询战力前缀
     */
    String PFX = "查战力";

    /**
     * 查询战力提示触发关键词
     */
    String HELP_KEY = "查战力帮助";

    /**
     * 添加管理员触发关键词
     */
    String HOST_ADD_KEY = "添加插件管理员";

    /**
     * 移除管理员触发关键词
     */
    String HOST_REMOVE_KEY = "移除插件管理员";

    /**
     * 开启当前群战力查询功能触发关键词
     */
    String SEARCH_ON_KEY = "开启战力查询";

    /**
     * 关闭当前群战力查询功能触发关键词
     */
    String SEARCH_OFF_KEY = "关闭战力查询";

    /**
     * 重新加载配置文件触发关键词
     */
    String LOAD_CONFIG_KEY = "加载HeroPower配置文件";

    // ================================ KEY对应回复 ================================//

    String HELP = "查战力请输入：\n“查战力 英雄 区服”\n例如：\n\n" +
                    "查战力 李白 安卓QQ\n" +
                    "查战力 李白 苹果微信\n\n" +
                    "不要漏掉空格";

    String HOST_ADD = "添加管理员成功";

    String HOST_REMOVE = "移除管理员成功";

    String SEARCH_ON = "已开启战力查询！\n输入“查战力帮助”，获取战力查询方法";

    String SEARCH_OFF = "已关闭战力查询，需要开启请找管理员";

    String LOAD_CONFIG = "加载配置文件成功";

    String WRONG_TOKEN = "查询指令错误！\n输入“查战力帮助”，获取战力查询方法";

    // ================================ Format格式标本 ================================ //

    String HERO_POWER_ALL = "查询结果如下：\n\n" +
            "系统：%s\n" +
            "英雄：%s\n\n" +
            "更新时间：\n" +
            "%s\n\n" +
            "省标：\n" +
            "%s %s分\n" +
            "市标：\n" +
            "%s %s分\n" +
            "区标：\n" +
            "%s %s分\n\n" +
            "微信小程序《峡谷战力查改》";

}
