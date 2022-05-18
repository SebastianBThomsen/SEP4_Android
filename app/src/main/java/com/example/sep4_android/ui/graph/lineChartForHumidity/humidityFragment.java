package com.example.sep4_android.ui.graph.lineChartForHumidity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentHumidityBinding;
import com.example.sep4_android.model.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class humidityFragment extends Fragment {

    private HumidityViewModelImpl viewModel;
    private FragmentHumidityBinding binding;
    private LineChart lineChart;

    double tmp =0;

    public static humidityFragment newInstance() {
        return new humidityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HumidityViewModelImpl.class);
        binding = FragmentHumidityBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        bindings();
        Log.e("observers", "Before Observers " + tmp);
        observers();
        Log.e("observers", "After Observers " );
        viewModel.findAllHealthDataByDevice();

        return root;
        }

    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), device -> {
            if (device.getMeasurements() != null) {
                ArrayList<Entry> test = new ArrayList<>();
                int i =0;
                for (Measurement measurement:device.getMeasurements()) {
                    i++;
                    test.add(new Entry(i, (float) measurement.getHumidity()));
                }
                inputDataToChart(test);


            }
            //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3

    });
    }

    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test, "Test");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

    }

    private void bindings() {
            lineChart= binding.LineChartForHumidity;
        }




}
