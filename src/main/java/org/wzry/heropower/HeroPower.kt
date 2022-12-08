package org.wzry.heropower


import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.event.GlobalEventChannel
import org.wzry.heropower.config.Config

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: Test
 * Description:
 * @author: 王晓文
 * @date: 2022/7/30 17:28
 */


object HeroPower : KotlinPlugin(

    JvmPluginDescriptionBuilder("org.wzry.heropower.HeroPower", "2.4.0")
        .name("HeroPower王者战力查询")
        .info("联系作者VX：Rem_wife\n关注公众号：夜寒信息")
        .author("暮至夜寒")
        .build()
) {

    override fun onEnable() {
        Config.reload()
        logger.info("王者战力查询 For QQGroup 已启用")
        val globalListener = GlobalListener.INSTANCE
        val eventChannel = GlobalEventChannel.parentScope(this)
        globalListener.listen(eventChannel)
    }
}