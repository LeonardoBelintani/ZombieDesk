package com.zombie_desk.zombiedesk.model;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class User {

    private int id;
    private String username;
    private String password;

    public User() {

    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
