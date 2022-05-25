package com.example.sep4_android.ui.createUser;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentCo2DetailBinding;
import com.example.sep4_android.databinding.FragmentCreateUserBinding;
import com.example.sep4_android.ui.graph.DesignForGraph.GraphDesign;
import com.example.sep4_android.ui.graph.lineChartForTemp.LineViewModelImpl;
import com.google.firebase.auth.FirebaseAuth;

public class CreateUserFragment extends Fragment {
    EditText fullName,email,password,phone;
    Button registerBtn;
    boolean valid = true;
    private CreateUserViewModel viewModel;
    private FragmentCreateUserBinding binding;
    FirebaseAuth mAuth;

    public static CreateUserFragment newInstance() {
        return new CreateUserFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(CreateUserViewModelImlp.class);
        binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        bindings();
        observers();
        onClickListeners();

        return root;    
        
    }



    private void observers()
    {

    }

    private void bindings() {
        fullName = binding.registerName;
        email = binding.registerEmail;
        password = binding.registerPassword;
        phone = binding.registerPhone;
        registerBtn = binding.registerBtn;
    }


    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }

    private void onClickListeners() {
        registerBtn.setOnClickListener(view -> {
            viewModel.signUp(email.getText().toString(),password.getText().toString());
            Toast.makeText(getContext(), "Bla",Toast.LENGTH_SHORT).show();
        });

    }
}