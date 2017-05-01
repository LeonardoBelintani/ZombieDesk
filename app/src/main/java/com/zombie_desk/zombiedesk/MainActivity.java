package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zombie_desk.zombiedesk.Called.ListCalledActivity;
import com.zombie_desk.zombiedesk.Department.ListDepartmentActivity;
import com.zombie_desk.zombiedesk.Employee.ListEmployeeActivity;
import com.zombie_desk.zombiedesk.Login.LoginActivity;
import com.zombie_desk.zombiedesk.Role.ListRoleActivity;
import com.zombie_desk.zombiedesk.User.CreateUserActivity;
import com.zombie_desk.zombiedesk.User.ListUserActivity;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void createUser(View v)
    {
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
    }

    public void ListarUsuarios(View v)
    {
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }
    public void ListarFuncionarios(View v)
    {
        Intent intent = new Intent(this, ListEmployeeActivity.class);
        startActivity(intent);
    }
    public void ListarRoles(View v)
    {
        Intent intent = new Intent(this, ListRoleActivity.class);
        startActivity(intent);
    }
    public void ListarDepartamentos(View v)
    {
        Intent intent = new Intent(this, ListDepartmentActivity.class);
        startActivity(intent);
    }
    public void ListarChamados(View v)
    {
        Intent intent = new Intent(this, ListCalledActivity.class);
        startActivity(intent);
    }
}
