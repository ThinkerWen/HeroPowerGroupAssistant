package org.wzry.heropower.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final Config INSTANCE = new Config();

    private boolean enable = true;
    private boolean enableGroup = true;
    private List<Long> hosts = new ArrayList<Long>(){{
        add(296854007L);
        add(2689969038L);
        add(168745806L);
    }};
    private List<Long> groups = new ArrayList<Long>(){{
        add(907230911L);
        add(497793737L);
        add(726925125L);
        add(69687196L);
        add(102032180L);
        add(791673513L);
    }};

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isEnableGroup() {
        return enableGroup;
    }

    public void setEnableGroup(boolean enableGroup) {
        this.enableGroup = enableGroup;
    }

    public List<Long> getHosts() {
        return hosts;
    }

    public void setHosts(List<Long> hosts) {
        this.hosts = hosts;
    }

    public List<Long> getGroups() {
        return groups;
    }

    public void setGroups(List<Long> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Config{" +
                "enable=" + enable +
                ", enableGroup=" + enableGroup +
                ", hosts=" + hosts +
                ", groups=" + groups +
                '}';
    }
}
