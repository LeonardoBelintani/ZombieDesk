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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.Adapters.CalledAdapter;
import com.zombie_desk.zombiedesk.Adapters.EmployeeAdapter;
import com.zombie_desk.zombiedesk.MainActivity;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Called;
import com.zombie_desk.zombiedesk.model.Employee;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CreateCalledActivity extends Activity
{
    EditText txtLocation;
    EditText txtReason;
    EditText txtPriority;
    TextView lblResult;
    Spinner spinnerStatus;
    Spinner spinnerResponsible;
    Spinner spinnerEmployee;
    boolean isConnected = false;
    int serverResponseCode;
    String serverResponseMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_called);
        txtLocation = (EditText) findViewById(R.id.txtLocation);
        txtReason = (EditText) findViewById(R.id.txtReason);
        txtPriority = (EditText) findViewById(R.id.txtPriority);
        lblResult = (TextView) findViewById(R.id.lblResult);
        spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);
        spinnerResponsible = (Spinner) findViewById(R.id.spinnerResponsible);
        spinnerEmployee = (Spinner) findViewById(R.id.spinnerEmployee);
        loadCalls();
    }

    public void cadastrarCall(View v)
    {
        Called called = new Called();
        called.setLocale(txtLocation.getText().toString());
        called.setReason(txtReason.getText().toString());
        called.setPriority(txtPriority.getText().toString());
        called.setStatus(spinnerStatus.getSelectedItem().toString());
        Long idEmployee = spinnerEmployee.getSelectedItemId();
        Long idResponsible = spinnerResponsible.getSelectedItemId();
        called.setEmployee_id(idEmployee.intValue());
        called.setResponsible_id(idResponsible.intValue());

        new UploadToMyAPI().execute(called);
    }

    private class UploadToMyAPI extends AsyncTask<Called, Void, String>
    {
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
                try
                {
                    Intent listCalls;
                    if (serverResponseMessage != null)
                    {
                        Toast.makeText(CreateCalledActivity.this, "Chamado registrado no Sistema!", Toast.LENGTH_SHORT).show();
                        listCalls = new Intent(CreateCalledActivity.this, ListCalledActivity.class);
                        startActivity(listCalls);
                    } else
                    {
                        Toast.makeText(CreateCalledActivity.this, "Falha ao abrir o chamado. Tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void back(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void loadCalls()
    {
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String>
    {
        protected String doInBackground(Void... params)
        {
            String result = "";
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/employee/find.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.getResponseCode();

                result = Util.webToString(urlConnection.getInputStream());
            } catch (Exception e)
            {
                Log.e("FError", "Error ", e);
                e.printStackTrace();
            } finally
            {
                if (urlConnection != null)
                {
                    urlConnection.disconnect();
                }
                return result;
            }
        }

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
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (isConnected)
            {
                List<Employee> employees = Util.convertJSONtoEmployee(s);
                EmployeeAdapter employeeAdapter = new EmployeeAdapter(CreateCalledActivity.this, R.layout.employee_item, employees);
                employeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEmployee.setAdapter(employeeAdapter);

            }
        }
    }
}
