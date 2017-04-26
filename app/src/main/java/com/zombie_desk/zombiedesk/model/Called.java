package com.zombie_desk.zombiedesk.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Lab. Desenvolvimento on 27/03/2017.
 */

public class Called {
    private int id;
    private String locale;
    private String motivo;
    private String priority;
    private String status;
    private int responsible_id;
    private int employee_id;

    public int getResponsible_id()
    {
        return responsible_id;
    }

    public void setResponsible_id(int responsible_id)
    {
        this.responsible_id = responsible_id;
    }

    public int getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(int employee_id)
    {
        this.employee_id = employee_id;
    }

    public Called() {
    }

    public Called(String locale, String motivo, String priority, String status) {
        this.locale = locale;
        this.motivo = motivo;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try {
            json.put("locale", this.locale);
            json.put("motivo", this.motivo);
            json.put("priority", this.priority);
            json.put("status", this.status);
            json.put("responsible_id", this.responsible_id);
            json.put("employee_id", this.employee_id);
        }catch(JSONException e){
            json = null;
        }
        return json;
    }

    public void toCall(JSONObject json){
        try{
            this.locale = json.getString("locale");
            this.motivo = json.getString("motivo");
            this.priority = json.getString("priority");
            this.status = json.getString("status");
            this.responsible_id = json.getInt("reponsible_id");
            this.employee_id = json.getInt("employee_id");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    public static Called findById(int id)
    {
        //TODO implementar busca
        return new Called();
    }

    public static List<Called> findAll()
    {
        //TODO
        //Implementar pesquisa de todos os chamados
        return null;
    }

    public Boolean save(JSONObject json)
    {
        //TODO
        //Metodo que vai salvar no webservice a model

        return true;
    }
}
