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
    private Config config = Config.INSTANCE;

    public void listen(EventChannel<Event> eventChannel) {
        eventChannel.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            handler(g);
        });
    }

    private void handler(GroupMessageEvent event) {
        Member sender = event.getSender();
        Group group = event.getGroup();
        String message = event.getMessage().contentToString();
        String id = "0";
        String other = null;

        if (StringUtils.isBlank(message)) return;

        // 开关命令
        if (PLUGIN_ON.equals(message) && !config.isEnable() && service.isHost(sender.getId())) {
            config.setEnable(true);
            group.sendMessage(PLUGIN_ON_SCRIPT);
            return;
        }
        if (PLUGIN_OFF.equals(message) && config.isEnable() && service.isHost(sender.getId())) {
            config.setEnable(false);
            group.sendMessage(PLUGIN_OFF_SCRIPT);
            return;
        }

        // 查询命令
        if (!service.isGroupEnable(group.getId())) return;
        if (!config.isEnable()) {
            group.sendMessage(PLUGIN_OFF_SCRIPT);
            return;
        }

        String[] params = message.split(" ");
        if (!PFX.equals(params[0])) return;
        for (int i=1; i<params.length; i++) {
            // 匹配英雄
            if (i == 1) {
                id = service.findHeroId(params[i]);
                if ("-1".equals(id)) {
                    group.sendMessage(WRONG_TOKEN);
                    return;
                }
            }
            // 匹配其他
            else if (i == 2) {
                other = params[i];
            }
        }

        if ("-1".equals(service.getHeroPower(id, other))) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }
        group.sendMessage(service.getHeroPower(id, other));
    }
}
