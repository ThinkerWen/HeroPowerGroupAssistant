package org.wzry.heropower.util;

import org.wzry.heropower.config.Config;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: PermissionUtil
 * Description: 权限控制工具类
 *
 * @author: 王晓文
 * @date: 2022/7/28 22:25
 */
public class PermissionUtil {

    public static boolean isHost(Long id) {
        for (Long host: Config.INSTANCE.getHosts()) {
            if (host.equals(id)) return true;
        }
        return false;
    }

    public static boolean isGroupEnable(Long id) {
        for (Long group:Config.INSTANCE.getGroups()) {
            if (group.equals(id)) return true;
        }
        return false;
    }
}
