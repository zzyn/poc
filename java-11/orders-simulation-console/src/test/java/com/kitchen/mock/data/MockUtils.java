package com.kitchen.mock.data;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class MockUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getMapper() {

        return mapper;
    }

    public static String getPath() {
        return MockUtils.class.getClassLoader().getResource(".").getPath();
    }

    public static URL getMockDataUrl(String fileName) {

        String str = String.format("file://%s%s", getPath(), fileName);
        URL url = null;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
        return url;
    }

    public static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static String readFileAsString(String path) {

        String data = null;
        try {
            InputStream json = MockUtils.class.getClassLoader().getResourceAsStream(path);
            data = readFromInputStream(json);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return data;
    }

    public static void writeObjectAsFile(String path, Object obj) {
        try {
            String list = getMapper().writeValueAsString(obj);
            Files.writeString(Path.of(getMockDataUrl(path).toURI()), list);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static <T> T mockObjectFromFile(String path, Class<T> clazz) {
        String json = readFileAsString(path);
        ObjectMapper mapper = getMapper();
        T obj = null;

        try {
            obj = mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return obj;
    }

    public static <T> List<T> mockListFromFile(String path, Class<T> clazz) {
        String json = readFileAsString(path);
        ObjectMapper mapper = getMapper();
        List<T> list = null;

        try {
            list = mapper.readerForListOf(clazz).readValue(json);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
}

