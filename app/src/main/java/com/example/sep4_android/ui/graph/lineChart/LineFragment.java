package com.example.sep4_android.ui.graph.lineChart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentLinechartBinding;
import com.example.sep4_android.model.Device;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LineFragment extends Fragment {

    private LineViewModelImpl viewModel;
    private FragmentLinechartBinding binding;
    private LineChart lineChart;
    private Device device;
    private double tmp;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LineViewModelImpl.class);
        binding = FragmentLinechartBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        bindings();
        observers();
        viewModel.findAllHealthDataByDevice();
        ArrayList<Entry> test = new ArrayList<>();

        test.add(new Entry(1, (float) tmp));

        LineDataSet lineDataSet = new LineDataSet(test,"Test");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.animateY(5000);
        lineChart.animateX(8000);



        return root;
    }

    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), device -> {
          tmp = device.getMeasurements().get(0).getTemperature();
        });
    }




    private void bindings() {
        lineChart= binding.LineChart;
    }


}