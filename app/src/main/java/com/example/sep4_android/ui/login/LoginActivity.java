package com.example.sep4_android.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth mAuth;

    LoginViewModel viewModel;

    private EditText username;
    private EditText password;

    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
        Button b = findViewById(R.id.loginBtn);
//        Button r = findViewById(R.id.signupBtn);
//        r.setOnClickListener(this);
        b.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(LoginViewModelImpl.class);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart() {
        super.onStart();

        FirebaseUser u = mAuth.getCurrentUser();
        if(u != null)
            Toast.makeText(getApplicationContext(), "User already logged in", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(), "User not logged in", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                viewModel.login(LoginActivity.this, username.getText().toString(), password.getText().toString());
                break;
//            case R.id.signupBtn:
//                viewModel.signUp(LoginActivity.this, username.getText().toString(), password.getText().toString());
//                break;
        }
    }
}
