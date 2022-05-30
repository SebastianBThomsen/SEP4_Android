package com.example.sep4_android.ui.createUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sep4_android.databinding.FragmentCreateUserBinding;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUserFragment extends Fragment {
    EditText email,password;
    Button registerBtn;
    CheckBox student, admin;

    private CreateUserViewModel viewModel;
    private FragmentCreateUserBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(CreateUserViewModelImlp.class);
        binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        bindings();
        onClickListeners();

        return root;
    }

    private void bindings() {
        email = binding.registerEmail;
        password = binding.registerPassword;
        registerBtn = binding.registerBtn;

        student = binding.isStudent;
        admin = binding.isAdmin;
    }


    private void onClickListeners() {
        registerBtn.setOnClickListener(view -> {
            if(admin.isChecked() && !student.isChecked())
                viewModel.signUp(email.getText().toString(),password.getText().toString(), 1);
            else if(student.isChecked() && !admin.isChecked())
                viewModel.signUp(email.getText().toString(),password.getText().toString(), 0);
            else
                Toast.makeText(getContext().getApplicationContext(), "Please select only one of the two roles", Toast.LENGTH_SHORT).show();
//            Toast.makeText(getContext(), "Bla",Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}