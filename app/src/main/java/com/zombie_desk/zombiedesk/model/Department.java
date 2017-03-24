package com.zombie_desk.zombiedesk.model;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Department {
    private int id;
    private String description;
    private String locale;

    public Department() {
    }

    public Department(String description, String locale) {
        this.description = description;
        this.locale = locale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
