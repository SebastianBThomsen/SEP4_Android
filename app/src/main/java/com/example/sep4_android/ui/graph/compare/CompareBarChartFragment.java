package com.example.sep4_android.ui.graph.compare;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentBarChartCompareBinding;
import com.example.sep4_android.databinding.FragmentLineChartcompareBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.ui.graph.DesignForGraph.GraphDesign;
import com.example.sep4_android.ui.graph.lineChartForTemp.LineViewModelImpl;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class CompareBarChartFragment extends Fragment {
   private LineViewModelImpl viewModel; //todo Fix LIneViewMOdelImpl måske bare lav en ny
    private FragmentBarChartCompareBinding binding;
    private BarChart barChart;
    private GraphDesign design;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LineViewModelImpl.class);
        binding = FragmentBarChartCompareBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        design = new GraphDesign();
        bindings();
        observers();
        viewModel.getAllMeasurementsByDevice();
        return root;
    }

    private void observers() {
        viewModel.getAllMeasurementsByDevice().observe(getViewLifecycleOwner(), measurements -> {
            if (measurements != null) {
                ArrayList<BarEntry> compareMesurements = new ArrayList<>();
                ArrayList<BarEntry> compareMesurements1 = new ArrayList<>();
                ArrayList<BarEntry> compareMesurements2 = new ArrayList<>();
                double sum =0;
                int i = 0;
                for (Measurement measurement:measurements)
                {
                    i++;
                    compareMesurements.add(new BarEntry(i, (float) measurement.getCo2()));
                    compareMesurements1.add(new BarEntry(i, (float) measurement.getTemperature()));
                    compareMesurements2.add(new BarEntry(i, (float) measurement.getHumidity()));
                }
                inputDataToChart(compareMesurements,compareMesurements1,compareMesurements2);

                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
            }
        });
    }

    private void inputDataToChart(ArrayList<BarEntry> compareMesurements, ArrayList<BarEntry> compareMesurements1, ArrayList<BarEntry> compareMesurements2) {
        BarDataSet barDataSet = new BarDataSet(compareMesurements,"Co2");
        BarDataSet barDataSet1 = new BarDataSet(compareMesurements1, "Temperature");
        BarDataSet barDataSet2= new BarDataSet(compareMesurements2, "Humidity");
        barDataSet.setColor(Color.rgb(52,168,50));
        barDataSet.setColor(Color.rgb(53,14,179));
        barDataSet2.setColor(Color.rgb(179,22,14));
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        BarData data = new BarData(dataSets);
        barChart.setData(data);
        barChart.groupBars(0,0.001f,0.01f);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(30);
        barChart.setDrawGridBackground(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);

    }

    private void bindings() {
        barChart = binding.barchartComapreView;
    }


}
