package com.zombie_desk.zombiedesk.model;

import java.util.Date;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Employee {
    private int id;
    private String name;
    private char gender;
    private Date birth;
    private User user;
    private Role role;
    private Department department;

    public Employee() {
    }

    public Employee(String name, char gender, Date birth) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public Employee(String name, char gender, Date birth, User user, Role role, Department department) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.user = user;
        this.role = role;
        this.department = department;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
