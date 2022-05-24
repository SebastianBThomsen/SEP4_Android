package com.example.sep4_android.ui.registerDevice;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentRegisterDeviceBinding;
import com.example.sep4_android.databinding.FragmentSelectRoomBinding;
import com.example.sep4_android.repositories.RouteRepositoryImpl;
import com.example.sep4_android.ui.selectRoom.SelectRoomViewModel;
import com.example.sep4_android.ui.selectRoom.SelectRoomViewModelImpl;

public class RegisterDevice extends Fragment {

    private RegisterDeviceViewModelImpl viewModel;
    private FragmentRegisterDeviceBinding binding;
    private RouteRepositoryImpl repo;
    private View root;

    private TextView text_deviceName;
    private EditText edit_Classroom;
    private Button btn_register;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RegisterDeviceViewModelImpl.class);
        binding = FragmentRegisterDeviceBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        repo = RouteRepositoryImpl.getInstance(getActivity().getApplication());

        bindings();
        setText();

        return root;
    }

    public void bindings(){
        text_deviceName = binding.textDeviceName;
        edit_Classroom = binding.editClassroom;
        btn_register = binding.btnRegister;

        btn_register.setOnClickListener(this::register);
    }

    public void setText(){
        System.out.println("Test: "+repo.getSelectedUnregistedDevice().getDeviceId());
        text_deviceName.setText(repo.getSelectedUnregistedDevice().getDeviceId());

    }

    private void register(View view){
        viewModel.Register(edit_Classroom.getText().toString());
        Navigation.findNavController(root).navigate(R.id.nav_selectRoomFragment);
    }
}