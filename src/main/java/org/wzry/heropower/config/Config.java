package org.wzry.heropower.config;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static final Config INSTANCE = new Config();
    private boolean enable = true;
    private String token = "free";
    private List<Long> hosts = new ArrayList<Long>(){{
        add(296854007L);
    }};
    private List<Long> groups = new ArrayList<Long>(){{
        add(726925125L);
    }};

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public List<Long> getHosts() {
        return hosts;
    }

    public void addHost(Long host) {
        for (Long id : this.hosts)
            if (id.equals(host)) return;
        this.hosts.add(host);
    }

    public void removeHost(Long host) {
        this.hosts.remove(host);
    }

    public List<Long> getGroups() {
        return groups;
    }

    public void addGroup(Long group) {
        for (Long id : this.groups)
            if (id.equals(group)) return;
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
