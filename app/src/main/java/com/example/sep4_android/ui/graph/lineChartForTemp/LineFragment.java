package com.example.sep4_android.ui.graph.lineChartForTemp;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentLinechartBinding;
import com.example.sep4_android.model.Device;
import com.example.sep4_android.model.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LineFragment extends Fragment {

    private LineViewModelImpl viewModel;
    private FragmentLinechartBinding binding;
    private LineChart lineChart;
    private Device device;
    private ArrayList<Entry> test = new ArrayList<>();
    double tmp =0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LineViewModelImpl.class);
        binding = FragmentLinechartBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        bindings();
        Log.e("observers", "Before Observers " + tmp);
        observers();
        Log.e("observers", "After Observers " + tmp);
        viewModel.findAllHealthDataByDevice();




        return root;
    }

    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), device -> {
            if (device.getMeasurements() != null) {
                ArrayList<Entry> tempMesurements = new ArrayList<>();
                int i =0;
                for (Measurement measurement:device.getMeasurements()) {
                    i++;
                    tempMesurements.add(new Entry(i, (float) measurement.getTemperature()));
                }
                inputDataToChart(tempMesurements);
                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
        }
    });}






    private void bindings() {
        lineChart= binding.LineChartForTemp;
    }



    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test,"Test");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);




        LimitLine llXAxis = new LimitLine(43f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);



        YAxis xAxis = lineChart.getAxisLeft();
        xAxis.addLimitLine(llXAxis); // add x-axis limit line
        xAxis.enableGridDashedLine(10f, 10f, 0f);

        lineChart.setData(lineData);

    }
}