package com.example.sep4_android.ui.login;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.sep4_android.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.example.sep4_android.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModelImpl extends ViewModel implements LoginViewModel {

    FirebaseAuth mAuth;

//    private DataReposetory repo;

    public LoginViewModelImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(Activity ac, String user, String pass) {
        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent mainAc = new Intent(ac, MainActivity.class);
                            ac.startActivity(mainAc);

                            Toast.makeText(ac.getApplicationContext(), "User logged in", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(LoginViewModelImpl.this, MainActivity.class));
                        } else {
                            Toast.makeText(ac.getApplicationContext(), "Login failed: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(LoginActivity.this, "Login err: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void signUp(Activity ac, String user, String pass) {
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            login(ac, user, pass);
                        } else {
                            Toast.makeText(ac.getApplicationContext(), "Signup failed: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}