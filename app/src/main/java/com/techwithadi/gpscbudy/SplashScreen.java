package com.techwithadi.gpscbudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreen extends AppCompatActivity {
TextView appname;
FirebaseAuth mauth;
Database ob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        appname = findViewById(R.id.txtappname);

        mauth= FirebaseAuth.getInstance();

        Database.firestore = FirebaseFirestore.getInstance();

//        Animation anim = AnimationUtils.loadAnimation(this,R.anim.splash);
//        appname.startAnimation(anim);

        ob= new Database();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mauth.getCurrentUser() != null){



                    ob.loaddata(new ComplateListners() {
                        @Override
                        public void OnSuccess() {

                            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void OnFailure() {
                            Toast.makeText(SplashScreen.this, "Fail to Load Data", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        },4000);

         }
}