package org.wzry.heropower;

import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.listener.GroupListener;

public class GlobalListener {
    private GroupListener groupListener = GroupListener.INSTANCE;
    private Config config = Config.INSTANCE;
    public static final GlobalListener INSTANCE = new GlobalListener();

    public void loadListener(EventChannel<Event> eventChannel) {
        if (!config.isEnable()) return;

        if (config.isEnableGroup()) groupListener.listen(eventChannel);
    }
}
