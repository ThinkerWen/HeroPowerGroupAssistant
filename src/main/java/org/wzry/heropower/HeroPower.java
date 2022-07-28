package org.wzry.heropower;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.util.JsonConfigUtil;

import java.io.IOException;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: HeroPower
 * Description: 插件加载入口
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public final class HeroPower extends JavaPlugin {
    public static final HeroPower INSTANCE = new HeroPower();
    private final GlobalListener globalListener = GlobalListener.INSTANCE;

    private HeroPower() {
        super(new JvmPluginDescriptionBuilder("org.wzry.heropower.plugin", "2.0.2")
                .name("王者战力查询")
                .info("联系作者VX：Rem_wife\n关注公众号：夜寒信息")
                .author("暮至夜寒")
                .build());
    }

    @Override
    public void onEnable() {
        Config config = Config.INSTANCE;
        // 首次启动创建本地配置文件，并将配置累内容写入文件中
        try {
            if (JsonConfigUtil.createConfigFile("HeroPower")) {
                JsonConfigUtil.setConfigFile("HeroPower", config);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 每次启动插件载入本地json
        try {
            config = (Config) JsonConfigUtil.getConfigFromFile("HeroPower", Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLogger().info("王者战力查询 For QQGroup 已启用");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        globalListener.listen(eventChannel);
    }
}