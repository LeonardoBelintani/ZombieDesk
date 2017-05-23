package com.zombie_desk.zombiedesk.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Employee implements Serializable
{
    private int id;
    private String name;
    private String gender;
    private Date birth;
    private int user_id;
    private int role_id;
    private int department_id;

    public Employee() {
    }

    public Employee(int id, String name, String gender, Date birth, int user_id, int role_id, int department_id)
    {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.user_id = user_id;
        this.role_id = role_id;
        this.department_id = department_id;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public Date getBirth()
    {
        return birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    public int getUser_id()
    {
        return user_id;
    }

    public void setUser_id(int user_id)
    {
        this.user_id = user_id;
    }

    public int getRole_id()
    {
        return role_id;
    }

    public void setRole_id(int role_id)
    {
        this.role_id = role_id;
    }

    public int getDepartment_id()
    {
        return department_id;
    }

    public void setDepartment_id(int department_id)
    {
        this.department_id = department_id;
    }

    public Employee(String name, String gender, Date birth) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

}
