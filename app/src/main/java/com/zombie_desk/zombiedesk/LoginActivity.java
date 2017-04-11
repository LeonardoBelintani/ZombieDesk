package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zombie_desk.zombiedesk.dao.WebService;
import com.zombie_desk.zombiedesk.model.User;
import com.zombie_desk.zombiedesk.util.Util;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends Activity {
    EditText txtLogin;
    EditText txtPassword;
    TextView lblResult;
    Intent intent;
    User user;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.txtLogin = (EditText) findViewById(R.id.txtLogin);
        this.txtPassword = (EditText) findViewById(R.id.txtPassword);
        this.lblResult = (TextView) findViewById(R.id.lblResult);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void acessar(View v) {
        try {
            this.user = new User(String.valueOf(this.txtPassword.getText()), String.valueOf(this.txtLogin.getText()));
            new UserAcess().execute(user);
            User copy = user;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class UserAcess extends AsyncTask<User, Void, String>
    {
        @Override
        protected String doInBackground(User... params){
            try {
                HttpURLConnection connection;
                URL url = new URL(WebService.urlUserAcess(user));
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                String jSon = Util.webToString(connection.getInputStream());
                return jSon;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String jSon) {
            super.onPostExecute(jSon);
            //Se o webservice não retornar nada, então o usuario nao existe
            if (jSon == null) {
                user.setId(0);
            } else {
                //Preenche o objeto atual
                user.fillUser(jSon);
            }
        }
    }
}
