package com.zombie_desk.zombiedesk.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.zombie_desk.zombiedesk.MainActivity;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.dao.WebService;
import com.zombie_desk.zombiedesk.model.User;

import java.net.HttpURLConnection;
import java.net.URL;

public class InicioActivity extends Activity
{
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    EditText txtLogin;
    EditText txtPassword;
    TextView lblResult;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_inicio);
        this.txtLogin = (EditText) findViewById(R.id.txtName);
        this.txtPassword = (EditText) findViewById(R.id.txtPassword);
        this.lblResult = (TextView) findViewById(R.id.lblResult);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("ID_FB",loginResult.getAccessToken().getUserId());
                redirect(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null){
            redirect(accessToken.getUserId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void redirect(String ID){
        Intent intent = new Intent(InicioActivity.this,MainActivity.class);
        intent.putExtra("FB_ID",ID);
        startActivity(intent);
    }

    public void Inicio_acessar(View v) {
        try {
            this.user = new User(String.valueOf(this.txtPassword.getText()), String.valueOf(this.txtLogin.getText()));
            new UserAcess().execute(user);

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
                Intent intent = new Intent(InicioActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
