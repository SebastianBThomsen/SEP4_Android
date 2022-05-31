package com.example.sep4_android.ui.createUser;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateUserViewModelImlp extends AndroidViewModel implements CreateUserViewModel {
    private final FirebaseAuth mAuth;
    private final FirebaseDatabase database;

    public CreateUserViewModelImlp(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://sep4-26d6b-default-rtdb.europe-west1.firebasedatabase.app/");
    }

    //Admin = 1, User = 0;
    @Override
    public void signUp(String user, String pass, int rank) {
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    addRank(task.getResult().getUser().getUid(), rank);
                } else {
                    Toast.makeText(getApplication().getApplicationContext(), "Account creation failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addRank(String UID, int rank) {
        DatabaseReference userRank = database.getReference("users").child(UID).child("rank");

        if (rank == 1) {
            userRank.setValue("admin").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplication().getApplicationContext(), "Account creation successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Account creation failed! Couldn't add rank! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            userRank.setValue("user").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplication().getApplicationContext(), "Account creation successful!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication().getApplicationContext(), "Account creation failed! Couldn't add rank! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}