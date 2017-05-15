package com.zombie_desk.zombiedesk.Facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.zombie_desk.zombiedesk.R;

public class LoginFacebookActivity extends AppCompatActivity
{
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_facebook);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("ID_FB",loginResult.getAccessToken().getUserId());
                redirect(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null){
            redirect(accessToken.getUserId());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void redirect(String ID){
        Intent intent = new Intent(LoginFacebookActivity.this,FriendsActivity.class);
        intent.putExtra("FB_ID",ID);
        startActivity(intent);
    }
}
