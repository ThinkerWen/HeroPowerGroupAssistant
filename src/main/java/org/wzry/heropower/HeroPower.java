package org.wzry.heropower;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;

public final class HeroPower extends JavaPlugin {
    public static final HeroPower INSTANCE = new HeroPower();
    private GlobalListener globalListener = GlobalListener.INSTANCE;

    private HeroPower() {
        super(new JvmPluginDescriptionBuilder("org.wzry.heropower.plugin", "1.0.0")
                .name("王者战力查询")
                .info("联系作者VX：Rem_wife\n关注公众号：夜寒信息")
                .author("暮至夜寒")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("王者战力查询 For QQGroup 已启用");
        EventChannel<Event> eventChannel = GlobalEventChannel.INSTANCE.parentScope(this);
        globalListener.loadListener(eventChannel);
    }
}