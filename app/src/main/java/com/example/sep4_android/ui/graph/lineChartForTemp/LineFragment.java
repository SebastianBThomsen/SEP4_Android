package com.example.sep4_android.ui.graph.lineChartForTemp;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentLinechartBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LineFragment extends Fragment {

    private LineViewModelImpl viewModel;
    private FragmentLinechartBinding binding;
    private LineChart lineChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LineViewModelImpl.class);
        binding = FragmentLinechartBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        bindings();
        observers();
        viewModel.findAllHealthDataByDevice();
        return root;
    }

    private void observers() {
        viewModel.getAllHealthDataByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> tempMesurements = new ArrayList<>();
                int i =0;
                double sum =0;
                for (Measurement measurement: measurements)
                {
                    i++;
                    sum= measurement.getTemperature() + sum;
                    tempMesurements.add(new Entry(i, (float) measurement.getTemperature()));
                }
                LimitLine llXAxis = new LimitLine((float) average(sum,i), "Average");
                llXAxis.setLineWidth(8f);
                llXAxis.enableDashedLine(10f, 10f, 0f);
                llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
                llXAxis.setTextSize(15f);
                YAxis xAxis = lineChart.getAxisLeft();
                //xAxis.addLimitLine(llXAxis); // add x-axis limit line
                xAxis.enableGridDashedLine(10f, 10f, 0f);
                inputDataToChart(tempMesurements);
                lineChart.setBackgroundColor(Color.parseColor("#252424"));//Set as a black
                lineChart.setDrawGridBackground(false);//set this to true to draw the grid background, false if not
                lineChart.getAxisLeft().setTextColor(Color.parseColor("#ffffff")); // left y-axis
                lineChart.getAxisRight().setTextColor(Color.parseColor("#ffffff"));
                llXAxis.setTextColor(Color.parseColor("#ffffff"));
                lineChart.getXAxis().setTextColor(Color.parseColor("#ffffff"));
                lineChart.getLegend().setTextColor(Color.parseColor("#ffffff"));
                lineChart.getDescription().setTextColor(Color.parseColor("#ffffff"));
                lineChart.getData().setValueTextColor(Color.parseColor("#ffffff"));
                lineChart.getData().setValueTextSize(0f); //Set to 8f to display datapoints as text
                lineChart.getXAxis().setDrawGridLines(false);
                lineChart.getAxisLeft().setDrawGridLines(false);
                lineChart.getAxisRight().setDrawGridLines(false);
                lineChart.getData().setDrawValues(false);

                //lineChart.getXAxis().mDecimals();



                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
        }
    });}






    private void bindings() {
        lineChart= binding.LineChartForTemp;

    }



    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test,"Average");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);

        lineChart.setData(lineData);
        lineChart.fitScreen();
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setText("Dette Chart indholder Tempature");
        lineChart.setBackgroundColor(Color.blue(10));



    }
    private double average(double b,int a )
    {
        double avg = b/a;
        System.out.println("Her får vi average fra metoden average fra temp  "+avg);
        lineChart.setAlpha((float) avg);
        return avg;
    }
}