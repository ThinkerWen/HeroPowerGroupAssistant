package org.wzry.heropower.listener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.config.Constant;
import org.wzry.heropower.util.JsonConfigUtil;

import java.io.IOException;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: SettingEventHolder
 * Description: 管理员消息事件监听器
 *
 * @author: 王晓文
 * @date: 2022/7/28 22:28
 */
public class SettingEventHolder extends SimpleListenerHost implements Constant {

    public static final SettingEventHolder INSTANCE = new SettingEventHolder();
    Config config = Config.INSTANCE;

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        System.out.println("出现未捕获错误");
    }

    @EventHandler
    private void onSettings(@NotNull GroupMessageEvent event) throws IOException {
        Group group = event.getGroup();
        long host = 296854007L;
        String[] param = event.getMessage().contentToString().split(" ");
        if (param.length == 2) host = Long.parseLong(param[1].substring(1));
        // 开启查战力功能
        if (SEARCH_ON_KEY.equals(event.getMessage().contentToString())) {
            config.addGroup(group.getId());
            JsonConfigUtil.setConfigFile("HeroPower", config);
            group.sendMessage(SEARCH_ON);
        }
        // 关闭查战力功能
        else if (SEARCH_OFF_KEY.equals(event.getMessage().contentToString())) {
            config.removeGroup(group.getId());
            JsonConfigUtil.setConfigFile("HeroPower", config);
            group.sendMessage(SEARCH_OFF);
        }
        // 添加此QQ为管理员
        else if (HOST_ADD_KEY.equals(param[0])) {
            config.addHost(host);
            JsonConfigUtil.setConfigFile("HeroPower", config);
            group.sendMessage(HOST_ADD);
        }
        // 移除此QQ的管理员
        else if (HOST_REMOVE_KEY.equals(param[0])) {
            config.removeHost(host);
            JsonConfigUtil.setConfigFile("HeroPower", config);
            group.sendMessage(HOST_REMOVE);
        }
        // 重新加载配置文件
        else if (LOAD_CONFIG_KEY.equals(event.getMessage().contentToString())) {
            JsonConfigUtil.getConfigFromFile("HeroPower", Config.class);
            group.sendMessage(LOAD_CONFIG);
        }
    }
}
