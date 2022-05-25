package com.example.sep4_android.ui.createRoom;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sep4_android.databinding.FragmentCreateRoomBinding;

public class CreateRoomFragment extends Fragment {

    private CreateRoomViewModelImpl viewModel;
    private FragmentCreateRoomBinding binding;

    //Fields from xml
    private EditText editText_roomNameAdd;
    private Button btn_submit;
    private TextView tv_validation;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CreateRoomViewModelImpl.class);

        binding = FragmentCreateRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bindings();
        observers();


        return root;
    }

    private void observers() {
        viewModel.getAllRooms().observe(getViewLifecycleOwner(), rooms -> {
            //For at tjekke om listen af rooms opdaterer!
            Log.i("Retrofit", "List of rooms: " + rooms);
        });
    }

    private void bindings() {
        editText_roomNameAdd = binding.editTextRoomNameAdd;
        btn_submit = binding.btnAddRoom;
        tv_validation = binding.tvValidation;
        btn_submit.setOnClickListener(this::addRoom);
    }

    private void addRoom(View view) {
        String addRoomReturn = viewModel.addRoom(editText_roomNameAdd.getText().toString());

        tv_validation.setText(addRoomReturn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}