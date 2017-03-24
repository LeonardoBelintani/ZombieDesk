package com.zombie_desk.zombiedesk.model;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Responsible {
    private int id;
    private String name;
    private char gender;
    private char time_work;
    private User user;

    public Responsible() {
    }

    public Responsible(String name, char gender, char time_work) {
        this.name = name;
        this.gender = gender;
        this.time_work = time_work;
    }

    public Responsible(String name, char gender, char time_work, User user) {
        this.name = name;
        this.gender = gender;
        this.time_work = time_work;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getTime_work() {
        return time_work;
    }

    public void setTime_work(char time_work) {
        this.time_work = time_work;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
