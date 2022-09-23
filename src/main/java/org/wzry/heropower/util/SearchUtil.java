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
        if (jsonNode.get("code").asInt() != 0) return WRONG_TOKEN;
        JsonNode jsonProvince = jsonNode.get("province");
        JsonNode jsonCity = jsonNode.get("city");
        JsonNode jsonArea = jsonNode.get("area");
        return String.format(HERO_POWER_ALL,
                jsonNode.get("server").asText(), jsonNode.get("name").asText(), jsonNode.get("updatetime").asText(),
                jsonProvince.get("name").asText(), jsonProvince.get("power").asText(),
                jsonCity.get("name").asText(), jsonCity.get("power").asText(),
                jsonArea.get("name").asText(), jsonArea.get("power").asText());
    }

    public static String getGameServer(String server) {
        if ("安卓QQ".equalsIgnoreCase(server)) return "qq";
        else if ("安卓微信".equalsIgnoreCase(server)) return "wx";
        else if ("苹果QQ".equalsIgnoreCase(server)) return "ios_qq";
        else if ("苹果微信".equalsIgnoreCase(server)) return "ios_wx";
        else return null;
    }
}
