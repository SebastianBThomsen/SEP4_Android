package com.example.sep4_android.ui.createUser;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.ui.createRoom.CreateRoomViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUserViewModelImlp extends AndroidViewModel implements CreateUserViewModel {
    FirebaseAuth mAuth;

    public CreateUserViewModelImlp(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
    }








    @Override
    public void signUp(String user, String pass) {
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    System.out.println("User Created");
                    System.out.println(user+" Successful");
                }
                else {
                    System.out.println("User failed");
                    System.out.println(user);
                }
            }
        });
    }
}