package com.zombie_desk.zombiedesk.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lab. Leonardo Belintani on 23/03/2017.
 */

public class User implements Serializable
{

    private int id;
    private String username;
    private String password;

    public User() {

    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public User(String password, String username, int id)
    {
        this.password = password;
        this.username = username;
        this.id = id;
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

    /**
     * Recebe uma string e preenche o objeto atual
     * @param jsonString
     */
    public void fillUser(String jsonString){
        try{
            JSONObject json = new JSONObject(jsonString);
            this.id = json.getInt("id");
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
        //TODO implementar busca geral
        return null;
    }

    /**
     * Vai salvar o usuario e retornar um objeto com informacoes do mesmo
      * @return
     */
    public User save()
    {
        //TODO implantar salvar
        //Metodo que vai salvar no webservice a model
        JSONObject json = new JSONObject();
        try {
            json.put("username", this.username);
            json.put("password", this.password);
        }catch(JSONException e){
            json = null;
        }

        return null;
    }
}
