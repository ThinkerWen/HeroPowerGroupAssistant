package org.wzry.heropower.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.wzry.heropower.config.Constant;

import java.io.IOException;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: SearchUtil
 * Description: 插件搜索检测工具类
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public class SearchUtil implements Constant {

    public static String getHeroPower(String token, String hero, String type) {
        String url = String.format("https://www.hive-net.cn/heropower/?token=%s&hero=%s&type=%s", token, hero, type);
        String body = null;
        try {
            body = Jsoup.connect(url).ignoreContentType(true).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(body);
        } catch (JsonProcessingException e) {
            return "请求出错，请联系作者！";
        }
        if (jsonNode.get("code").asInt() != 0) return "请求出错，请联系作者！";
        JsonNode jsonProvince = jsonNode.get("province");
        JsonNode jsonCity = jsonNode.get("city");
        JsonNode jsonArea = jsonNode.get("area");
        return String.format(HERO_POWER_ALL,
                jsonNode.get("updatetime").asText(), jsonNode.get("name").asText(),
                jsonProvince.get("name").asText(), jsonProvince.get("power").asText(),
                jsonCity.get("name").asText(), jsonCity.get("power").asText(),
                jsonArea.get("name").asText(), jsonArea.get("power").asText());
    }

    public static String getHero(String searchHero) {
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
                "武则天","艾琳","云缨","金蝉","暃","桑启","戈娅"};
        for (String hero : allHero) if (hero.equals(searchHero)) return hero;
        return null;
    }

    public static String getGameServer(String server) {
        if ("安卓QQ".equalsIgnoreCase(server)) return "qq";
        else if ("安卓微信".equalsIgnoreCase(server)) return "wx";
        else if ("苹果QQ".equalsIgnoreCase(server)) return "ios_qq";
        else if ("苹果微信".equalsIgnoreCase(server)) return "ios_wx";
        else return null;
    }
}
