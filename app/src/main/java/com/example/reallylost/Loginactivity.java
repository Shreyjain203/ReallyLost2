package com.example.reallylost;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Loginactivity extends Activity {
    boolean status = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        ImageButton imguser = findViewById(R.id.user);
        ImageButton imgadmin =findViewById(R.id.admin);


        imgadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loginactivity.this,adminlogin.class );
                startActivity(i);
            }
        });

        imguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Loginactivity.this,user.class );
                startActivity(i);



                    }
                });
            }



}
