package com.example.sep4_android.ui.login;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginViewModelImpl extends ViewModel implements LoginViewModel {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

//    private DataReposetory repo;

    public LoginViewModelImpl() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://sep4-26d6b-default-rtdb.europe-west1.firebasedatabase.app/");
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
        FirebaseUser finalUser = mAuth.getCurrentUser();
        if(finalUser != null){
            System.out.println("her");
            database.getReference("users").child(finalUser.getUid()).child("rank").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() != null){
                        String result = snapshot.getValue().toString();
                        System.out.println(result);
                    } else
                        System.out.println("rank: "+snapshot.getValue());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }



    @Override
    public void checkLoggedIn(Activity ac) {
        FirebaseUser u = mAuth.getCurrentUser();

        if(u != null){
            Toast.makeText(ac, "User already logged in", Toast.LENGTH_LONG).show();
            Intent mainAc = new Intent(ac, MainActivity.class);
            ac.startActivity(mainAc);
        }
            else
            Toast.makeText(ac, "User not logged in", Toast.LENGTH_LONG).show();
    }
}