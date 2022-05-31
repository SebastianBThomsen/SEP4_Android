package com.example.sep4_android.ui.graph.lineCharts.humidity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHumidityBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.ui.graph.GraphViewModel;
import com.example.sep4_android.ui.graph.GraphViewModelImpl;
import com.example.sep4_android.ui.graph.lineCharts.design.GraphDesign;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class HumidityFragment extends Fragment {

    private GraphViewModel viewModel;
    private FragmentHumidityBinding binding;
    private LineChart lineChart;
    private GraphDesign graphDesign;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(GraphViewModelImpl.class);
        binding = FragmentHumidityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        graphDesign = new GraphDesign();

        bindings();
        observers();

        return root;
    }

    private void observers() {
        viewModel.getAllMeasurementsByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<Entry> test = new ArrayList<>();
                int i = 0;
                double sum = 0;
                for (Measurement measurement : measurements) {
                    i++;
                    sum = measurement.getHumidity() + sum;
                    test.add(new Entry(i, (float) measurement.getHumidity()));

                }
                inputDataToChart(test);
            }
        });
    }

    private void inputDataToChart(ArrayList<Entry> test) {
        //todo add design classen efter vi har fået det op at kører
        LineDataSet lineDataSet = new LineDataSet(test, "Humidity");
        lineDataSet.setValueTextSize(16f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        graphDesign.lineChartDesign(lineChart);
        graphDesign.lineData(lineData);
        graphDesign.lineDataSet(lineDataSet);
    }

    private void bindings() {
        lineChart = binding.LineChartForHumidity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
