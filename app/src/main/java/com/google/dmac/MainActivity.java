package com.google.dmac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {
Button old,user;
EditText email1,password1;
private FirebaseAuth auth;
Boolean valid,good;
String email,password;
private FirebaseAuth.AuthStateListener authlisten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        old=(Button)findViewById(R.id.login);
        user =(Button)findViewById(R.id.info);
        email1=(EditText)findViewById(R.id.username);
        password1=(EditText)findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        authlisten= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(auth.getCurrentUser()!=null){
                    startActivity(new Intent(MainActivity.this,Account.class));
                }

            }
        };
        old.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 signin();

            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i1= new Intent(MainActivity.this,Userinfo.class);
              startActivity(i1);
            }
        });
    }
   protected void onStart()
    {super.onStart();
    auth.addAuthStateListener(authlisten);

    }
    private void signin() {
         email = email1.getText().toString();
         password = password1.getText().toString();
          good= validate();
      if(good)
      {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Sign in Failed", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    }
    public Boolean validate(){
        valid=true;
        if (TextUtils.isEmpty(email)) {
            email1.setError("Required Field");
            valid = false;
        }
        if (TextUtils.isEmpty(password)) {
            password1.setError("Required Field");
            valid = false;
        }
        return valid;
    }




}
//if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
    //    Toast.makeText(MainActivity.this, "***Required Fields", Toast.LENGTH_LONG).show();
     //   }