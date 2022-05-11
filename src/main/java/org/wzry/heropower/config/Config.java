package org.wzry.heropower.config;

import java.util.ArrayList;
import java.util.List;

public class Config {
    private boolean enable = true;
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
        add(103172845L);
    }};

    private Config() {}

    private static final class ConfigHolder {
        static final Config config = new Config();
    }

    public static Config getConfigInstance() {
        return ConfigHolder.config;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Long> getHosts() {
        return hosts;
    }

    public void addHost(Long host) {
        this.hosts.add(host);
    }

    public void removeHost(Long host) {
        this.hosts.remove(host);
    }

    public List<Long> getGroups() {
        return groups;
    }

    public void addGroup(Long group) {
        this.groups.add(group);
    }

    public void removeGroup(Long group) {
        this.groups.remove(group);
    }

    @Override
    public String toString() {
        return "Config{" +
                "enable=" + enable +
                ", hosts=" + hosts +
                ", groups=" + groups +
                '}';
    }
}
