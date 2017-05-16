package com.zombie_desk.zombiedesk.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.zombie_desk.zombiedesk.R;
import com.zombie_desk.zombiedesk.model.Called;
import com.zombie_desk.zombiedesk.model.Employee;

/**
 * Created by admin on 15/05/2017.
 */

public class EmployeeUpdateActivity extends AppCompatActivity
{
    EditText editNome;
    EditText editGenero;
    EditText editID;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_update);

        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editID = (EditText) findViewById(R.id.editID);

        Intent intent = getIntent();
        Employee employee = (Employee) intent.getSerializableExtra("employee_id");

        editNome.setText(employee.getName());
        editGenero.setText(employee.getGender());
        editID.setText(String.valueOf(employee.getId()));
    }
}
