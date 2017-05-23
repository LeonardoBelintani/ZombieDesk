package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.zombie_desk.zombiedesk.Called.CreateCalledActivity;
import com.zombie_desk.zombiedesk.Called.ListCalledActivity;
import com.zombie_desk.zombiedesk.Department.ListDepartmentActivity;
import com.zombie_desk.zombiedesk.Employee.ListEmployeeActivity;
import com.zombie_desk.zombiedesk.Facebook.LoginFacebookActivity;
import com.zombie_desk.zombiedesk.Login.LoginActivity;
import com.zombie_desk.zombiedesk.Role.ListRoleActivity;
import com.zombie_desk.zombiedesk.User.CreateUserActivity;
import com.zombie_desk.zombiedesk.User.ListUserActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnHome:
                Intent intentHome = new Intent(this, LoginActivity.class);
                startActivity(intentHome);
                break;
            case R.id.btnCreateUser:
                Intent intentCreateUser = new Intent(this, CreateUserActivity.class);
                startActivity(intentCreateUser);
                break;
            case R.id.btnListarUsuarios:
                Intent intentListUser = new Intent(this, ListUserActivity.class);
                startActivity(intentListUser);
                break;
            case R.id.btnListFuncio:
                Intent intentListEmployee = new Intent(this, ListEmployeeActivity.class);
                startActivity(intentListEmployee);
                break;
            case R.id.btnListRole:
                Intent intentListRole = new Intent(this, ListRoleActivity.class);
                startActivity(intentListRole);
                break;
            case R.id.btnListDepartment:
                Intent intentListDepart = new Intent(this, ListDepartmentActivity.class);
                startActivity(intentListDepart);
                break;
            case R.id.btnListCall:
                Intent intentListCall = new Intent(this, CreateCalledActivity.class);
                startActivity(intentListCall);
                break;
            case R.id.btnLogarFacebook:
                Intent intentFace = new Intent(this, LoginFacebookActivity.class);
                startActivity(intentFace);
                break;
            case R.id.btnInternal:
                Intent intentInterna = new Intent(this, InternalActionsActivity.class);
                startActivity(intentInterna);
                break;
            case R.id.btnAnimationProperty:
                Intent intentAnimationProperty = new Intent(this, AnimationPropertyActivity.class);
                startActivity(intentAnimationProperty);
                break;
            case R.id.btnAnimation:
                Intent intentAnimation = new Intent(this, AnimationActivity.class);
                startActivity(intentAnimation);
                break;
            case R.id.btnInicio:
                Intent intentInicio = new Intent(this, InicioActivity.class);
                startActivity(intentInicio);
                break;
        }
    }
}
