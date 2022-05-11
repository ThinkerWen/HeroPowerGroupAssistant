package org.wzry.heropower.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
public class JsonConfigUtil {

    public static <T> T getConfigFromFile(String fileName, Class<T> object) throws IOException {
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
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
        String filePath = String.format("config/%s/%s.json", fileName, fileName, fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,false),"UTF-8"));
        bw.write(jsonString);
        bw.close();
    }

    public static boolean createConfigFile(String fileName) throws IOException {
        String filePath = String.format("config/%s/%s.json", fileName, fileName);
        File file = new File(filePath);
        File dir = new File("config/" + fileName);
        if (!file.exists()) {
            dir.mkdirs();
            file.createNewFile();
            return true;
        }
        return false;
    }
}
