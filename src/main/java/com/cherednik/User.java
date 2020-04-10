package com.cherednik;

class User {

    private int id;

    private String name;

    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    void setId(int id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }


    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
