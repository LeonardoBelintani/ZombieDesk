package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.dao.WebService;
import com.zombie_desk.zombiedesk.model.User;
import com.zombie_desk.zombiedesk.util.Util;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateUserActivity extends Activity {
    EditText txtLogin;
    EditText txtPass;
    TextView lblResult;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        this.txtLogin = (EditText) findViewById(R.id.txtLogin);
        this.txtPass = (EditText) findViewById(R.id.txtPass);
        this.lblResult = (TextView) findViewById(R.id.lblResult);
    }

    private class CreateUser extends AsyncTask<Void, Void, User> {
        @Override
        protected User doInBackground(Void... params) {
            try {
                URL url = new URL(WebService.urlUserCreate());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                JSONObject json = new JSONObject();
                json.put("username", user.getUsername());
                json.put("password", user.getPassword());

                DataOutputStream stream = new DataOutputStream(connection.getOutputStream());
                stream.writeBytes(json.toString());
                stream.flush();
                stream.close();

                String response = Util.webToString(connection.getInputStream());
                user.fillUser(response);
            }
            catch(Exception e) {
                System.out.println("Erro no background da classe CREATEUSER");
                e.printStackTrace();
            }

            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            if(user.getId() > 0) {
                lblResult.setText("Usuário cadastrado com sucesso: nº "+user.getId());
            }
            else {
                lblResult.setText("Não foi possivel cadastrar o usuário!");
            }
        }
    }
}
