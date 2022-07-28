package org.wzry.heropower.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.wzry.heropower.config.Config;
import org.wzry.heropower.config.Constant;
import org.wzry.heropower.util.JsonConfigUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService implements Constant {

    public static final GroupService INSTANCE = new GroupService();
    private Config config = Config.INSTANCE;
    private String url = "https://api.wzxzs.cn/api/select?hero=%s&type=%s";

    public boolean isHost(Long id) {
        List<Long> hosts = config.getHosts();
        for (Long host : hosts) {
            if (host.equals(id)) return true;
        }
        return false;
    }

    public boolean isGroupEnable(Long id) {
        List<Long> groups = config.getGroups();
        for (Long group : groups) {
            if (group.equals(id)) return true;
        }
        return false;
    }

    public void loadConfig() {
        try {
            config = (Config) JsonConfigUtil.getConfigFromFile("HeroPower", Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setConfig() {
        try {
            JsonConfigUtil.setConfigFile("HeroPower", config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isHeroRight(String hero) {
        String[] allHero = {"韩信","百里玄策","嫦娥","铠","项羽","鲁班七号","孙悟空",
                "上官婉儿","后羿","兰陵王","孙策","妲己","关羽","钟馗","李白",
                "李元芳","钟无艳","伽罗","曜","典韦","百里守约","不知火舞","夏侯惇",
                "狂铁","孙尚香","马可波罗","赵云","虞姬","貂蝉","米莱狄","瑶","公孙离",
                "盘古","澜","牛魔","阿轲","程咬金","刘禅","干将莫邪","黄忠","吕布",
                "姜子牙","宫本武藏","裴擒虎","亚瑟","夏洛特","成吉思汗","庄周","狄仁杰",
                "小乔","诸葛亮","元歌","云中君","花木兰","镜","墨子","安琪拉","刘备",
                "明世隐","张良","露娜","达摩","鬼谷子","李信","司马懿","大乔","盾山",
                "蒙犽","杨戬","扁鹊","娜可露露","高渐离","嬴政","蔡文姬","马超","刘邦",
                "廉颇","张飞","王昭君","梦奇","东皇太一","橘右京","芈月","白起","阿古朵",
                "孙膑","曹操","老夫子","猪八戒","杨玉环","太乙真人","鲁班大师","甄姬","苏烈",
                "周瑜","女娲","西施","雅典娜","沈梦溪","蒙恬","司空震","哪吒","弈星",
                "武则天","艾琳","云缨","金蝉","暃","桑启"};
        for (int i=0; i<allHero.length; i++) {
            if (allHero[i].equals(hero)) return true;
        }
        return false;
    }

    public String getGameServer(String server) {
        if ("安卓QQ".equalsIgnoreCase(server)) return "qq";
        else if ("安卓微信".equalsIgnoreCase(server)) return "wx";
        else if ("苹果QQ".equalsIgnoreCase(server)) return "ios_qq";
        else if ("苹果微信".equalsIgnoreCase(server)) return "ios_wx";
        else return "-1";
    }
    public String getHeroPower(String hero, String server) {
        String url = String.format(this.url, hero, server);
        String body = null;
        Map<String, String> header = new HashMap<String, String>();
        header.put("Host", "api.wzxzs.cn");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        header.put("content-type", "application/json");
        header.put("Referer", "https://servicewechat.com/wxaad8c6dc6599c5d4/26/page-frame.html");

        try {
            body = Jsoup.connect(url).headers(header).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return "-1";
        }

        // 检查查询是否成功
        JSONObject jsonObject = JSONObject.parseObject(body);
        if (jsonObject.getInteger("code") != 200) return "-1";

        // 格式化返回内容
        JSONObject data = jsonObject.getJSONObject("data");
        return formatData(data);
    }

    public String formatData(JSONObject data) {
        String name = data.getString("name");
        String time = data.getString("updatetime");
        return String.format(HERO_POWER_ALL, time, name,
                data.getString("province"), data.getString("provincePower"),
                data.getString("city"), data.getString("cityPower"),
                data.getString("area"), data.getString("areaPower"));
    }

    public String findHeroId(String hero) {
        String[][] allHero = {{"1","韩信"},{"2","百里玄策"},{"3","嫦娥"},{"4","铠"},{"5","项羽"},{"6","鲁班七号"},{"7","孙悟空"},
                {"8","上官婉儿"},{"9","后羿"},{"10","兰陵王"},{"11","孙策"},{"12","妲己"},{"13","关羽"},{"14","钟馗"},{"15","李白"},
                {"16","李元芳"},{"17","钟无艳"},{"18","伽罗"},{"19","曜"},{"20","典韦"},{"21","百里守约"},{"22","不知火舞"},{"23","夏侯惇"},
                {"24","狂铁"},{"25","孙尚香"},{"26","马可波罗"},{"27","赵云"},{"28","虞姬"},{"29","貂蝉"},{"30","米莱狄"},{"31","瑶"},{"32","公孙离"},
                {"33","盘古"},{"34","澜"},{"35","牛魔"},{"36","阿轲"},{"37","程咬金"},{"38","刘禅"},{"39","干将莫邪"},{"40","黄忠"},{"41","吕布"},
                {"42","姜子牙"},{"43","宫本武藏"},{"44","裴擒虎"},{"45","亚瑟"},{"46","夏洛特"},{"47","成吉思汗"},{"48","庄周"},{"49","狄仁杰"},
                {"50","小乔"},{"51","诸葛亮"},{"52","元歌"},{"53","云中君"},{"54","花木兰"},{"55","镜"},{"56","墨子"},{"57","安琪拉"},{"58","刘备"},
                {"59","明世隐"},{"60","张良"},{"61","露娜"},{"62","达摩"},{"63","鬼谷子"},{"64","李信"},{"65","司马懿"},{"66","大乔"},{"67","盾山"},
                {"68","蒙犽"},{"69","杨戬"},{"70","扁鹊"},{"71","娜可露露"},{"72","高渐离"},{"73","嬴政"},{"74","蔡文姬"},{"75","马超"},{"76","刘邦"},
                {"77","廉颇"},{"78","张飞"},{"79","王昭君"},{"80","梦奇"},{"81","东皇太一"},{"82","橘右京"},{"83","芈月"},{"84","白起"},{"85","阿古朵"},
                {"86","孙膑"},{"87","曹操"},{"88","老夫子"},{"89","猪八戒"},{"90","杨玉环"},{"91","太乙真人"},{"92","鲁班大师"},{"93","甄姬"},{"94","苏烈"},
                {"95","周瑜"},{"96","女娲"},{"97","西施"},{"98","雅典娜"},{"99","沈梦溪"},{"100","蒙恬"},{"101","司空震"},{"102","哪吒"},{"103","弈星"},
                {"104","武则天"},{"105","艾琳"},{"109","云缨"},{"110","金蝉"},{"111","暃"},{"112","桑启"}};

        for (int i=0; i<allHero.length; i++) {
            if (allHero[i][1].equals(hero)) return allHero[i][0];
        }
        return "-1";
    }




    public String formatJSONObject(JSONObject jsonObject) {
        JSONObject hero = jsonObject.getJSONObject("data").getJSONObject("hero");
        JSONArray powerList = hero.getJSONArray("list");

        String name = hero.getString("name");
        String time = hero.getString("update_time");
        JSONObject province = powerList.getJSONObject(0);
        JSONObject city = powerList.getJSONObject(1);
        JSONObject district = powerList.getJSONObject(2);

        String result = String.format(HERO_POWER_ALL, time, name,
                province.getString("area"), province.getString("power"),
                city.getString("area"), city.getString("power"),
                district.getString("area"), district.getString("power"));
        return result;
    }

    public String formatOther(JSONObject jsonObject, String condition) {
        JSONObject hero = null;
        try {
            hero = jsonObject.getJSONObject("data").getJSONObject(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
        JSONArray powerList = hero.getJSONArray("list");

        String name = hero.getString("name");
        String time = hero.getString("update_time");
        String powers = "";
        String formatter = "\n%s：%s分";
        for (int i=0; i<powerList.size(); i++) {
            JSONObject power = powerList.getJSONObject(i);
            powers = powers + String.format(formatter, power.getString("area"), power.getString("power"));
        }

        String result = String.format(HERO_POWER_OTHER, time, name, powers);
        return result;
    }
}