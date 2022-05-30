package com.example.sep4_android.ui.graph.lineCharts.temperature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentLinechartBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.ui.graph.GraphViewModel;
import com.example.sep4_android.ui.graph.GraphViewModelImpl;
import com.example.sep4_android.ui.graph.lineCharts.design.GraphDesign;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class LineFragment extends Fragment {

    private GraphViewModel viewModel;
    private FragmentLinechartBinding binding;
    private LineChart lineChart;
    private GraphDesign graphDesign;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(GraphViewModelImpl.class);
        binding = FragmentLinechartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        graphDesign = new GraphDesign();

        bindings();
        observers();

        return root;
    }

    private void observers() {
        viewModel.getAllMeasurementsByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> tempMesurements = new ArrayList<>();
                int i = 0;
                double sum = 0;
                for (Measurement measurement : measurements) {
                    i++;
                    sum = measurement.getTemperature() + sum;
                    tempMesurements.add(new Entry(i, (float) measurement.getTemperature()));
                }
                inputDataToChart(tempMesurements);
                //FIXME: Timestamp skal bruges på x istedet for 1 ,2 og 3
            }
        });
    }


    private void bindings() {
        lineChart = binding.LineChartForTemp;
    }


    private void inputDataToChart(ArrayList<Entry> input) {
        LineDataSet lineDataSet = new LineDataSet(input, "Temperature");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        graphDesign.lineChartDesign(lineChart);
        graphDesign.lineData(lineData);
        graphDesign.lineDataSet(lineDataSet);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}