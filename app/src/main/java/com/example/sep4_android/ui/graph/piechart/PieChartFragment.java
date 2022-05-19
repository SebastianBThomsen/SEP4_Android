package com.example.sep4_android.ui.graph.piechart;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.sep4_android.databinding.FragmentPieChartBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartFragment extends Fragment {

    private PieChartViewModel mViewModel;
    private FragmentPieChartBinding binding;
    private PieChart pieChart;
    int count,range;

    public static PieChartFragment newInstance() {
        return new PieChartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //inflater.inflate(R.layout.fragment_pie_chart, container, false);
        binding = FragmentPieChartBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        bindings();
        observers();

        count = 5;
        range = 200;
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5)));
        }
        PieDataSet pieDataSet = new PieDataSet(entries, "TestGraph");


        pieDataSet.setValueTextSize(16f);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(180);
        //pieChart.setFitBars(true);
        pieChart.setData(pieData);

        pieChart.setCenterText("Tærte chart");
        pieChart.setCenterTextSize(24f);
        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(24f);

        ArrayList<Integer> colors = new ArrayList<>();



        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        pieChart.getDescription().setText("BarChart TEst");
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        onClickListeners();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PieChartViewModel.class);
        // TODO: Use the ViewModel
    }

    private void onClickListeners() {
    }

    private void observers() {
    }

    private void bindings() {
        pieChart = binding.pieChart;
    }

}