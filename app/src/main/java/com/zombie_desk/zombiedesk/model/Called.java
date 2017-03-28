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
    private Responsible responsible;
    private Employee employee;

    public Responsible getResponsible() {
        return responsible;
    }

    public void setResponsible(Responsible responsible) {
        this.responsible = responsible;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
            json.put("responsible_id", this.responsible.getId());
            json.put("employee_id", this.employee.getId());
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
            this.responsible = Responsible.findById(json.getInt("reponsible_id"));
            this.employee = Employee.findById(json.getInt("employee_id"));
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
