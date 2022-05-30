package com.example.sep4_android.ui.registerDevice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentRegisterDeviceBinding;
import com.example.sep4_android.model.persistence.entities.DeviceRoom;

import java.util.ArrayList;

public class RegisterDeviceFragment extends Fragment {

    private RegisterDeviceViewModelImpl viewModel;
    private FragmentRegisterDeviceBinding binding;
    private View root;

    private TextView text_deviceName;
    private Button btn_register;
    private Spinner spinner_selectRoom;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RegisterDeviceViewModelImpl.class);
        binding = FragmentRegisterDeviceBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        bindings();
        setText();

        adapterSetup();

        return root;
    }

    private void adapterSetup() {
        ArrayList<String> roomList = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, roomList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_selectRoom.setAdapter(adapter);

        viewModel.getAllRooms().observe(getViewLifecycleOwner(), deviceRooms -> {
            roomList.clear();
            for (DeviceRoom room: deviceRooms) {
                if(!room.getRoomName().equals("def"))
                    roomList.add(room.getRoomName());
            }
            //notifyDataSetChanged after update roomList variable here
            adapter.notifyDataSetChanged();
        });
    }


    private void bindings() {
        text_deviceName = binding.textDeviceName;
        btn_register = binding.btnRegister;

        spinner_selectRoom = binding.spinnerSelectRoom;

        btn_register.setOnClickListener(this::register);
    }

    private void setText() {
        text_deviceName.setText(viewModel.getSelectedUnregisteredDevice().getClimateDeviceId());
    }

    private void register(View view) {
        viewModel.register(spinner_selectRoom.getSelectedItem().toString());
        Navigation.findNavController(root).navigate(R.id.nav_selectRoomFragment);
    }
}