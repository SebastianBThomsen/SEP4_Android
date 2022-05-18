package com.example.sep4_android.ui.graph.barchart;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sep4_android.databinding.FragmentBarchartBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarchartFragment extends Fragment {

    private BarchartViewModel viewModel;
    private FragmentBarchartBinding binding;
    private BarChart barChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(BarchartViewModel.class);
        binding = FragmentBarchartBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        bindings();
        observers();
        ArrayList<BarEntry> test = new ArrayList<>();
        test.add(new BarEntry(2000,100));
        test.add(new BarEntry(2001,110));
        test.add(new BarEntry(2002,120));
        test.add(new BarEntry(2003,130));
        test.add(new BarEntry(2004,140));
        test.add(new BarEntry(2005,150));
        test.add(new BarEntry(2006,130));
        test.add(new BarEntry(2007,110));
        test.add(new BarEntry(2008,10));
        BarDataSet barDataSet = new BarDataSet(test, "TestGraph");
        barDataSet.setValueTextSize(16f);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("BarChart TEst");
        barChart.animateY(2000);
        onClickListeners();

        return root;
    }

    private void populate() {





    }

    private void onClickListeners() {
    }

    private void observers() {
    }

    private void bindings() {
        barChart = binding.barChart;
    }




}