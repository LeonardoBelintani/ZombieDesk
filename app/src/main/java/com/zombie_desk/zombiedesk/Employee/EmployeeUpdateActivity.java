package com.zombie_desk.zombiedesk.Employee;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Called;
import com.zombie_desk.zombiedesk.model.Employee;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by admin on 15/05/2017.
 */

public class EmployeeUpdateActivity extends AppCompatActivity
{
    EditText editNome;
    EditText editGenero;
    EditText editID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_update);

        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editID = (EditText) findViewById(R.id.editID);
    }

    public void updateEmployee(View v){
        try{
            Intent intent = getIntent();
            Employee employee = (Employee) intent.getSerializableExtra("employee_id");

            editNome.setText(employee.getName());
            editGenero.setText(employee.getGender());
            editID.setText(String.valueOf(employee.getId()));

            employee = new Employee();
            employee.setName(editNome.getText().toString());
            employee.setGender(editGenero.getText().toString());
            new UploadToMyAPI().execute(employee);

        }catch (Exception e)
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
                    (ConnectivityManager) EmployeeUpdateActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if (!isConnected)
            {
                Toast.makeText(EmployeeUpdateActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(Employee... params)
        {
            String result;
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/employee/update.php");
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
                    Toast.makeText(EmployeeUpdateActivity.this, "funcionário alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    listEmployee = new Intent(EmployeeUpdateActivity.this, ListEmployeeActivity.class);
                    startActivity(listEmployee);
                } else
                {
                    Toast.makeText(EmployeeUpdateActivity.this, "Falha ao alterar o funcionário.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
