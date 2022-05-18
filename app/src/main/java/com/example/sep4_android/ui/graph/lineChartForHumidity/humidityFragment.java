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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class humidityFragment extends Fragment {

    private HumidityViewModelImpl viewModel;
    private FragmentHumidityBinding binding;
    private LineChart lineChart;
    private ArrayList<Entry> test = new ArrayList<>();
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
        Log.e("observers", "After Observers " + tmp);
        viewModel.findAllHealthDataByDevice();
        inputs();
        return root;
        }

    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), device -> {
            device.getMeasurements().get(0).getHumidity();
            device.getMeasurements().get(0).getTimestamp();
    });
    }

    private void bindings() {
            lineChart= binding.LineChartForHumidity;
        }


    private void inputs(){

        test.add(new Entry(1, 60));
        test.add(new Entry(2, 60));
        test.add(new Entry(3, 30));
        test.add(new Entry(4, 40));
        LineDataSet lineDataSet = new LineDataSet(test,"Test");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.animateY(5000);
        lineChart.animateX(5000);
    }


}
