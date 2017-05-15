package com.zombie_desk.zombiedesk.Employee;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zombie_desk.zombiedesk.Adapters.EmployeeAdapter;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Employee;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListEmployeeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createEmployee = new Intent(ListEmployeeActivity.this,CreateEmployeeActivity.class);
                startActivity(createEmployee);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadEmployees();
    }
    public void loadEmployees(){
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://lexgalante.esy.es/zombiews/employee/find.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                int test = urlConnection.getResponseCode();

                String result = Util.webToString(urlConnection.getInputStream());

                return result;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            final List<Employee> employees = Util.convertJSONtoEmployee(s);
            if(employees != null){
                ArrayAdapter<Employee> employeeAdapter = new EmployeeAdapter(ListEmployeeActivity.this,R.layout.employee_item,employees);
                final ListView listEmployees = (ListView) findViewById(R.id.listEmployees);
                listEmployees.setAdapter(employeeAdapter);
                listEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Employee employee = employees.get(position);
                        Intent intent = new Intent(ListEmployeeActivity.this, EmployeeUpdateActivity.class);
                        intent.putExtra("employee_id", employee);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}










