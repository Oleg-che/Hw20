package com.cherednik;

public class Main {

    public static void main(String[] args) {
        FileStorage fileStorage = new FileStorage("Text.json");
        User user = new User("Jon", 150);
        User user1 = new User("Corona", 19);
        User user2 = new User("Yoda", 5300);
        fileStorage.addUser("Alex", 22);
        fileStorage.addUser("Genocide", 30);
        fileStorage.addUser(user);
        fileStorage.addUser(user1);
        fileStorage.addUser(user2);
        System.out.println(fileStorage);
        System.out.println(fileStorage.getAllUsers());
        System.out.println(fileStorage.getUser(3));
        fileStorage.updateUser(new User(5, "Yoda", 530));
        fileStorage.removeUserByName("Corona");
        System.out.println(fileStorage);
        fileStorage.removeUser(3);
        System.out.println(fileStorage);
        //fileStorage.removeAll();
        //System.out.println(fileStorage);

    }
}
