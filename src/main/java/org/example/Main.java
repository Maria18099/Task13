package org.example;

import java.io.IOException;
import java.net.URI;

public class Main {
    private static final String urlUsers = "https://jsonplaceholder.typicode.com/users";
    public static void main(String[] args) throws IOException, InterruptedException {
        User user = HttpUtil.sendPost(URI.create(urlUsers),createUser());
        System.out.println(user);
    }
    public static User createUser(){
        Address address = new Address("Lesia Kurbasa","Suite 123", "Kyiv","12345-12345",new Geo(1,1));
        Company company = new Company("Sony","electronic sales","harness real-time e-markets");
        return new User(11,"Tom Smith","Tom","tomSmith@gmail.com", address, "+380931235406","site.org", company);
    }
}