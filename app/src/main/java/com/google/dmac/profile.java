package com.google.dmac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class profile extends AppCompatActivity {
public Button update;
public EditText name,status;
public CircleImageView circleview;
private String name1,status1;
 private String currentuser;
 private FirebaseAuth author;
 private DatabaseReference db;
 Firebase fire,userschild,statuschild,namechild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        update=(Button)findViewById(R.id.update);
        name=(EditText)findViewById(R.id.usernameprof);
        status=(EditText)findViewById(R.id.statusprof);
        circleview=(CircleImageView)findViewById(R.id.profile_image);
        //author=FirebaseAuth.getInstance();
       // currentuser=author.getCurrentUser().getUid();
       // db= FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        fire=new Firebase("https://dmac-b92af.firebaseio.com/Users");
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1=name.getText().toString().trim();
                status1=status.getText().toString().trim();
                userschild = fire.child("Usersinfo");
                namechild = userschild.child("Name");
                statuschild =userschild.child("Status");
                //namechild.push().setValue(name1);
                //statuschild.push().setValue(status1);
               // fire.push().setValue(name1);
               // fire.push().setValue(status1);
                namechild.setValue(name1);
                statuschild.setValue(status1);
                Toast.makeText(profile.this,"Successfully Updated!",Toast.LENGTH_LONG).show();


            }
        });


    }
}
