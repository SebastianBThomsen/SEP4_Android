package com.example.sep4_android.ui.graph.lineChartForCo2;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sep4_android.databinding.FragmentCo2Binding;
import com.example.sep4_android.databinding.FragmentHumidityBinding;
import com.example.sep4_android.model.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class co2Fragment extends Fragment {
    private Co2ViewModelImpl viewModel;
    private FragmentCo2Binding binding;
    private TextView tv_Average;
    private LineChart lineChart;
    private ArrayList<Entry> test = new ArrayList<>();
    double avg;


    public static co2Fragment newInstance() {
        return new co2Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(Co2ViewModelImpl.class);
        binding = FragmentCo2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        bindings();

        observers();

        viewModel.findAllHealthDataByDevice();
        return root;
    }


    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), device -> {
            if (device.getMeasurements() != null) {
                ArrayList<Entry> co2Mesurements = new ArrayList<>();
                int i = 0;
                double sum = 0;
                for (Measurement measurement : device.getMeasurements()) {
                    i++;
                    sum = measurement.getCo2() + sum;
                    co2Mesurements.add(new Entry(i, (float) measurement.getCo2()));
                }
                inputDataToChart(co2Mesurements);
                average(sum, i);
                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
            }


        });
    }

    private void bindings() {
        lineChart = binding.LineChartForCo2;
        tv_Average = binding.txtAvg;
    }

    private void inputDataToChart(ArrayList<Entry> co2Mesurements) {
        LineDataSet lineDataSet = new LineDataSet(test, "Co2");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        // add x-axis limit line         xAxis.enableGridDashedLine(10f, 10f, 0f);
    }

    private double average(double b, int a) {
        double avg = b / a;
        System.out.println("Her får vi average fra metoden average fra co2 " + avg);
        lineChart.setY((float) avg);
        return avg;
    }

}