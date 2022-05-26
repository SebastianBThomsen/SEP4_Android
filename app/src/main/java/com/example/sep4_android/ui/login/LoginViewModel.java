package com.example.sep4_android.ui.login;

import android.app.Activity;

public interface LoginViewModel {
    void login(Activity ac, String user, String pass);
    void checkLoggedIn(Activity ac);
}
