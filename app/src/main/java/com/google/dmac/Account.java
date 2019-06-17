package com.google.dmac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Account extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout dl;
    private ActionBarDrawerToggle mt;
 //public static TextView resultanswer;
    FirebaseAuth fire;
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mt.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        //resultanswer=(TextView)findViewById(R.id.resultans);
        dl = (DrawerLayout) findViewById(R.id.drawer);
        mt = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(mt);
        mt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nv = (NavigationView) findViewById(R.id.nav);
        nv.setNavigationItemSelectedListener(Account.this);



    }
  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
        //it does nothing
        Toast.makeText(getApplicationContext(),"Please log in again",Toast.LENGTH_LONG).show();
    }*/


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id=Item.getItemId();
        if(id==R.id.profile1)
        {
            Intent i= new Intent(Account.this,profile.class);
            startActivity(i);
        }
        if(id==R.id.voucher1)
        {
            Intent i1 =new Intent(Account.this,voucher.class);
            startActivity(i1);
        }
        if(id==R.id.scanqr)
        {
            Intent i2 =new Intent(Account.this,scanning.class);
            startActivity(i2);
        }
        if(id==R.id.logout)
        {
           FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Account.this,MainActivity.class));
            finish();
        }
        return false;
    }
    }

