package org.wzry.heropower.listener;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.config.Constant;
import org.wzry.heropower.util.SearchUtil;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: GroupEventHolder
 * Description: 群消息事件监听器
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public class GroupEventHolder extends SimpleListenerHost implements Constant {

    public static final GroupEventHolder INSTANCE = new GroupEventHolder();
    private final Config config = Config.INSTANCE;

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        System.out.println("出现未捕获错误");
    }

    @EventHandler
    private void onHelp(@NotNull GroupMessageEvent event){
        if (HELP_KEY.equals(event.getMessage().contentToString())) {
            event.getGroup().sendMessage(HELP);
        }
    }

    @EventHandler
    private void onSearch(@NotNull GroupMessageEvent event) {
        Group group = event.getGroup();
        String message = event.getMessage().contentToString();

        // 解析命令
        String[] params = message.split(" ");
        // 匹配指令前缀是否正确
        if (!PFX.equals(params[0])) return;
        // 检查指令参数正确性
        if (params.length != 3) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }
        String hero = SearchUtil.getHero(params[1]);
        String server = SearchUtil.getGameServer(params[2]);

        // 检查英雄、区服是否错误
        if (StringUtils.isBlank(hero)) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }
        if (StringUtils.isBlank(server)) {
            group.sendMessage(WRONG_TOKEN);
            return;
        }

        // 返回查询结果
        String result = SearchUtil.getHeroPower(config.getToken(), hero, server);
        group.sendMessage(result);
    }
}
