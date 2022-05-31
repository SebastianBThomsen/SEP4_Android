package com.example.sep4_android.ui.allDevices;

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
import com.example.sep4_android.databinding.FragmentAllDevicesBinding;
import com.example.sep4_android.ui.roomRecycler.UnregisteredDeviceAdapter;


public class AllDevicesFragment extends Fragment {

    private AllDevicesViewModelImpl viewModel;
    private FragmentAllDevicesBinding binding;
    private RecyclerView recyclerView;

    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AllDevicesViewModelImpl.class);
        binding = FragmentAllDevicesBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        setUpRecyclerView();

        return root;
    }

    private void setUpRecyclerView() {
        recyclerView = binding.rvAllDevices;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        viewModel.getAllDevices().observe(getViewLifecycleOwner(), devices -> {
            UnregisteredDeviceAdapter adapter = new UnregisteredDeviceAdapter(devices);
            recyclerView.setAdapter(adapter);

            adapter.setOnClickListener(device -> {
                Navigation.findNavController(root).navigate(R.id.nav_register_device);
            });
        });
    }

}