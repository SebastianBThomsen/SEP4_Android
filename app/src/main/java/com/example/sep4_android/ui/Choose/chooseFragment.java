package com.example.sep4_android.ui.Choose;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentChooseBinding;
import com.example.sep4_android.databinding.FragmentCreateUserBinding;
import com.example.sep4_android.ui.createUser.CreateUserViewModelImlp;
import com.google.firebase.auth.FirebaseAuth;

public class chooseFragment extends Fragment {

    private ChooseViewModel viewModel;
    private Button addUser,Desire;
    private FragmentChooseBinding binding;

    public static chooseFragment newInstance() {
        return new chooseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ChooseViewModel.class);
        binding = FragmentChooseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        bindings();
        onClickListeners();

        return root;
    }

    private void onClickListeners() {
        addUser.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_createUserFragment);
        });
        Desire.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_settings);
        });


    }

    private void bindings() {
        addUser = binding.btnAddUser;
        Desire = binding.btnDesire;
    }


}