package org.wzry.heropower;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.listener.GroupEventHolder;
import org.wzry.heropower.listener.SettingEventHolder;
import org.wzry.heropower.util.PermissionUtil;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: GlobalListener
 * Description: 插件全局事件监听加载器
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public class GlobalListener {
    private final GroupEventHolder groupEventHolder = GroupEventHolder.INSTANCE;
    private final SettingEventHolder settingEventHolder = SettingEventHolder.INSTANCE;
    private final Config config = Config.INSTANCE;
    public static final GlobalListener INSTANCE = new GlobalListener();

    public void listen(EventChannel<Event> eventChannel) {
        if (!config.getEnable()) return;

        /*
         * 群消息事件监听
         * 权限控制：
         * 1.当前群开启插件功能
         * 2.消息不为空
         */
        eventChannel.filter(event ->
                        PermissionUtil.isGroupEnable(((GroupMessageEvent) event).getGroup().getId()) &&
                        StringUtils.isNotBlank(((GroupMessageEvent) event).getMessage().contentToString()))
                        .registerListenerHost(groupEventHolder);
        /*
         * 插件设置消息事件监听
         * 权限控制：
         * 1.发言者为插件管理员
         * 2.消息不为空
         */
        eventChannel.filter(event ->
                        PermissionUtil.isHost(((GroupMessageEvent) event).getSender().getId()) &&
                        StringUtils.isNotBlank(((GroupMessageEvent) event).getMessage().contentToString()))
                        .registerListenerHost(settingEventHolder);
    }
}
