package com.example.sep4_android.ui.mainActivity;


import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.example.sep4_android.R;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.repositories.RouteRepositoryImpl;
import com.example.sep4_android.ui.login.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivityViewModelImpl extends AndroidViewModel implements MainActivityViewModel {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private RouteRepository repo; //FIXME: Bruges aldrig?
    private Application application; //Gem for observer


    public MainActivityViewModelImpl(Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://sep4-26d6b-default-rtdb.europe-west1.firebasedatabase.app/");
        repo = RouteRepositoryImpl.getInstance(application);
        this.application = application;
    }

    public void hideDefaultNavItems(Menu menu){
        //Hide default graphing items - Kan ikke hide dem by default i xml, så er nød til det med kode
        menu.findItem(R.id.nav_compareLineChartFragment).setVisible(false);
        menu.findItem(R.id.nav_compareBarChartFragment).setVisible(false);
        menu.findItem(R.id.nav_healthInspection).setVisible(false);
        menu.findItem(R.id.nav_frontpageFragment).setVisible(false);
        menu.findItem(R.id.nav_adminsettingsFragment).setVisible(false);
    }


    @Override
    public void DynamicNavigation(Menu navMenu) {
        hideDefaultNavItems(navMenu);
        //Rolecheck (ingen need for at gemme user .getCurrent er singleton og har instans)

        if (mAuth.getCurrentUser() == null) return;
        database.getReference("users").child(mAuth.getCurrentUser().getUid()).child("rank").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists() &&
                        snapshot.getValue() != null &&
                        snapshot.getValue().equals("admin")){
                    navMenu.findItem(R.id.nav_adminsettingsFragment).setVisible(true);
                }
//                navMenu.findItem(R.id.nav_create_room).setVisible(false);
//                navMenu.findItem(R.id.nav_register_device).setVisible(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplication(), "Error while checking your user role", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<Device> getSelectedDeviceLive() {
        return repo.getSelectedDeviceLive();
    }

    public void updateGraphNav(Device device, Menu menu){
        if(device != null){
            menu.findItem(R.id.nav_compareLineChartFragment).setVisible(true);
            menu.findItem(R.id.nav_compareBarChartFragment).setVisible(true);
            menu.findItem(R.id.nav_healthInspection).setVisible(true);
            menu.findItem(R.id.nav_frontpageFragment).setVisible(true);
        }
    }
}