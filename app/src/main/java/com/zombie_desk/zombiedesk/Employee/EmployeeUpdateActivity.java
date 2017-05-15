package com.zombie_desk.zombiedesk.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Called;
import com.zombie_desk.zombiedesk.model.Employee;

/**
 * Created by admin on 15/05/2017.
 */

class EmployeeUpdateActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_update);

        Intent intent = getIntent();
        Employee employee = (Employee) intent.getSerializableExtra("employee_id");
    }
}
