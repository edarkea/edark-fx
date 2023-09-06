package com.edarkea.edark.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author Steeven Ayui
 */
public class JsonFileConverter<T> {

    public T fromJson(Class<T> type, String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, type);
    }

    public String toJson(T obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    public T fromJson(Class<T> type, InputStream inputStream, Charset charsets) throws IOException, Exception {
        InputStreamReader reader = new InputStreamReader(inputStream, charsets);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        StringBuilder jsonContent = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            jsonContent.append(line);
        }
        bufferedReader.close();
        reader.close();
        inputStream.close();
        return fromJson(type, jsonContent.toString());
    }

}
