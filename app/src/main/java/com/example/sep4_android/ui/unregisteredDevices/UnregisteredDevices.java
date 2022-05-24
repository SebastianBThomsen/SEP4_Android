package com.example.sep4_android.ui.unregisteredDevices;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentSelectRoomBinding;
import com.example.sep4_android.databinding.FragmentUnregisteredDevicesFragmentBinding;
import com.example.sep4_android.model.persistence.entities.Device;
import com.example.sep4_android.repositories.RouteRepositoryImpl;
import com.example.sep4_android.ui.RoomRecycler.DeviceAdapter;
import com.example.sep4_android.ui.RoomRecycler.UnregisterdDeviceAdapter;
import com.example.sep4_android.ui.selectRoom.SelectRoomViewModel;
import com.example.sep4_android.ui.selectRoom.SelectRoomViewModelImpl;

import java.util.ArrayList;
import java.util.List;

public class UnregisteredDevices extends Fragment {

    private UnregisteredDevicesViewModelImpl viewModel;
    private FragmentUnregisteredDevicesFragmentBinding binding;
    private RecyclerView recyclerView;
    private RouteRepositoryImpl repo;

    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UnregisteredDevicesViewModelImpl.class);
        binding = FragmentUnregisteredDevicesFragmentBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        repo = RouteRepositoryImpl.getInstance(getActivity().getApplication());

        setText();

        return root;
    }

    private void setText(){
        recyclerView = binding.rvUnregisteredList;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        MutableLiveData<List<Device>> liste = new MutableLiveData();

        repo.getAllDevices().observe(getViewLifecycleOwner(), devices -> {
            List tmp = new ArrayList();
            for (Device i: devices) {
                if(i.getDeviceRoom() == null || i.getDeviceRoom().equals("")){
                    tmp.add(i);
                }
            }
            liste.postValue(tmp);
        });

        liste.observe(getViewLifecycleOwner(), devices -> {
            UnregisterdDeviceAdapter adapter = new UnregisterdDeviceAdapter(devices);
            recyclerView.setAdapter(adapter);

            adapter.setOnClickListener(device ->{
                //TODO: Brug viewmodel
                repo.setSelectedUnregistedDevice(device);
                Navigation.findNavController(root).navigate(R.id.nav_register_device);
            });
        });


    }

}