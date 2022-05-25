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

import com.example.sep4_android.databinding.FragmentLineChartcompareBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.ui.graph.DesignForGraph.GraphDesign;
import com.example.sep4_android.ui.graph.lineChartForTemp.LineViewModelImpl;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class CompareLineChartFragment extends Fragment {
    private LineViewModelImpl viewModel;
    private FragmentLineChartcompareBinding binding;
    private LineChart lineChart;

    //FIXME: Disse 2 bliver ikke brugt? - Skal de det?
    private LineChart lineChart1;
    private LineChart lineChart2;
    private GraphDesign design;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(LineViewModelImpl.class);
        binding = FragmentLineChartcompareBinding.inflate(inflater,container,false);
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
                ArrayList<Entry> compareMesurements = new ArrayList<>();
                ArrayList<Entry> compareMesurements1 = new ArrayList<>();
                ArrayList<Entry> compareMesurements2 = new ArrayList<>();
                double sum =0;
                int i = 0;
                for (Measurement measurement:measurements)
                {
                    i++;
                    compareMesurements.add(new Entry(i, (float) measurement.getCo2()));
                    compareMesurements1.add(new Entry(i, (float) measurement.getTemperature()));
                    compareMesurements2.add(new Entry(i, (float) measurement.getHumidity()));
                }
                inputDataToChart(compareMesurements,compareMesurements1,compareMesurements2);

                //fixme Timestamp skal bruges på x istedet for 1 ,2 og 3
            }
        });}




    private void bindings() {
        lineChart= binding.compareLineChartView;


    }

    private void inputDataToChart(ArrayList<Entry> test, ArrayList<Entry> test1, ArrayList<Entry> test2) {
        //FIXME: Beskriv hvad der sker
        LineDataSet lineDataCO2 = new LineDataSet(test,"Co2");
        LineDataSet lineDataTemperature = new LineDataSet(test1, "Temperature");
        LineDataSet lineDataHumidity = new LineDataSet(test2, "Humidity");

        //Sætter CO2 til at have y-aske til højre
        lineDataCO2.setAxisDependency(YAxis.AxisDependency.RIGHT);

        //Sætter data overview farve (Aner ikke hvorfor dette skal gøres 2 gange??)
        lineDataCO2.setColor(Color.rgb(255,30,0));
        lineDataTemperature.setColor(Color.rgb(25,30,255));
        lineDataHumidity.setColor(Color.rgb(25,255,25));

        //FIXME: Beskriv hvad der sker her @BOBTHEMARK
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataCO2);
        dataSets.add(lineDataTemperature);
        dataSets.add(lineDataHumidity);

        //FIXME: Beskriv hvad der sker
        LineData data = new LineData(dataSets);
        lineChart.setData(data);

        //FIXME: Beskriv hvad der gøres her @BOBTHEBUILDER
        design.compareLineChartDesign(lineChart);
        design.compareLineDataSet(lineDataCO2);
        design.compareLineDataSet(lineDataTemperature);
        design.compareLineDataSet(lineDataHumidity);

        //Sætter linjernes farve
        lineDataTemperature.setColor(Color.rgb(25,30,255));
        lineDataCO2.setColor(Color.rgb(255,30,0));
        lineDataHumidity.setColor(Color.rgb(25,255,25));
    }
}
