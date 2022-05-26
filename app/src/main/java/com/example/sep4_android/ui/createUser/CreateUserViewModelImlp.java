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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUserViewModelImlp extends AndroidViewModel implements CreateUserViewModel {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    public CreateUserViewModelImlp(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void signUp(String user, String pass) {
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    addRank(task.getResult().getUser().getUid());
                }
                else {
                    Toast.makeText(getApplication().getApplicationContext(), "Account creation failed: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addRank(String UID){
        DatabaseReference userRank = database.getReference("users").child(UID).child("rank");
        userRank.setValue("Admin");

        Toast.makeText(getApplication().getApplicationContext(), "Account creation successful!", Toast.LENGTH_SHORT).show();
    }
}