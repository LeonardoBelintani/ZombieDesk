package com.zombie_desk.zombiedesk.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try {
            json.put("username", this.username);
            json.put("password", this.password);
        }catch(JSONException e){
            json = null;
        }
        return json;
    }

    public void toUser(JSONObject json){
        try{
            this.username = json.getString("username");
            this.password = json.getString("password");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static User findById(int id)
    {
        //TODO implementar busca
        return new User();
    }

    public static List<User> findAll()
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

    public User Login()
    {
        //TODO
        //Implementar aqui login, fazer webservice retornar um json com id do usuario
        return null;
    }
}
