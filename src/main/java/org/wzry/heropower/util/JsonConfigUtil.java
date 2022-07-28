package org.wzry.heropower.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Copyright (c) 2022. Jason Wang (wxw126mail@126.com)
 * Title: JsonConfigUtil
 * Description: 插件本地配置文件工具类
 *
 * @author: 王晓文
 * @date: 2022/7/28 23:33
 */
public class JsonConfigUtil {

    public static <T> T getConfigFromFile(String fileName, Class<T> object) throws IOException {
        String absolutePath = new File("config/" + fileName).getAbsolutePath();
        String filePath = String.format("%s/%s.json", absolutePath, fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath)), StandardCharsets.UTF_8));
        StringBuilder jsonString = new StringBuilder();
        String buffer;
        while ((buffer = reader.readLine()) != null) {
            jsonString.append(buffer);
        }

        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(jsonString.toString(), object);
    }

    public static void setConfigFile(String fileName, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,false), StandardCharsets.UTF_8));
        bw.write(jsonString);
        bw.close();
    }

    public static boolean createConfigFile(String fileName) throws IOException {
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        File file = new File(filePath);
        File dir = new File("config/" + fileName);
        if (!file.exists()) {
            return dir.mkdirs() && file.createNewFile();
        }
        return false;
    }
}
