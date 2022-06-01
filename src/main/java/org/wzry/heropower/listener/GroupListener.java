package org.wzry.heropower.listener;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.config.Constant;
import org.wzry.heropower.service.GroupService;

import java.net.URL;

public class GroupListener implements Constant {

    public static final GroupListener INSTANCE = new GroupListener();
    private GroupService service = GroupService.INSTANCE;
    private Config config = Config.getConfigInstance();

    public void listen(EventChannel<Event> eventChannel) {
        //监听群消息
        eventChannel.subscribeAlways(GroupMessageEvent.class, this::handler);
    }

    private void handler(GroupMessageEvent event) {
        Member sender = event.getSender();
        Group group = event.getGroup();
        String message = event.getMessage().contentToString();

        if (StringUtils.isBlank(message)) return;
        // 插件关闭
        if (!config.isEnable() && PFX.equals(message.substring(0, 3))) {
            group.sendMessage(GLOBAL_OFF);
            return;
        }

        // 群插件开关命令
        if (PLUGIN_ON.equals(message) && !service.isGroupEnable(group.getId()) && service.isHost(sender.getId())) {
            config.addGroup(group.getId());
            group.sendMessage(PLUGIN_ON_SCRIPT);
            service.setConfig();
            return;
        }
        if (PLUGIN_OFF.equals(message) && service.isGroupEnable(group.getId()) && service.isHost(sender.getId())) {
            config.removeGroup(group.getId());
            group.sendMessage(PLUGIN_OFF_SCRIPT);
            service.setConfig();
            return;
        }

        // 查战力方法介绍
        if (message.matches(QUESTION)) {
            group.sendMessage(INTRODUCE);
        }

        // 插件管理员添加
        String[] addHostParam = message.split(" ");
        if (addHostParam.length == 2) {
            if (HOST_ADD.equals(addHostParam[0])) {
                config.addHost(Long.parseLong(addHostParam[1]));
                group.sendMessage(ADD_HOST_OK);
                service.setConfig();
            } else if (HOST_REMOVE.equals(addHostParam[0])) {
                config.removeHost(Long.parseLong(addHostParam[1]));
                group.sendMessage(REMOVE_HOST_OK);
                service.setConfig();
            }
        }

        // 检查是否开启
        if (!service.isGroupEnable(group.getId())) {
            group.sendMessage(PLUGIN_OFF_SCRIPT);
            return;
        }

        // 重载配置文件命令
        if (CONFIG_LOAD.equalsIgnoreCase(message) && service.isHost(sender.getId())) {
            service.loadConfig();
            group.sendMessage(CONFIG_LOAD_OK);
        }

        // 查询命令
        String[] params = message.split(" ");
        // 匹配指令前缀是否正确
        if (!PFX.equals(params[0])) return;
        // 检查指令参数正确性
        if (params.length != 3) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }
        // 匹配英雄
        String hero = params[1];
        if (!service.isHeroRight(hero)) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }

        // 匹配区服
        String server = service.getGameServer(params[2]);
        if ("-1".equals(server)) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }

        // 返回查询结果
        String result = service.getHeroPower(hero, server);
        if ("-1".equals(result)) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }
        group.sendMessage(result);
    }
}
