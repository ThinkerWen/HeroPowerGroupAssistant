package org.wzry.heropower.config;

public interface Constant {

    String PFX = "查战力";

    String PLUGIN_ON = "开启战力查询";

    String PLUGIN_OFF = "关闭战力查询";

    String ALL_PROVINCE = "查省榜";

    String ALL_CITY = "查市榜";

    String ALL_DISTRICT = "查区榜";

    String PLUGIN_ON_SCRIPT = "已开启战力查询\n\n" +
            "请输入：\n" +
            "“查战力 英雄 [榜级别]”来查询\n" +
            "例如：\n" +
            "查战力 李白\n\n" +
            "或者\n" +
            "查战力 李白 查省榜";

    String PLUGIN_OFF_SCRIPT = "已关闭战力查询，请找管理员激活";

    String WRONG_TOKEN = "查询指令错误" +
            "请输入：\n" +
            "“查战力 英雄”来查询\n" +
            "不要漏掉空格，例如：\n" +
            "查战力 李白";

    String HERO_POWER_ALL = "查询结果如下：\n\n" +
            "更新时间：\n" +
            "%s\n" +
            "英雄：%s\n" +
            "省标：\n" +
            "%s %s分\n" +
            "市标：\n" +
            "%s %s分\n" +
            "区标：\n" +
            "%s %s分";

    String HERO_POWER_OTHER = "查询结果如下：\n\n" +
            "更新时间：\n" +
            "%s\n" +
            "英雄：%s\n" +
            "%s";

}
