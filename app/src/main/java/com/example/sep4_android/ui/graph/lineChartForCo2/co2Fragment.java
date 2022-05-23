package com.example.sep4_android.ui.graph.lineChartForCo2;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentCo2Binding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class co2Fragment extends Fragment {
    private Co2ViewModelImpl viewModel;
    private FragmentCo2Binding binding;
    private LineChart lineChart;

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
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> co2Mesurements = new ArrayList<>();
                int i = 0;
                double sum = 0;
                for (Measurement measurement : measurements) {
                    i++;
                    sum = measurement.getCo2() + sum;
                    co2Mesurements.add(new Entry(i, (float) measurement.getCo2()));
                }
                LimitLine llXAxis = new LimitLine((float) average(sum, i), "Average");
                llXAxis.setLineWidth(4f);
                llXAxis.enableDashedLine(10f, 10f, 0f);
                llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
                llXAxis.setTextSize(15f);
                YAxis xAxis = lineChart.getAxisLeft();
                xAxis.addLimitLine(llXAxis); // add x-axis limit line
                xAxis.enableGridDashedLine(10f, 10f, 0f);
                inputDataToChart(co2Mesurements);
                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
            }


        });
    }
    private void bindings() {
        lineChart = binding.LineChartForCo2;

    }

    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test, "Co2");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setScaleEnabled(true);
        lineChart.setBackgroundColor(0);
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setText("Dette Chart indholder Co2");
    }

    private double average(double b, int a) {
        double avg = b / a;
        System.out.println("Her får vi average fra metoden average fra co2 " + avg);
        lineChart.setAlpha((float) avg);
        return avg;
    }

}