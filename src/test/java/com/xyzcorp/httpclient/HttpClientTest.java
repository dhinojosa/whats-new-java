package com.xyzcorp.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;

public class HttpClientTest {
    String urlString = "https://gist.githubusercontent" +
        ".com/dhinojosa/877425fb98a939a816e2c56f02bbedd0/raw" +
        "/84158bf19d58dca011cecf267eae0307232b76f3/countries.json";

    @Test
    void testRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    @Test
    @DisplayName("Run an example connecting to a simple" +
        " web endpoint using GET asynchronously and returning a String")
    void testSimpleAsyncGet() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        CompletableFuture<HttpResponse<String>> future =
            client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        future
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println);

        Thread.sleep(1000);
    }


    @Test
    public void testDeserializationOfJson() throws JsonProcessingException {
        var json = """
            {
                "AD": {
                    "currency": {
                        "primary": "EUR"
                    },
                    "iso": {
                        "code2": "AD",
                        "code3": "AND",
                        "num": "020"
                    },
                    "languages": [
                        "ca"
                    ],
                    "name": "Andorra",
                    "region": "Europe"
                },
                "AE": {
                    "currency": {
                        "primary": "AED"
                    },
                    "iso": {
                        "code2": "AE",
                        "code3": "ARE",
                        "num": "784"
                    },
                    "languages": [
                        "ar"
                    ],
                    "name": "United Arab Emirates",
                    "region": "Asia"
                }
           }""";

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, Country>> ref = new TypeReference<>() {};
        HashMap<String, Country> stringCountryHashMap =
            objectMapper.readValue(json, ref);

        System.out.println(stringCountryHashMap);

    }

    public Map<String, Country> processJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, Country>> ref = new TypeReference<>() {};
        try {
            return objectMapper.readValue(json, ref);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }


    @Test
    void testSimpleAsyncGetJson() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
            HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .build();

        CompletableFuture<HttpResponse<String>> future =
            client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString());

        future
            .thenApply(HttpResponse::body)
            .thenApply(this::processJson)
            .thenAccept(System.out::println);

        Thread.sleep(4000);
    }
}
