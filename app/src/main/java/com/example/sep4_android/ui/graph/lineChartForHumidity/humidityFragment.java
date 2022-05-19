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
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
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
                double sum =0;
                for (Measurement measurement:device.getMeasurements()) {
                    i++;
                    sum = measurement.getHumidity() + sum;
                    test.add(new Entry(i, (float) measurement.getHumidity()));

                }

                LimitLine llXAxis = new LimitLine((float) average(sum,i), "Average");
                llXAxis.setLineWidth(4f);
                llXAxis.enableDashedLine(10f, 10f, 0f);
                llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
                llXAxis.setTextSize(10f);
                YAxis xAxis = lineChart.getAxisLeft();
                xAxis.addLimitLine(llXAxis); // add x-axis limit line
                xAxis.enableGridDashedLine(10f, 10f, 0f);
                inputDataToChart(test);


            }
            //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3

    });
    }

    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test, "Humidity");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.fitScreen();
        lineChart.setDrawGridBackground(false);
        lineChart.setScaleEnabled(true);
        lineChart.getDescription().setText("Dette Chart indholder Humidity");


    }

    private void bindings() {
            lineChart= binding.LineChartForHumidity;
        }

    private double average(double b,int a )
    {
        double avg = b/a;
        System.out.println("Her får vi average fra metoden average fra humidity "+avg);
        return avg;
    }


}
