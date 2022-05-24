package com.example.sep4_android.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sep4_android.MainActivity;
import com.example.sep4_android.R;
import com.example.sep4_android.model.persistence.entities.FirebaseDAO;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity  {
    FirebaseAuth mAuth;
    Button btn_login, btn_signUp;
    EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // btn_googleLogin = findViewById(R.id.GoogleLogin);
        btn_login = findViewById(R.id.btn_login);
        btn_signUp = findViewById(R.id.btn_SignUp);
        editTextEmail = findViewById(R.id.tv_email);
        editTextPassword = findViewById(R.id.tv_password);
        mAuth = FirebaseDAO.getmAuth();

        btn_login.setOnClickListener(view -> {
            loginUser();
        });

        /*
        btn_signUp.setOnClickListener(view -> {
            startActivity(new Intent (Login.this, SignUp.class));
            Log.e("OnClickListener sign up", "onCreate: " );
        });
        */



    }


    private void loginUser() {
        System.out.println("Dillererr");
        String Email,Password;
        Email = editTextEmail.getText().toString();
        Password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(Email)){
            editTextEmail.setError("Email cant be empty");
            editTextEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(Password))
        {
            editTextPassword.setError("Password cant be empty");
            editTextEmail.requestFocus();
        }
        else
            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    Log.e("Login", Email);
                    Log.e("Login", Password);
                    if (task.isSuccessful()){
                        Log.e("Login", "On Completeeetete");
                        Toast.makeText(Login.this, "User Login successfull", Toast.LENGTH_LONG).show();
                        startActivity(new Intent (Login.this, MainActivity.class));
                    }else{
                        Log.e("Login", "On fail Completeeetete" + task.getException().getMessage());
                        Toast.makeText(Login.this, "User Login Failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
    }




    //On click skifter view her, og det gør den ved en switch, Kig startActivity




}


