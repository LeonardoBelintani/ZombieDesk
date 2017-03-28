package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class CreateUserActivity extends Activity {
    EditText txtLogin;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        this.txtLogin = (EditText) findViewById(R.id.txtLogin);
        this.txtPass = (EditText) findViewById(R.id.txtPass);
    }
}
