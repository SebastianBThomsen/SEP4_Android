package com.example.sep4_android.ui.healthInspection;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sep4_android.databinding.FragmentHealthInspectionBinding;

public class HealthInspectionFragment extends Fragment {

    private HealthInspectionViewModelImpl viewModel;

    private FragmentHealthInspectionBinding binding;

    private Button btn_findRandomTemperature;
    private TextView tv_temperature, tv_co2, tv_humidity;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HealthInspectionViewModelImpl.class);


        binding = FragmentHealthInspectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bindings();
        observers();
        onClickListeners();

        return root;
    }

    private void observers() {
        viewModel.getRandomData().observe(getViewLifecycleOwner(), healthData -> {
            tv_temperature.setText(String.valueOf(healthData.getTemperature()));
            tv_co2.setText(String.valueOf(healthData.getCo2()));
            tv_humidity.setText(String.valueOf(healthData.getHumidity()));

            /* Glide.with(this).load(dog.getImageURL()).into(imageView);
            name.setText(dog.getName());
            lifespan.setText(dog.getLifeSpan());
            temperament.setText(dog.getTemperament());

             */
        });
    }

    private void onClickListeners() {
        btn_findRandomTemperature.setOnClickListener(view -> {
            viewModel.findRandomHealthData();
        });

        /*
        //mobile_navigation.xml
         button_addHealthInspection.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(R.id.action_nav_healthInspection_to_addHealthInspectionFragment);
        });
         */
    }

    private void bindings() {
        btn_findRandomTemperature = binding.findRandomTemperature;
        tv_temperature = binding.textViewTemperature;
        tv_co2 = binding.textViewCo2;
        tv_humidity = binding.textViewHumidity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}