package com.zombie_desk.zombiedesk.Called;

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

import com.zombie_desk.zombiedesk.MainActivity;
import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Called;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailsCallActivity extends AppCompatActivity
{
    EditText editLocale, editPriority, editReason, editStatus, editCallId;
    Called call;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_call);

        editLocale = (EditText) findViewById(R.id.editLocale);
        editPriority = (EditText) findViewById(R.id.editPriority);
        editReason = (EditText) findViewById(R.id.editReason);
        editStatus = (EditText) findViewById(R.id.editStatus);
        editCallId = (EditText) findViewById(R.id.editCallId);

        editLocale.setEnabled(false);
        editPriority.setEnabled(false);
        editReason.setEnabled(false);
        editStatus.setEnabled(false);
        editCallId.setEnabled(false);

        Intent intent = getIntent();
        call = (Called) intent.getSerializableExtra("call");

        editLocale.setText(call.getLocale());
        editPriority.setText(call.getPriority());
        editReason.setText(call.getReason());
        editStatus.setText(call.getStatus());
        editCallId.setText(String.valueOf(call.getId()));
    }
    public void backToListCall(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
