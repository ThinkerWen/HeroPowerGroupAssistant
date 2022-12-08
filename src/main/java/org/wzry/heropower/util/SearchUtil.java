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
        String url = "https://www.hive-net.cn/funtools/heroPower/getPower?"
                .concat("hero=").concat(hero)
                .concat("&type=").concat(type)
                .concat("&token=").concat(token);
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
        JsonNode data = jsonNode.get("data");
        JsonNode jsonProvince = data.get("province");
        JsonNode jsonCity = data.get("city");
        JsonNode jsonArea = data.get("area");
        return String.format(HERO_POWER_ALL,
                data.get("server").asText(), data.get("name").asText(), data.get("updatetime").asText(),
                jsonProvince.get("name").asText(), jsonProvince.get("power").asText(),
                jsonCity.get("name").asText(), jsonCity.get("power").asText(),
                jsonArea.get("name").asText(), jsonArea.get("power").asText());
    }

    public static String getGameServer(String server) {
        if ("安卓QQ".equalsIgnoreCase(server)) return "aqq";
        else if ("安卓微信".equalsIgnoreCase(server)) return "awx";
        else if ("苹果QQ".equalsIgnoreCase(server)) return "ios_qq";
        else if ("苹果微信".equalsIgnoreCase(server)) return "ios_wx";
        else return null;
    }
}
