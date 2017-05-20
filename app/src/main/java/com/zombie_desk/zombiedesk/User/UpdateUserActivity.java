package com.zombie_desk.zombiedesk.User;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.User;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateUserActivity extends AppCompatActivity
{
    EditText editUser;
    EditText editPass;
    EditText editId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        editUser = (EditText) findViewById(R.id.editUsername);
        editPass = (EditText) findViewById(R.id.editPass);
        editId = (EditText) findViewById(R.id.editId);
    }

    public void updateUser(View v){
        try{
            Intent intent = getIntent();
            User user = (User) intent.getSerializableExtra("user_id");

            editUser.setText(user.getUsername());
            editPass.setText(user.getPassword());
            editId.setText(String.valueOf(user.getId()));

            user = new User();
            user.setUsername(editUser.getText().toString());
            user.setPassword(editPass.getText().toString());
            new UploadToMyAPI().execute(user);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
                    (ConnectivityManager) UpdateUserActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
            {
                Toast.makeText(UpdateUserActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(User... params)
        {
            String result;
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/user/update.php");
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
                Intent listEmployee;
                if (Util.getStatusFromJSON(serverResponseMessage).equals("1"))
                {
                    Toast.makeText(UpdateUserActivity.this, "Usuário alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    listEmployee = new Intent(UpdateUserActivity.this, ListUserActivity.class);
                    startActivity(listEmployee);
                } else
                {
                    Toast.makeText(UpdateUserActivity.this, "Falha ao alterar o usuário.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



}
