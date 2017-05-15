package com.zombie_desk.zombiedesk.Employee;

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
import com.zombie_desk.zombiedesk.model.Employee;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Exchanger;

public class CreateEmployeeActivity extends Activity
{
    EditText txtName;
    EditText txtGender;
    TextView lblResult;
    EditText txtBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        txtName = (EditText) findViewById(R.id.txtName);
        txtGender = (EditText) findViewById(R.id.txtGender);
        txtBirth = (EditText) findViewById(R.id.txtBirth);
        lblResult = (TextView) findViewById(R.id.lblResult);
    }
    public void cadastrarEmployee(View v)
    {
        try
        {
            Employee employee = new Employee();
            employee.setName(txtName.getText().toString());
            employee.setGender(txtGender.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            employee.setBirth(sdf.parse(txtBirth.getText().toString()));
            new UploadToMyAPI().execute(employee);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
    }

    private class UploadToMyAPI extends AsyncTask<Employee, Void, String>
    {
        boolean isConnected = false;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected void onPreExecute()
        {
            ConnectivityManager cm =
                    (ConnectivityManager) CreateEmployeeActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
            {
                Toast.makeText(CreateEmployeeActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(Employee... params)
        {
            String result;
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/employee/create.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                result = Util.convertEmployeetoJSON(params[0]);
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
                    Toast.makeText(CreateEmployeeActivity.this, "funcionario registrado no Sistema!", Toast.LENGTH_SHORT).show();
                    listEmployee = new Intent(CreateEmployeeActivity.this, ListEmployeeActivity.class);
                    startActivity(listEmployee);
                } else
                {
                    Toast.makeText(CreateEmployeeActivity.this, "Falha ao cadastrar o funcionario.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
