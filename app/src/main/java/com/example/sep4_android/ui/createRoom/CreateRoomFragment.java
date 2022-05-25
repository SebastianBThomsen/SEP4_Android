package com.example.sep4_android.ui.createRoom;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentCreateRoomBinding;

public class CreateRoomFragment extends Fragment {

    private CreateRoomViewModelImpl viewModel;
    private FragmentCreateRoomBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CreateRoomViewModelImpl.class);

        binding = FragmentCreateRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bindings();


        return root;
    }

    private void bindings() {

    }

}