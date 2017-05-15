package com.zombie_desk.zombiedesk.Called;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Called;

public class CallUpdateActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_update);

        Intent intent = getIntent();
        Called call = (Called) intent.getSerializableExtra("call_id");
    }
}
