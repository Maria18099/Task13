package org.example.task1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static User sendGet(URI uri) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }
    public static User[] sendGetAll(URI uri) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User[].class);
    }
    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
        String requestBody = GSON.toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type","application/json")
                .build();
        HttpResponse<String> response = CLIENT.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(),User.class);
    }
    public static int deleteUser(URI uri) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(uri)
                        .header("Content-type","application/json")
                        .DELETE()
                        .build();
        return CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString()).statusCode();
    }
    public static User update(URI uri, User user) throws IOException, InterruptedException {
        String userGson = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(userGson))
                .build();
        HttpResponse<String> response= CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status code: " + response.statusCode());
        return  GSON.fromJson(response.body(),User.class);
    }
}
