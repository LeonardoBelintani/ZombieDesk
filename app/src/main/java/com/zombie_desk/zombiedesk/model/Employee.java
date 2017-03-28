package com.zombie_desk.zombiedesk.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lab. Desenvolvimento on 23/03/2017.
 */

public class Employee {
    private int id;
    private String name;
    private String gender;
    private Date birth;
    private User user;
    private Role role;
    private Department department;

    public Employee() {
    }

    public Employee(String name, String gender, Date birth) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }

    public Employee(String name, String gender, Date birth, User user, Role role, Department department) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try {
            json.put("name", this.name);
            json.put("gender", this.gender);
            json.put("birth", this.birth);
            json.put("user_id", this.user.getId());
            json.put("role_id", this.role.getId());
            json.put("department_id", this.department.getId());
        }catch(JSONException e){
            json = null;
        }
        return json;
    }

    public void toEmployee(JSONObject json){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            this.name = json.getString("name");
            this.gender = json.getString("gender");
            this.birth = formatter.parse(json.getString("birth"));
            this.user = User.findById(json.getInt("user_id"));
            this.role = Role.findById(json.getInt("role_id"));
            this.department = Department.findById(json.getInt("password"));
        }catch(JSONException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Employee findById(int id)
    {
        //TODO implementar busca
        return new Employee();
    }

    public static List<Employee> findAll()
    {
        //TODO implementar busca
        return null;
    }

    public Boolean save(JSONObject json)
    {
        //TODO
        //Metodo que vai salvar no webservice a model
        return true;
    }


}
