package com.zombie_desk.zombiedesk.Department;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zombie_desk.zombiedesk.Adapters.DepartmentAdapter;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Department;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department);
        loadDepartment();
    }

    public void loadDepartment(){
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://lexgalante.esy.es/zombiews/department/find.php");
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
            List<Department> departments = Util.convertJSONtoDepartment(s);
            if(departments != null){
                ArrayAdapter<Department> departmentAdapter = new DepartmentAdapter(ListDepartmentActivity.this,R.layout.department_item,departments);
                ListView listDepartments = (ListView) findViewById(R.id.listDepartments);
                listDepartments.setAdapter(departmentAdapter);
            }
        }
    }
}
