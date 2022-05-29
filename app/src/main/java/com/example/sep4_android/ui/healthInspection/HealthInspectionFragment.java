package com.example.sep4_android.ui.healthInspection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHealthInspectionBinding;
import com.example.sep4_android.model.DateHandler;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DecimalFormat;

public class HealthInspectionFragment extends Fragment {

    private HealthInspectionViewModel viewModel;

    private FragmentHealthInspectionBinding binding;

    //Display data between timestamps
    private TextView startTime, endTime;
    private TextView tv_avgTemp, tv_avgCO2, tv_avgHumidity;
    private TextView tv_maxTemp, tv_maxCO2, tv_maxHumidity;
    private TextView tv_minTemp, tv_minCO2, tv_minHumidity;
    private TextView tv_latestCO2, tv_latestHumidity, tv_latestTemp, tv_latestTime;

    private Button btn_submitTime;

    //DatePickers
    private MaterialDatePicker materialDatePickerStart, materialDatePickerEnd;
    private Button btn_startDate, btn_endDate;
    private long startDate, endDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HealthInspectionViewModelImpl.class);

        binding = FragmentHealthInspectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        materialDatePickerStart = DateHandler.getMaterialDatePicker();
        materialDatePickerEnd = DateHandler.getMaterialDatePicker();

        bindings();
        observers();

        return root;
    }

    private void observers() {
        //Observering from data from ViewModel
        viewModel.getAverageMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            DecimalFormat df = new DecimalFormat("#0.0");
            tv_avgCO2.setText("" + df.format(measurement.getCo2()));
            tv_avgHumidity.setText("" + df.format(measurement.getHumidity()));
            tv_avgTemp.setText("" + df.format(measurement.getTemperature()));
        });

        viewModel.getMaximumMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            tv_maxCO2.setText("" + measurement.getCo2());
            tv_maxHumidity.setText("" + measurement.getHumidity());
            tv_maxTemp.setText("" + measurement.getTemperature());
        });

        viewModel.getMinimumMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            tv_minCO2.setText("" + measurement.getCo2());
            tv_minHumidity.setText("" + measurement.getHumidity());
            tv_minTemp.setText("" + measurement.getTemperature());
        });

        viewModel.getLatestMeasurement().observe(getViewLifecycleOwner(), measurement -> {
            tv_latestCO2.setText("" + measurement.getCo2());
            tv_latestHumidity.setText("" + measurement.getHumidity());
            tv_latestTemp.setText("" + measurement.getTemperature());
            tv_latestTime.setText("" + measurement.getTimestampString());
        });
    }

    private void bindings() {
        //Displaying data between timestamps
        //Average
        tv_avgTemp = binding.tvAvgTemp;
        tv_avgHumidity = binding.tvAvgHumidity;
        tv_avgCO2 = binding.tvAvgCO2;

        //Max
        tv_maxTemp = binding.tvMaxTemp;
        tv_maxHumidity = binding.tvMaxHumidity;
        tv_maxCO2 = binding.tvMaxCO2;

        //Min
        tv_minTemp = binding.tvMinTemp;
        tv_minHumidity = binding.tvMinHumidity;
        tv_minCO2 = binding.tvMinCO2;

        //
        tv_latestCO2 = binding.tvLatestCO2;
        tv_latestHumidity = binding.tvLatestHumidity;
        tv_latestTemp = binding.tvLatestTemp;
        tv_latestTime = binding.tvLatestTimeStamp;

        startTime = binding.tvStartTime;
        endTime = binding.tvEndTime;

        btn_submitTime = binding.btnSubmit;
        btn_submitTime.setOnClickListener(this::submitTime);

        btn_startDate = binding.btnStartDate;
        btn_endDate = binding.btnEndDate;

        btn_startDate.setOnClickListener(this::setStartDate);
        btn_endDate.setOnClickListener(this::setEndDate);


    }

    private void setStartDate(View view) {
        materialDatePickerStart.show(getActivity().getSupportFragmentManager(), "test");

        //When accepting chosen date, display in view!
        materialDatePickerStart.addOnPositiveButtonClickListener(selection -> {
            startDate = (Long) selection;
            startTime.setText(DateHandler.fromLongToStringDatePicker((Long) selection));
        });
    }

    private void setEndDate(View view) {
        materialDatePickerEnd.show(getActivity().getSupportFragmentManager(), "test");

        //When accepting chosen date, display in view!
        materialDatePickerEnd.addOnPositiveButtonClickListener(selection -> {
            endDate = (Long) selection;
            endTime.setText(DateHandler.fromLongToStringDatePicker((Long) selection));
        });
    }

    private void submitTime(View view) {
        viewModel.setTimestamp(startDate, endDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}