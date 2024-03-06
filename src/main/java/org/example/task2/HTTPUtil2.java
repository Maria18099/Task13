package org.example.task2;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HTTPUtil2 {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    
    public static void getAllCommentsForLastPost(int userId) throws IOException, InterruptedException {
        int lastPostId = getLastPostIdForUser(userId);
        String commentsUrl = BASE_URL + "/posts/" + lastPostId + "/comments";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(commentsUrl))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        String fileName = "user-" + userId + "-post-" + lastPostId + "-comments.json";
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(response.body());
        }
    }

    private static int getLastPostIdForUser(int userId) throws IOException, InterruptedException {
        String postsUrl = BASE_URL + "/users/" + userId + "/posts";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(postsUrl))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        Post[] posts = new Gson().fromJson(response.body(), Post[].class);
        int lastPostId = 0;
        for (Post post : posts) {
            if (post.getId() > lastPostId) {
                lastPostId = post.getId();
            }
        }
        return lastPostId;
    }

}
