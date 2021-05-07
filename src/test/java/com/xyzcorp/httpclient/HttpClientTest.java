package com.xyzcorp.httpclient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpClientTest {
    String urlString = "https://gist.githubusercontent.com/dhinojosa/877425fb98a939a816e2c56f02bbedd0/raw/84158bf19d58dca011cecf267eae0307232b76f3/countries.json";

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

        CompletableFuture<String> stringCompletableFuture = future
            .thenApply(HttpResponse::body)
            .thenApply(JSONDeserializer::processJson)
            .thenApplyAsync(m -> CountryFunctions.findLanguagesByRegion(m, "en", "Americas"));

        stringCompletableFuture
            .orTimeout(10, TimeUnit.SECONDS)
            .thenAccept(System.out::println);

        Thread.sleep(5000);
    }
}
