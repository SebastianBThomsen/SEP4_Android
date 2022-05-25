package com.example.sep4_android.ui.login;

import android.app.Activity;

public interface LoginViewModel {
    //Sender activity med så jeg kan starte ny aactivity / intent fra vm idk hvis det her er 100% rigtig
    public void login(Activity ac, String user, String pass);
    public void signUp(Activity ac, String user, String pass);
}
