package com.example.sep4_android.ui.admin;

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
import com.example.sep4_android.databinding.FragmentAdminBinding;

public class AdminFragment extends Fragment {

    private AdminViewModel viewModel;
    private Button addUser, desire, room, device;
    private FragmentAdminBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AdminViewModelImpl.class);
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        bindings();
        onClickListeners();
        checkSelected();

        return root;
    }

    private void onClickListeners() {
        addUser.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_createUserFragment);
        });
        desire.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_settings);
        });
        room.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_create_room);
        });
        device.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.nav_unregistered_devices);
        });
    }

    private void bindings() {
        addUser = binding.btnAddUser;
        desire = binding.btnDesire;
        room = binding.btnRegRoom;
        device = binding.btnRegDevice;
    }

    private void checkSelected(){
        viewModel.checkSelected(desire);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}