package com.zombie_desk.zombiedesk.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.User;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateUserActivity extends Activity
{
    EditText txtLogin;
    EditText txtPass;
    TextView lblResult;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        txtLogin = (EditText) findViewById(R.id.txtName);
        lblResult = (TextView) findViewById(R.id.lblResult);
        txtPass = (EditText) findViewById(R.id.txtGender);
    }

    public void cadastrarUsuario(View v)
    {
        User user = new User();
        user.setUsername(txtLogin.getText().toString());
        user.setPassword(txtPass.getText().toString());
        new UploadToMyAPI().execute(user);

    }

    private class UploadToMyAPI extends AsyncTask<User, Void, String>
    {
        boolean isConnected = false;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected void onPreExecute()
        {
            ConnectivityManager cm =
                    (ConnectivityManager) CreateUserActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
            {
                Toast.makeText(CreateUserActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(User... params)
        {
            String result;
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/user/create.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                result = Util.convertUsertoJSON(params[0]);
                outputStream.writeBytes(result);

                serverResponseCode = urlConnection.getResponseCode();
                serverResponseMessage = Util.webToString(urlConnection.getInputStream());

                outputStream.flush();
                outputStream.close();

            } catch (Exception e)
            {
                Log.e("Error", "Error ", e);
                return null;
            } finally
            {
                if (urlConnection != null)
                {
                    urlConnection.disconnect();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (isConnected)
            {
                Intent listUser;
                if (Util.getStatusFromJSON(serverResponseMessage).equals("1"))
                {
                    Toast.makeText(CreateUserActivity.this, "usuario registrado no Sistema!", Toast.LENGTH_SHORT).show();
                    listUser = new Intent(CreateUserActivity.this, ListUserActivity.class);
                    startActivity(listUser);
                } else
                {
                    Toast.makeText(CreateUserActivity.this, "Falha ao cadastrar o usuario.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
