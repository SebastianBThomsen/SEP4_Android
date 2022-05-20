package com.example.sep4_android.ui.selectRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sep4_android.databinding.FragmentSelectRoomBinding;
import com.example.sep4_android.repositories.RouteRepository;
import com.example.sep4_android.ui.RoomRecycler.DeviceAdapter;

public class SelectRoomFragment extends Fragment {

    private SelectRoomViewModel viewModel;
    private FragmentSelectRoomBinding binding;
    private RecyclerView recyclerView;
    private RouteRepository repo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SelectRoomViewModelImpl.class);
        binding = FragmentSelectRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        repo = RouteRepository.getInstance(getActivity().getApplication());

        bindings();
        setText();


        return root;
    }

    private void setText(){
        recyclerView = binding.rv;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        repo.getAllDevices().observe(getViewLifecycleOwner(), devices -> {
            DeviceAdapter adapter = new DeviceAdapter(devices);
            recyclerView.setAdapter(adapter);

            adapter.setOnClickListener(device ->{
                System.out.println(device.getDeviceRoom());
            });
        });

    }

    private void bindings() {
        //Select Room

    }

}