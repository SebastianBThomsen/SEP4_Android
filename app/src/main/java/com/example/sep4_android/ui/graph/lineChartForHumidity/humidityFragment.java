package com.example.sep4_android.ui.graph.lineChartForHumidity;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentHumidityBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.github.mikephil.charting.charts.LineChart;
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
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> test = new ArrayList<>();
                int i =0;
                double sum =0;
                for (Measurement measurement: measurements) {
                    i++;
                    sum = measurement.getHumidity() + sum;
                    test.add(new Entry(i, (float) measurement.getHumidity()));

                }
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
        lineChart.setScaleEnabled(true);
        lineChart.getDescription().setEnabled(false);
        lineData.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.rgb(76,175,80));
        lineDataSet.setFillColor(Color.rgb(76,175,80));
        lineChart.setBackgroundColor(Color.rgb(37, 36, 36));//Set as a gray
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        YAxis yAxis = lineChart.getAxisLeft(); // Show left y-axis line
        yAxis.setDrawGridLines(false); // Hide y-axis (horizontal) grid lines
        lineChart.getAxisLeft().setTextColor(Color.parseColor("#ffffff")); // left y-axis
        lineChart.getAxisRight().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getXAxis().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getLegend().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getDescription().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getData().setValueTextColor(Color.parseColor("#ffffff"));



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
