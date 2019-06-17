package com.google.dmac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.regex.Pattern;

public class Userinfo extends AppCompatActivity {
    Button register1;
    EditText usernamer1, emailr1, phoner1, passwordr1;
    private FirebaseAuth auth;
    String username, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        register1 = (Button) findViewById(R.id.Registerr);
        usernamer1 = (EditText) findViewById(R.id.usernamer);
        emailr1 = (EditText) findViewById(R.id.emailr);
        phoner1 = (EditText) findViewById(R.id.phoner);
        passwordr1 = (EditText) findViewById(R.id.passwordr);
        auth = FirebaseAuth.getInstance();
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });

    }

    private void registeruser() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "Enter the correct details", Toast.LENGTH_LONG).show();
        } else

            onsuccess();

    }

    public void initialize() {
        username = usernamer1.getText().toString().trim();
        email = emailr1.getText().toString().trim();
        phone = phoner1.getText().toString().trim();
        password = passwordr1.getText().toString().trim();
    }

    public boolean validate() {
        boolean valid = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailr1.setError(" Enter valid email ");
            valid = false;
        }
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches() || phone.length() != 10) {
            phoner1.setError(" Enter valid phone no");
            valid = false;
        }
        if (password.isEmpty()) {
            passwordr1.setError("Enter valid password");
            valid = false;
        }
        if (username.isEmpty()) {
            usernamer1.setError("Enter the username");
            valid = false;
        }

        return valid;
    }
    public void checkemailexists(){
        auth.fetchSignInMethodsForEmail(emailr1.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                        boolean check =!task.getResult().getSignInMethods().isEmpty();
                        if(check)
                            Toast.makeText(getApplicationContext(),"Email Already Exists!",Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void onsuccess() {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Userinfo.this, MainActivity.class);
                    startActivity(i2);
                }
                 else
                        checkemailexists();

                }
            }
        );

    }


}




