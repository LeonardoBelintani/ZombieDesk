package com.zombie_desk.zombiedesk.Called;

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
import com.zombie_desk.zombiedesk.model.Called;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CreateCalledActivity extends Activity
{
    EditText txtLocation;
    EditText txtReason;
    EditText txtPriority;
    EditText txtStatus;
    TextView lblResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_called);
        txtLocation = (EditText) findViewById(R.id.txtLocation);
        txtReason = (EditText) findViewById(R.id.txtReason);
        txtPriority = (EditText) findViewById(R.id.txtPriority);
        txtStatus = (EditText) findViewById(R.id.txtStatus);
        lblResult = (TextView) findViewById(R.id.lblResult);
    }

    public void cadastrarCall(View v)
    {
        Called called = new Called();
        called.setLocale(txtLocation.getText().toString());
        called.setReason(txtReason.getText().toString());
        called.setPriority(txtPriority.getText().toString());
        called.setStatus(txtStatus.getText().toString());

        new UploadToMyAPI().execute(called);

    }

    private class UploadToMyAPI extends AsyncTask<Called, Void, String>
    {
        boolean isConnected = false;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected void onPreExecute()
        {
            ConnectivityManager cm =
                    (ConnectivityManager) CreateCalledActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
            {
                Toast.makeText(CreateCalledActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(Called... params)
        {
            String result;
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/call/create.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                result = Util.convertCalledtoJSON(params[0]);
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
                Intent listCalled;
                if (Util.getStatusFromJSON(serverResponseMessage).equals("1"))
                {
                    Toast.makeText(CreateCalledActivity.this, "chamado aberto com sucesso!", Toast.LENGTH_SHORT).show();
                    listCalled = new Intent(CreateCalledActivity.this, ListCalledActivity.class);
                    startActivity(listCalled);
                } else
                {
                    Toast.makeText(CreateCalledActivity.this, "Falha ao abrir o chamado. Tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
