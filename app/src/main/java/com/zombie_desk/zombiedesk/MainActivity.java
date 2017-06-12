package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zombie_desk.zombiedesk.Called.CreateCalledActivity;
import com.zombie_desk.zombiedesk.Called.ListCalledActivity;
import com.zombie_desk.zombiedesk.Department.ListDepartmentActivity;
import com.zombie_desk.zombiedesk.Employee.ListEmployeeActivity;
import com.zombie_desk.zombiedesk.Facebook.LoginFacebookActivity;
import com.zombie_desk.zombiedesk.Login.InicioActivity;
import com.zombie_desk.zombiedesk.Role.ListRoleActivity;
import com.zombie_desk.zombiedesk.User.CreateUserActivity;
import com.zombie_desk.zombiedesk.User.ListUserActivity;

import java.util.ArrayList;


public class MainActivity extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Menu));
        setListAdapter(adapter);
//        setContentView(R.layout.activity_main);
    }

    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);
        Uri uri;
        Intent intent;

        switch (position)
        {
            //criar usuarios
            case 0:
                Intent intentCreateUser = new Intent(this, CreateUserActivity.class);
                startActivity(intentCreateUser);
                break;

            //listar usuarios
            case 1:
                Intent intentListUser = new Intent(this, ListUserActivity.class);
                startActivity(intentListUser);
                break;

            //listar funcionarios
            case 2:
                Intent intentListEmployee = new Intent(this, ListEmployeeActivity.class);
                startActivity(intentListEmployee);
                break;

            //listar funções
            case 3:
                Intent intentListRole = new Intent(this, ListRoleActivity.class);
//                startActivity(intentListRole);
//                break;

            //listar departamentos
            case 4:
                Intent intentListDepart = new Intent(this, ListDepartmentActivity.class);
                startActivity(intentListDepart);
                break;

            //listar chamados
            case 5:
                Intent intentListCall = new Intent(this, ListCalledActivity.class);
                startActivity(intentListCall);
                break;

            //Interação com aplicações existentes no aparelho
            case 6:
                Intent intentInterna = new Intent(this, InternalActionsActivity.class);
                startActivity(intentInterna);
                break;

            //Animação da propriedade
            case 7:
                Intent intentAnimationProperty = new Intent(this, AnimationPropertyActivity.class);
                startActivity(intentAnimationProperty);
                break;

            //Animação
            case 8:
                Intent intentAnimation = new Intent(this, AnimationActivity.class);
                startActivity(intentAnimation);
                break;

//            //Sair
//            case 9:
//                Intent intentSair = new Intent(this, InicioActivity.class);
//                startActivity(intentSair);
//                break;

            default:
                finish();
        }
    }


//    public void onClick(View v)
//    {
//        switch (v.getId())
//        {
//            case R.id.btnCreateUser:
//                Intent intentCreateUser = new Intent(this, CreateUserActivity.class);
//                startActivity(intentCreateUser);
//                break;
//            case R.id.btnListarUsuarios:
//                Intent intentListUser = new Intent(this, ListUserActivity.class);
//                startActivity(intentListUser);
//                break;
//            case R.id.btnListFuncio:
//                Intent intentListEmployee = new Intent(this, ListEmployeeActivity.class);
//                startActivity(intentListEmployee);
//                break;
//            case R.id.btnListRole:
//                Intent intentListRole = new Intent(this, ListRoleActivity.class);
//                startActivity(intentListRole);
//                break;
//            case R.id.btnListDepartment:
//                Intent intentListDepart = new Intent(this, ListDepartmentActivity.class);
//                startActivity(intentListDepart);
//                break;
//            case R.id.btnListCall:
//                Intent intentListCall = new Intent(this, ListCalledActivity.class);
//                startActivity(intentListCall);
//                break;
//            case R.id.btnInternal:
//                Intent intentInterna = new Intent(this, InternalActionsActivity.class);
//                startActivity(intentInterna);
//                break;
//            case R.id.btnAnimationProperty:
//                Intent intentAnimationProperty = new Intent(this, AnimationPropertyActivity.class);
//                startActivity(intentAnimationProperty);
//                break;
//            case R.id.btnAnimation:
//                Intent intentAnimation = new Intent(this, AnimationActivity.class);
//                startActivity(intentAnimation);
//                break;
//            case R.id.btnSair:
//                Intent intentSair = new Intent(this, InicioActivity.class);
//                startActivity(intentSair);
//                break;
//        }
//    }
}
