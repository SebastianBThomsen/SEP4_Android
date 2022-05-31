package com.example.sep4_android.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        int co2 = Integer.parseInt(sco2);
        int humidity = Integer.parseInt(shumidity);
        int temp = Integer.parseInt(stemp);
        int tempMargin = Integer.parseInt(stempMargin);

        //Rq null check
        if (!(editHumidity.getText().toString().isEmpty() && editCO2.getText().toString().isEmpty() && editTemp.getText().toString().isEmpty() && editTempMargin.getText().toString().isEmpty()))
        {
            //Rq grænse check
            if(temp <= 25 && co2 <= 1000 && humidity <= 80 && tempMargin <= 5){
                viewModel.sendDeviceSettings(
                        temp,
                        co2,
                        humidity,
                        tempMargin
                );
                Snackbar.make(getView(), "Succes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if(temp > 25){
                Snackbar.make(getView(), "Invalid desired temperature input. Cant be over 25 degrees", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if(co2 > 1000){
                Snackbar.make(getView(), "Invalid co2 input. Cant be over 1000 ppm", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if(humidity > 80){
                Snackbar.make(getView(), "Invalid humidity input. Cant be over 80%", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else if(tempMargin > 5){
                Snackbar.make(getView(), "Invalid temperature margin input. Cant be over deviate more than 5", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                Snackbar.make(getView(), "Invalid input. Something wasent right. Make sure temperature <= 25, co2 <= 1000, humidity <= 80 & margin <= 5", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }
        else Snackbar.make(getView(), " Invalid Data", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        System.out.println("Kommer vi her ned");
    }

    private void submitSettings2(View view) {
        //Ternary Statements, if no input get hint, else use input
        String co2 = editCO2.getText().toString().equals("") ?
                editCO2.getHint().toString() : editCO2.getText().toString();
        String humidity = editHumidity.getText().toString().equals("") ?
                editHumidity.getHint().toString() : editHumidity.getText().toString();
        String temp = editTemp.getText().toString().equals("") ?
                editTemp.getHint().toString() : editTemp.getText().toString();
        String tempMargin = editTempMargin.getText().toString().equals("") ?
                editTempMargin.getHint().toString() : editTempMargin.getText().toString();

        if (co2.equals("") || humidity.equals("") || temp.equals("") || tempMargin.equals("")) {
            Snackbar.make(getView(), " Invalid Data", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        viewModel.sendDeviceSettings(
                Integer.parseInt(co2),
                Integer.parseInt(humidity),
                Integer.parseInt(temp),
                Integer.parseInt(tempMargin));

        Snackbar.make(getView(), "Success", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}