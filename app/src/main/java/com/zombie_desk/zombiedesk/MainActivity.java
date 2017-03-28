package com.zombie_desk.zombiedesk;

import android.app.Activity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zombie_desk.zombiedesk.model.User;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    EditText txtLogin;
    EditText passLogin;
    TextView txtError;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        passLogin = (EditText) findViewById(R.id.passLogin);
        txtError = (TextView) findViewById(R.id.txtError);
    }

    public void Login(View v){
        try{
            user = new User(String.valueOf(this.txtLogin.getText()), String.valueOf(this.passLogin.getText()));
            user.Login();

            if(user.getId() > 0){
                //CONCEDE ACESSO AO SISTEMA
            }
            else{
                //CASO NAO CONSIGA LOGAR AVISAR USANDO txtError
                //txtError.
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
