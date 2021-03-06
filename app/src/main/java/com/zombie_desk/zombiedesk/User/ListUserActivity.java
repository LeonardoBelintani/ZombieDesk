package com.zombie_desk.zombiedesk.User;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zombie_desk.zombiedesk.Adapters.UserAdapter;
import com.zombie_desk.zombiedesk.Called.CreateCalledActivity;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.User;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListUserActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUser = new Intent(ListUserActivity.this,CreateUserActivity.class);
                startActivity(createUser);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadUsers();
    }

    public void loadUsers(){
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://lexgalante.esy.es/zombiews/user/find.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

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
            final List<User> users = Util.convertJSONtoUser(s);
            if(users != null){
                ArrayAdapter<User> userAdapter = new UserAdapter(ListUserActivity.this,R.layout.user_item,users);
                final ListView listUser = (ListView) findViewById(R.id.listUsers);
                listUser.setAdapter(userAdapter);
                listUser.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        User user = users.get(position);
                        Intent intent = new Intent(ListUserActivity.this, UpdateUserActivity.class);
                        intent.putExtra("user_id", user);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
