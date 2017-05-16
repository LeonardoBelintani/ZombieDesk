package com.zombie_desk.zombiedesk.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.User;

public class UpdateUserActivity extends AppCompatActivity
{
    EditText editUser;
    EditText editPass;
    EditText editId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        editUser = (EditText) findViewById(R.id.editUsername);
        editPass = (EditText) findViewById(R.id.editPass);
        editId = (EditText) findViewById(R.id.editId);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user_id");

        editUser.setText(user.getUsername());
        editPass.setText(user.getPassword());
        editId.setText(String.valueOf(user.getId()));
    }
}
