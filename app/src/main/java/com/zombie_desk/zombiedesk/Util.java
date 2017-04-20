package com.zombie_desk.zombiedesk;

import com.zombie_desk.zombiedesk.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lab. Desenvolvimento on 10/04/2017.
 */

public class Util {
    /**
     *Lê um arquivo da web via HTTP e converte o mesmo em String.
     *@param inputStream Stream do arquivo local no aplicativo
     *@return O arquivo convertido em String.
     */
    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static List<User> convertJSONtoUser(String jsonFile){
        List<User> users = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(jsonFile);

            for(int i = 0; i < mainObject.length(); i++){
                User novoUser = new User();
                JSONObject localObj = mainObject.getJSONObject(i);
                String username = localObj.getString("username");
                String password = localObj.getString("password");
                novoUser.setUsername(username);
                novoUser.setPassword(password);
                users.add(novoUser);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}
