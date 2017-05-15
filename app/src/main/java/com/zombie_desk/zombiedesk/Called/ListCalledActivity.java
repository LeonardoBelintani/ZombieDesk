package com.zombie_desk.zombiedesk.Called;

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

import com.zombie_desk.zombiedesk.Adapters.CalledAdapter;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.Util.Util;
import com.zombie_desk.zombiedesk.model.Called;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListCalledActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_called);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createCalled = new Intent(ListCalledActivity.this,CreateCalledActivity.class);
                startActivity(createCalled);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadCalls();

    }
    public void loadCalls(){
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://lexgalante.esy.es/zombiews/call/find.php");
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
            final List<Called> calls = Util.convertJSONtoCalled(s);
            if(calls != null){
                ArrayAdapter<Called> calledAdapter = new CalledAdapter(ListCalledActivity.this,R.layout.called_item,calls);
                final ListView listCalls = (ListView) findViewById(R.id.listCalls);
                listCalls.setAdapter(calledAdapter);
                listCalls.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        Called call = calls.get(position);
                        Intent intent = new Intent(ListCalledActivity.this, CallUpdateActivity.class);
                        intent.putExtra("call_id", call);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
