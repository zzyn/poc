package com.spring.libs.flux.auth;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public final class TestUtils {

    public static String getFileAsString(String relativePath) {
        InputStream inputStream;
        try {
            inputStream = TestUtils.class.getClassLoader().getResourceAsStream(relativePath);
            String result = readFromInputStream(inputStream);
            inputStream.close();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> getListDataFromJsonFile(String relativeFilePath, Class<T> clazz) {
        try {
            InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(relativeFilePath);
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(is, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getSingleDataFromJsonFile(String relativeFilePath, Class<T> clazz) {
        InputStream is = TestUtils.class.getClassLoader().getResourceAsStream(relativeFilePath);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        T root = null;
        try {
            root = mapper.readValue(is, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return root;
    }

    private static String readFromInputStream(InputStream inputStream)
        throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
