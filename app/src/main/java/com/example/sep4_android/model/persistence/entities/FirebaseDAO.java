package com.example.sep4_android.model.persistence.entities;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDAO {
    private static FirebaseDAO single_instance = null;

    private static FirebaseDatabase mDatabase;
    private static FirebaseAuth mAuth;


    public static FirebaseDAO getInstance()
    {
        if (single_instance == null) {
            single_instance = new FirebaseDAO();
            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance();
        }
        return single_instance;
    }

    public static FirebaseAuth getmAuth() {
        getInstance();
        return mAuth;
    }

    public static FirebaseDatabase getmDatabase() {
        getInstance();
        return mDatabase;
    }
}

