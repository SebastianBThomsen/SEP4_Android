package com.example.sep4_android.ui.graph.lineCharts.humidity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHumidityDetailBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.ui.graph.lineCharts.design.GraphDesign;
import com.example.sep4_android.ui.graph.GraphViewModel;
import com.example.sep4_android.ui.graph.GraphViewModelImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class HumidityDetailFragment extends Fragment {

    private GraphViewModel viewModel;
    private FragmentHumidityDetailBinding binding;
    private LineChart lineChart;
    private GraphDesign graphDesign;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(GraphViewModelImpl.class);
        binding = FragmentHumidityDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        graphDesign = new GraphDesign();

        bindings();
        observers();

        return root;
    }

    private void observers() {
        viewModel.getAllMeasurementsByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> HumMesurements = new ArrayList<>();
                int i = 0;
                double sum = 0;
                for (Measurement measurement : measurements) {
                    i++;
                    sum = measurement.getHumidity() + sum;
                    HumMesurements.add(new Entry(i, (float) measurement.getHumidity()));
                }
                graphDesign.setAvg(lineChart, (float) average(sum, i));
                inputDataToChart(HumMesurements);
                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
            }
        });
    }

    private void bindings() {
        lineChart = binding.lcDetailHumidity;
    }

    private void inputDataToChart(ArrayList<Entry> test) {
        LineDataSet lineDataSet = new LineDataSet(test, "Humidity");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        graphDesign.lineChartDesign(lineChart);
        graphDesign.lineDataSet(lineDataSet);
        lineDataSet.setDrawCircles(true);
    }

    private double average(double b, int a) {
        double avg = b / a;
        System.out.println("Her får vi average fra metoden average fra temp  " + avg);
        lineChart.setAlpha((float) avg);
        return avg;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}