package com.example.messagingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    private EditText signUpUserName,signUpPassword,logInUserName,logInPassword;
    private Button signUpButton,logInButton;
    public void signUpTap(View view){
        ParseUser appUser=new ParseUser();
        appUser.setUsername(signUpUserName.getText().toString());
        appUser.setPassword(signUpPassword.getText().toString());
        appUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,UsersList.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void logInTap(View view){
        ParseUser.logInInBackground(logInUserName.getText().toString(), logInPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null && e==null){
                    Toast.makeText(MainActivity.this, "LoggedIn", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,UsersList.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        signUpButton=findViewById(R.id.signUpButton);
        signUpUserName=findViewById(R.id.signUpUserName);
        signUpPassword=findViewById(R.id.signUpPassword);
        
        
        logInButton=findViewById(R.id.logInButton);
        logInUserName=findViewById(R.id.logInUserName);
        logInPassword=findViewById(R.id.logInPassword);


    }
}
