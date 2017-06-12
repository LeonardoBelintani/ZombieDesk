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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.Adapters.DepartmentAdapter;
import com.zombie_desk.zombiedesk.Adapters.RoleAdapter;
import com.zombie_desk.zombiedesk.Adapters.UserAdapter;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Department;
import com.zombie_desk.zombiedesk.model.Employee;
import com.zombie_desk.zombiedesk.model.Role;
import com.zombie_desk.zombiedesk.model.User;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CreateEmployeeActivity extends Activity
{
    EditText editName;
    EditText editGender;
    TextView lblResult;
    Spinner spinnerUser;
    Spinner spinnerRole;
    Spinner spinnerDepartment;
    boolean isConnected = false;
    int serverResponseCode;
    String serverResponseMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        editName = (EditText) findViewById(R.id.editName);
        editGender = (EditText) findViewById(R.id.editGender);
        lblResult = (TextView) findViewById(R.id.lblResult);
        spinnerUser = (Spinner) findViewById(R.id.spinnerUser);
        spinnerRole = (Spinner) findViewById(R.id.spinnerRole);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
        loadUsers();
        loadRoles();
        loadDepartments();
    }
    public void cadastrarEmployee(View v)
    {
        try
        {
            Employee employee = new Employee();
            employee.setName(editName.getText().toString());
            employee.setGender(editGender.getText().toString());
            Long idUser = spinnerUser.getSelectedItemId();
            Long idRole = spinnerRole.getSelectedItemId();
            Long idDepartment = spinnerDepartment.getSelectedItemId();
            employee.setUser_id(idUser.intValue());
            employee.setRole_id(idRole.intValue());
            employee.setDepartment_id(idDepartment.intValue());

            new UploadToMyAPI().execute(employee);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private class UploadToMyAPI extends AsyncTask<Employee, Void, String>
    {
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
                try
                {
                    Intent listEmployee;
                    String result = Util.getStatusFromJSON(serverResponseMessage);
                    if (result.equals("1"))
                    {
                        Toast.makeText(CreateEmployeeActivity.this, "Funcionário registrado no Sistema!", Toast.LENGTH_SHORT).show();
                        listEmployee = new Intent(CreateEmployeeActivity.this, ListEmployeeActivity.class);
                        startActivity(listEmployee);
                    } else
                    {
                        Toast.makeText(CreateEmployeeActivity.this, "Falha ao cadastrar o funcionário.", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadUsers()
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
                URL url = new URL("http://lexgalante.esy.es/zombiews/user/find.php");
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
                Log.e("Error", "Error ", e);
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
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (isConnected)
            {
                List<User> users = Util.convertJSONtoUser(s);
                UserAdapter userAdapter = new UserAdapter(CreateEmployeeActivity.this, R.layout.user_item, users);
                userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerUser.setAdapter(userAdapter);

            }
        }
    }

    public void loadRoles()
    {
        new DownloadFromMyAPI2().execute();
    }

    private class DownloadFromMyAPI2 extends AsyncTask<Void, Void, String>
    {
        protected String doInBackground(Void... params)
        {
            String result = "";
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/role/find.php");
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
                Log.e("Error", "Error ", e);
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
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (isConnected)
            {
                List<Role> roles = Util.convertJSONtoRole(s);
                RoleAdapter roleAdapter = new RoleAdapter(CreateEmployeeActivity.this, R.layout.role_item, roles);
                roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerRole.setAdapter(roleAdapter);

            }
        }
    }

    public void loadDepartments()
    {
        new DownloadFromMyAPI3().execute();
    }

    private class DownloadFromMyAPI3 extends AsyncTask<Void, Void, String>
    {
        protected String doInBackground(Void... params)
        {
            String result = "";
            HttpURLConnection urlConnection = null;
            try
            {
                URL url = new URL("http://lexgalante.esy.es/zombiews/department/find.php");
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
                Log.e("Error", "Error ", e);
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
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if (isConnected)
            {
                List<Department> departments = Util.convertJSONtoDepartment(s);
                DepartmentAdapter departmentAdapter = new DepartmentAdapter(CreateEmployeeActivity.this, R.layout.department_item, departments);
                departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDepartment.setAdapter(departmentAdapter);

            }
        }
    }
}
