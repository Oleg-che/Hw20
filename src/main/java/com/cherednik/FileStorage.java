package com.cherednik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements Storage {

    private String fileName;
    private List<User> list;
    private int lastId;

    FileStorage(String fileName) {
        this.fileName = fileName;
        writeData(this);
    }

    private String getFileName() {
        return fileName;
    }

    @Override
    public void addUser(User user) {
        FileStorage tmp = readData();
        if (tmp.lastId == 0) {
            tmp.list = new ArrayList<>();
        }
        tmp.lastId++;
        user.setId(tmp.lastId);
        tmp.list.add(user);
        writeData(tmp);
    }

    void addUser(String name, int age) {
        FileStorage tmp = readData();
        User user = new User(name, age);
        if (tmp.lastId == 0) {
            tmp.list = new ArrayList<>();
        }
        tmp.lastId++;
        user.setId(tmp.lastId);
        tmp.list.add(user);
        writeData(tmp);
    }

    @Override
    public void removeAll() {
        FileStorage tmp = readData();
        tmp.lastId = 0;
        tmp.list.clear();
        writeData(tmp);
    }

    @Override
    public void removeUser(int id) {
        FileStorage tmp = readData();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getId() == id) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            tmp.list.remove(index);
        }
        writeData(tmp);
    }

    @Override
    public void removeUserByName(String name) {
        FileStorage tmp = readData();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getName().equalsIgnoreCase(name)) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            tmp.list.remove(index);
        } else {
            System.out.println("There is no User with this name");
        }
        writeData(tmp);
    }

    @Override
    public User getUser(int id) {
        FileStorage tmp = readData();
        int index = -1;
        for (User user : tmp.list) {
            if (user.getId() == id) {
                index = tmp.list.indexOf(user);
            }
        }
        if (index != -1) {
            return tmp.list.get(index);
        } else {
            System.out.println("There is no User with this ID");
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        FileStorage tmp = readData();
        return tmp.list;
    }

    @Override
    public void updateUser(User user) {
        if (user.getId() != 0) {
            FileStorage tmp = readData();
            for (User listUsers : tmp.list) {
                if (listUsers.getId() == user.getId()) {
                    listUsers.setName(user.getName());
                    listUsers.setAge(user.getAge());
                    writeData(tmp);
                }
            }
        }
    }

    private void writeData(FileStorage fileStorage) {
        Gson gson = new GsonBuilder().create();
        String strJson = gson.toJson(fileStorage);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFileName()))) {
            writer.write(strJson);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private FileStorage readData() {
        String strFromJson = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getFileName()))) {
            strFromJson = reader.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(strFromJson, FileStorage.class);
    }

    @Override
    public String toString() {
        FileStorage tmp = readData();
        return "FileStorage {" +
                "list=" +
                tmp.list +
                '}';
    }

}