package com.zombie_desk.zombiedesk.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;

import com.zombie_desk.zombiedesk.model.Called;
import com.zombie_desk.zombiedesk.model.Department;
import com.zombie_desk.zombiedesk.model.Employee;
import com.zombie_desk.zombiedesk.model.Role;
import com.zombie_desk.zombiedesk.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.bitmap;
import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.format;
import static android.R.attr.mediaRouteButtonStyle;
import static android.R.attr.name;
import static android.R.attr.process;

/**
 * Created by Lab. Desenvolvimento on 10/04/2017.
 */

public class Util
{
    /**
     * Lê um arquivo da web via HTTP e converte o mesmo em String.
     *
     * @param inputStream Stream do arquivo local no aplicativo
     * @return O arquivo convertido em String.
     */
    public static String webToString(InputStream inputStream)
    {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null)
            {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return localString;
    }

    public static List<User> convertJSONtoUser(String jsonFile)
    {
        List<User> users = new ArrayList<>();
        try
        {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++)
            {
                User novoUser = new User();
                JSONObject localObj = mainObject.getJSONObject(i);
                String username = localObj.getString("username");
                String password = localObj.getString("password");
                Integer id = localObj.getInt("id");
                novoUser.setUsername(username);
                novoUser.setPassword(password);
                novoUser.setId(id);
                users.add(novoUser);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return users;
    }

    public static String convertUsertoJSON(User user)
    {
        JSONObject mainObject = new JSONObject();
        try
        {
            mainObject.put("id", user.getId());
            mainObject.put("username", user.getUsername());
            mainObject.put("password", user.getPassword());

            return mainObject.toString();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertUsertoJSONLogin(User user)
    {
        JSONObject mainObject = new JSONObject();
        try
        {
            mainObject.put("username", user.getUsername());
            mainObject.put("password", user.getPassword());

            return mainObject.toString();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStatusFromJSON(String json)
    {
        try
        {
            return new JSONObject(json).getString("status");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Employee> convertJSONtoEmployee(String jsonFile)
    {
        List<Employee> employees = new ArrayList<>();
        try
        {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++)
            {
                Employee newEmployee = new Employee();
                JSONObject localObj = mainObject.getJSONObject(i);
                String name = localObj.getString("name");
                String gender = localObj.getString("gender");
                Integer id = localObj.getInt("id");
                Integer user_id = localObj.getInt("user_id");
                Integer role_id = localObj.getInt("role_id");
                Integer department_id = localObj.getInt("department_id");

                newEmployee.setName(name);
                newEmployee.setGender(gender);
                newEmployee.setId(id);
                newEmployee.setUser_id(user_id);
                newEmployee.setRole_id(role_id);
                newEmployee.setDepartment_id(department_id);

                employees.add(newEmployee);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return employees;
    }

    public static String convertEmployeetoJSON(Employee employee)
    {
        JSONObject mainObject = new JSONObject();
        try
        {
            mainObject.put("user_id", employee.getUser_id());
            mainObject.put("role_id", employee.getRole_id());
            mainObject.put("department_id", employee.getDepartment_id());
            mainObject.put("name", employee.getName());
            mainObject.put("gender", employee.getGender());

            return mainObject.toString();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertEmployeetoJSONAlter(Employee employee)
    {
        JSONObject mainObject = new JSONObject();
        try
        {
            mainObject.put("user_id", employee.getUser_id());
            mainObject.put("role_id", employee.getRole_id());
            mainObject.put("department_id", employee.getDepartment_id());
            mainObject.put("name", employee.getName());
            mainObject.put("gender", employee.getGender());
            mainObject.put("id", employee.getId());

            return mainObject.toString();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Role> convertJSONtoRole(String jsonFile)
    {
        List<Role> roles = new ArrayList<>();
        try
        {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++)
            {
                Role newRole = new Role();
                JSONObject localObj = mainObject.getJSONObject(i);
                int id = localObj.getInt("id");
                String description = localObj.getString("description");
                newRole.setId(id);
                newRole.setDescription(description);
                roles.add(newRole);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return roles;
    }

    public static List<Department> convertJSONtoDepartment(String jsonFile)
    {
        List<Department> departments = new ArrayList<>();
        try
        {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++)
            {
                Department newdepartment = new Department();
                JSONObject localObj = mainObject.getJSONObject(i);
                int id = localObj.getInt("id");
                String description = localObj.getString("description");
                newdepartment.setId(id);
                newdepartment.setDescription(description);
                departments.add(newdepartment);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return departments;
    }

    public static List<Called> convertJSONtoCalled(String jsonFile)
    {
        List<Called> calls = new ArrayList<>();
        try
        {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++)
            {
                Called newCall = new Called();
                JSONObject localObj = mainObject.getJSONObject(i);
                int id = localObj.getInt("id");
                String location = localObj.getString("location");
                String priority = localObj.getString("priority");
                String status = localObj.getString("status");
                String reason = localObj.getString("reason");
                newCall.setId(id);
                newCall.setLocale(location);
                newCall.setPriority(priority);
                newCall.setStatus(status);
                newCall.setReason(reason);
                calls.add(newCall);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return calls;
    }

    public static String convertCalledtoJSON(Called called)
    {
        JSONObject mainObject = new JSONObject();
        try
        {
            mainObject.put("clerk_id", called.getResponsible_id());
            mainObject.put("employee_id", called.getEmployee_id());
            mainObject.put("location", called.getLocale());
            mainObject.put("reason", called.getReason());
            mainObject.put("priority", called.getPriority());
            mainObject.put("status", called.getStatus());

            return mainObject.toString();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
