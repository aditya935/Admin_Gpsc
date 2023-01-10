package com.techwithadi.gpscbudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    //Initialize Variable
    LinearLayout regbtn;
    Button btnlogin;
    EditText email,pass;
    boolean status;
    String txtemail, txtpass;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mauth;
    LinearLayout gbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        asignver(); //Assign Veriables
        clicklistner(); // All Click Listners here

        mauth=FirebaseAuth.getInstance();

    }
    public void asignver(){
        regbtn= findViewById(R.id.regbtn);
        btnlogin= findViewById(R.id.btnlogin);
        email= findViewById(R.id.edtemail);
        pass= findViewById(R.id.edtpass);
        gbtn=findViewById(R.id.gbtn);


    }

    public boolean validation(){
        txtemail= email.getText().toString();
        txtpass= pass.getText().toString();
        if (txtemail.isEmpty()){
            status=false;
            email.setError("Please Enter Email");
        }else if(txtpass.isEmpty()){
            status=false;
            pass.setError("Please Enter Password");
        } else {
            status=true;
        }
        return status;
    };

    public void clicklistner(){
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()){

                    mauth.signInWithEmailAndPassword(txtemail,txtpass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        Database.loaddata(new ComplateListners() {
                                            @Override
                                            public void OnSuccess() {
                                                Toast.makeText(LoginActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void OnFailure() {

                                            }
                                        });



                                    }
                                    else {
                                        email.setText("");
                                        pass.setText("");
                                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}


