package com.xyzcorp.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JSONDeserializer {
    public static Map<String, Country> processJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, Country>> ref = new TypeReference<>() {};
        try {
            return objectMapper.readValue(json, ref);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
