package com.xyzcorp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.*;

public class FindAllJavaWordsFromURL {

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private static Future<Long> downloadingContentFromURLAsync(final String url) {
            return cachedThreadPool.submit(() -> findAllJavaWordsIn(url));
    }

    private static long findAllJavaWordsIn(String urlString) throws IOException, URISyntaxException {
        System.out.format("%s: %s\n", "S1", Thread.currentThread());
        URLConnection urlConnection = getUrlConnection(new URI(urlString).toURL());

        try (InputStream inputStream = urlConnection.getInputStream();
             InputStreamReader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(in)) {

            System.out.format("%s: %s\n", "S2", Thread.currentThread());
            return reader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\W+")))
                .filter(word -> word.equalsIgnoreCase("java"))
                .count();
        }
    }

    private static URLConnection getUrlConnection(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        // Uncomment and it will work, but this is only one part of the problem
        // urlConnection.setConnectTimeout(1000);
        // urlConnection.setReadTimeout(1000);
        // urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        return urlConnection;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        String javaAlmanacURIString = "https://javaalmanac.io/";
        String openJDKURIString = "https://openjdk.org/";
        Future<Long> future = downloadingContentFromURLAsync(openJDKURIString);
        System.out.println("This should immediately print");
        System.out.println(future.get(2, TimeUnit.SECONDS));
        cachedThreadPool.close();
        cachedThreadPool.shutdown();  // Shutdown the executor service here
    }
}
