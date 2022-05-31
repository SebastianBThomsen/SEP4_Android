package com.example.sep4_android.ui.settings;

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

import com.example.sep4_android.databinding.FragmentSettingsBinding;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {

    private SettingsViewModelImpl viewModel;
    private FragmentSettingsBinding binding;

    private EditText editTemp, editCO2, editHumidity, editTempMargin;
    private Button btn_submit;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SettingsViewModelImpl.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        observers();
        bindings();

        return root;
    }

    private void observers() {
        viewModel.getDeviceSettings().observe(getViewLifecycleOwner(), deviceSettings -> {
            if (deviceSettings != null) {
                editCO2.setHint("" + deviceSettings.getCo2Threshold());
                editHumidity.setHint("" + deviceSettings.getHumidityThreshold());
                editTemp.setHint("" + deviceSettings.getTargetTemperature());
                editTempMargin.setHint("" + deviceSettings.getTemperatureMargin());
            }
        });
    }

    private void bindings() {
        //Settings
        editCO2 = binding.editTextEditRoomCO2;
        editHumidity = binding.editTextEditRoomHumidity;
        editTemp = binding.editTextEditRoomTemperature;
        editTempMargin = binding.editTextEditRoomTempMargin;

        //Button
        btn_submit = binding.btnSubmitDesiredSettings;
        btn_submit.setOnClickListener(this::submitSettings);
    }

    private void submitSettings(View view) {
        //Skal bruge dem mange gange i checks så laver lige et field
        String sco2 = editCO2.getText().toString().equals("") ?
                editCO2.getHint().toString() : editCO2.getText().toString();
        String shumidity = editHumidity.getText().toString().equals("") ?
                editHumidity.getHint().toString() : editHumidity.getText().toString();
        String stemp = editTemp.getText().toString().equals("") ?
                editTemp.getHint().toString() : editTemp.getText().toString();
        String stempMargin = editTempMargin.getText().toString().equals("") ?
                editTempMargin.getHint().toString() : editTempMargin.getText().toString();

        //Rq null check
        if (sco2.equals("") && shumidity.equals("") && stemp.equals("") && stempMargin.equals("")) {
            Snackbar.make(getView(), "Invalid Data, some fields empty", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        //Rq grænse check
        int co2 = Integer.parseInt(sco2);
        int humidity = Integer.parseInt(shumidity);
        int temp = Integer.parseInt(stemp);
        int tempMargin = Integer.parseInt(stempMargin);

        if (temp <= 25 && co2 <= 1000 && humidity <= 80 && tempMargin <= 5) {
            viewModel.sendDeviceSettings(
                    co2,
                    humidity,
                    temp,
                    tempMargin
            );
            Snackbar.make(getView(), "Success", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (temp > 25 || temp < 10) {
            Snackbar.make(getView(), "Invalid temperature input. Can't be outside 10-25 degrees", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (co2 > 1000 || co2 < 0) {
            Snackbar.make(getView(), "Invalid CO2 input. Can't be outside 0-1000 ppm", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (humidity > 80 || humidity < 0) {
            Snackbar.make(getView(), "Invalid humidity input. Can't be outside 0-80%", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (tempMargin < 0) {
            Snackbar.make(getView(), "Invalid temperature margin input. Can't be below 0", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(getView(), "Invalid input. Something wasent right. Make sure temperature <= 25, co2 <= 1000, humidity <= 80 & margin <= 5", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}