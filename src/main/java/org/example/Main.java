package org.example;

import org.example.task1.*;
import org.example.task2.HTTPUtil2;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {
    private static final String urlUsers = "https://jsonplaceholder.typicode.com/users";
    private static final String urlUserByID = "https://jsonplaceholder.typicode.com/users";
    private static final String urlUserByUsername = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException, InterruptedException {
        //Task 1
        //1. Create user
        System.out.println("\nCreate user: ");
        User user1 = HttpUtil.sendPost(URI.create(urlUsers),createUser());
        System.out.println(user1);

        //2. Update user
        System.out.println("\nUpdate user: ");
        User user2 = HttpUtil.update(URI.create(urlUsers+"/2"), user1);
        System.out.println(user2);

        //3. Delete user
        int status = HttpUtil.deleteUser(URI.create(urlUsers+"/2"));
        System.out.println("\nDelete Status = "+status);

        //4. GetAllUsers
        User[] users = HttpUtil.sendGetAll(URI.create(urlUsers));
        System.out.println("\nAll users:");
        for (User i:
             users) {
            System.out.println(i);
        }

        //5. Get user by ID
        int id = 10;
        User user5 = HttpUtil.sendGet(URI.create(String.format("%s/%d", urlUserByID, id)));
        System.out.println("\nUser by id "+id);
        System.out.println(user5);

        //6. Get user bu Username
        String username = "Antonette";
        User[] user6 = HttpUtil.sendGetAll(URI.create(String.format("%s?username=%s",urlUserByUsername,username)));
        System.out.println("\nUser by username "+username);
        System.out.println(user6[0]);

        //Task 2
        HTTPUtil2.getAllCommentsForLastPost(1);

        //Task 3
        int userId = 1;
        List<Task> taskList = HttpUtil3.getAllOpenTasks(userId);
        System.out.println("\nTasks to do for user "+userId);
        for (Task task:
             taskList) {
            System.out.println(task);
        }
    }
    public static User createUser(){
        User user =  new User("Tom Smith","Tom","tomSmith@gmail.com", "+380931235406","site.org");
        user.setAddress(new Address("Lesia Kurbasa","Suite 123", "Kyiv","12345-12345",new Geo(1,1)));
        user.setCompany(new Company("Sony","electronic sales","harness real-time e-markets"));
        return user;
    }
}