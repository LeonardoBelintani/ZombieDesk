package com.zombie_desk.zombiedesk.model;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Responsible {
    private int id;
    private String name;
    private char gender;
    private char time_work;
    private int user_id;

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
        this.user_id = user_id;
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

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public static Responsible findById(int id)
    {
        //TODO implementar busca
        return new Responsible();
    }
}
