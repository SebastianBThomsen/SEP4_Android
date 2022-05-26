package com.example.sep4_android;


import android.app.Application;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityViewModelImpl extends AndroidViewModel implements MainActivityViewModel {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private RouteRepository repo; //FIXME: Bruges aldrig?

    public MainActivityViewModelImpl(Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://sep4-26d6b-default-rtdb.europe-west1.firebasedatabase.app/");
        repo = RouteRepositoryImpl.getInstance(application);
    }


    @Override
    public void DynamicNavigation(Menu navMenu) {
        //Rolecheck (ingen need for at gemme user .getCurrent er singleton og har instans)

        /* FIXME: Dette er udkommenteret, pga. bug, hvor Appen ikke kan åbnes efter man lige har installeret appen? :O (Ingen user cache)
        database.getReference("users").child(mAuth.getCurrentUser().getUid()).child("rank").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue().equals("admin")) return;

                navMenu.findItem(R.id.nav_adminsettingsFragment).setVisible(false);
//                navMenu.findItem(R.id.nav_create_room).setVisible(false);
//                navMenu.findItem(R.id.nav_register_device).setVisible(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication().getApplicationContext(), "Error while checking your user role", Toast.LENGTH_SHORT).show();
            }
        });

         */
    }
}