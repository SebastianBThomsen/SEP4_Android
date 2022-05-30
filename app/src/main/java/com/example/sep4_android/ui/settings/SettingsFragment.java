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
        if (!(editHumidity.getText().toString().isEmpty() && editCO2.getText().toString().isEmpty() && editTemp.getText().toString().isEmpty() && editTempMargin.getText().toString().isEmpty()))
        {
            viewModel.sendSettings(
                    Integer.parseInt(editTemp.getText().toString()),
                    Integer.parseInt(editCO2.getText().toString()),
                    Integer.parseInt(editHumidity.getText().toString()),
                    Integer.parseInt(editTempMargin.getText().toString())
                    );
            Snackbar.make(getView(), "Succes", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else Snackbar.make(getView(), " Invalid Data", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        System.out.println("Kommer vi her ned");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}