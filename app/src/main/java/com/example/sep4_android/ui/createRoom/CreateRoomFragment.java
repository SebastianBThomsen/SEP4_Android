package com.example.sep4_android.ui.createRoom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentCreateRoomBinding;

public class CreateRoomFragment extends Fragment {

    private CreateRoomViewModelImpl viewModel;
    private FragmentCreateRoomBinding binding;

    //Fields from xml
    private EditText editText_blockName, editText_floor, editText_roomNumber, editText_roomLetter;
    private Button btn_submit;
    private TextView tv_validation;

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
        editText_blockName = binding.editTextBlockName;
        editText_floor = binding.editTextFloor;
        editText_roomLetter = binding.editTextRoomLetter;
        editText_roomNumber = binding.editTextRoomNumber;


        btn_submit = binding.btnAddRoom;
        tv_validation = binding.tvValidation;
        btn_submit.setOnClickListener(this::addRoom);
    }

    private void addRoom(View view) {
        String addRoomReturn = viewModel.addRoom(
                editText_blockName.getText().toString(),
                editText_floor.getText().toString(),
                editText_roomNumber.getText().toString(),
                editText_roomLetter.getText().toString());

        tv_validation.setText(addRoomReturn);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}