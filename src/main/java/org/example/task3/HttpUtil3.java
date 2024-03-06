package org.example.task3;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil3 {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static List<Task> getAllOpenTasks(int userID) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL+"/"+userID+"/todos"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(httpRequest,HttpResponse.BodyHandlers.ofString());
        Task[] tasks = new Gson().fromJson(response.body(),Task[].class);
        List<Task> openTasksList = new ArrayList<>();
        for (Task task:
             tasks) {
            if(!task.isCompleted()){
                openTasksList.add(task);
            }
        }
        return openTasksList;
    }
}
