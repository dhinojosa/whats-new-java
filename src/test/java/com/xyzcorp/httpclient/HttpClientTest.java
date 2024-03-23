package com.xyzcorp.httpclient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class HttpClientTest {
    String urlString = "https://gist.githubusercontent.com/dhinojosa/877425fb98a939a816e2c56f02bbedd0/raw/84158bf19d58dca011cecf267eae0307232b76f3/countries.json";

    @Test
    void testRequest() throws IOException, InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

            Assertions.assertThat(response.body()).isNotBlank();
        }
    }

    @Test
    @DisplayName("Run an example connecting to a simple" +
                 " web endpoint using GET asynchronously and returning a String")
    void testSimpleAsyncGet() throws InterruptedException {

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(urlString))
                    .build();

            client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .get();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSimpleAsyncGetJson() throws InterruptedException {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                HttpRequest
                    .newBuilder()
                    .uri(URI.create(urlString))
                    .build();


            client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(JSONDeserializer::processJson)
                .thenApplyAsync(m -> CountryFunctions.findLanguagesByRegion(m, "es", "Americas"))
                .orTimeout(15, TimeUnit.SECONDS)
                .handle((s, throwable) -> {
                    if (s != null) return s;
                    else {
                        throwable.printStackTrace();
                        return "Error Occured";
                    }
                })
                .thenAccept(System.out::println)
                .get();

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

