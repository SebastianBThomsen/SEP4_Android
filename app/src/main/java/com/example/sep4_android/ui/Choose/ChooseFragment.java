package com.example.sep4_android.ui.Choose;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentChooseBinding;

public class ChooseFragment extends Fragment {

    private ChooseViewModel viewModel;
    private Button addUser, desire;
    private FragmentChooseBinding binding;


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
        desire.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_settings);
        });


    }

    private void bindings() {
        addUser = binding.btnAddUser;
        desire = binding.btnDesire;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}