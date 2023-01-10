package com.techwithadi.gpscbudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    Button btnregister;
    EditText edtname, edtemail, edtpass, edtconfpass;
    String name, email, pass, confpass;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    Boolean valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        clicklistener();
    }

    public void gotologin(View view) {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void init(){
        btnregister=findViewById(R.id.btnreg);
        edtname=findViewById(R.id.edtname);
        edtemail=findViewById(R.id.edtmail);
        edtpass=findViewById(R.id.edtpass);
        edtconfpass=findViewById(R.id.edtconfpass);
        progressBar=findViewById(R.id.idPBLoading);
    }

    public Boolean validations(){
        name = edtname.getText().toString();
        email= edtemail.getText().toString();
        pass = edtpass.getText().toString();
        confpass = edtconfpass.getText().toString();

        if(name.isEmpty()){
            edtname.setError("Required");
            valid=false;
        }
        else if(email.isEmpty()){
            edtemail.setError("Required");
            valid=false;
        }
        else if(pass.isEmpty()){
            edtpass.setError("Required");
        }
        else if(confpass.isEmpty() || !pass.equals(confpass)){
            edtconfpass.setError("Password not Match");
            valid=false;
        } else {
            valid = true;
        }
        return valid;
    }
    public void clicklistener(){
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validations()){
                    progressBar.setVisibility(View.VISIBLE);
                    usersignup();
                }


            }
        });
    }
    public void usersignup(){
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Database.createuser(email,name, new ComplateListners() {
                                @Override
                                public void OnSuccess() {

                                    Database.loaddata(new ComplateListners() {
                                        @Override
                                        public void OnSuccess() {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(RegisterActivity.this, "Successfully login", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                        @Override
                                        public void OnFailure() {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(RegisterActivity.this, "Error While Load Data Please Try After Some Time..", Toast.LENGTH_SHORT).show();
                                            edtname.setText("");
                                            edtpass.setText("");
                                            edtemail.setText("");
                                            edtconfpass.setText("");                    }
                                    });


                                }

                                @Override
                                public void OnFailure() {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(RegisterActivity.this, "Error While Creating User", Toast.LENGTH_SHORT).show();
                                    edtname.setText("");
                                    edtpass.setText("");
                                    edtemail.setText("");
                                    edtconfpass.setText("");
                                }
                            });


                        }
                        else {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, "Error While login", Toast.LENGTH_SHORT).show();
                            edtname.setText("");
                            edtpass.setText("");
                            edtemail.setText("");
                            edtconfpass.setText("");
                        }
                    }
                });
    }
}