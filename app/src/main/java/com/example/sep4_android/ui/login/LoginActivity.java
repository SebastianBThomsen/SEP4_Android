package com.example.sep4_android.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
    private FirebaseAuth mAuth;

    LoginViewModel viewModel;

    private EditText username;
    private EditText password;

    //FIXME: Bruges denne?
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
        viewModel.checkLoggedIn(LoginActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                viewModel.login(LoginActivity.this, username.getText().toString(), password.getText().toString());
                checks(username,password);
                break;
        }
    }

    public void checks(EditText user,EditText pass)
    {
        String Email,Password;
        Email = username.getText().toString();
        Password = password.getText().toString();

        if (TextUtils.isEmpty(Email)){
            username.setError("Email cant be empty");
            username.requestFocus();
        }
        else if (TextUtils.isEmpty(Password))
        {
            password.setError("Password cant be empty");
            password.requestFocus();
        }

    }
}
