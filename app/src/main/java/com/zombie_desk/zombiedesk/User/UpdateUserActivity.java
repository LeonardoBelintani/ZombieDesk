package com.zombie_desk.zombiedesk.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.User;

public class UpdateUserActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user_id");
    }
}
