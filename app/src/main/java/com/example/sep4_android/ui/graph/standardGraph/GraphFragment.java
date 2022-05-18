package com.example.sep4_android.ui.graph.standardGraph;

import static com.patrykandpatryk.vico.core.entry.EntryListExtensionsKt.entryModelOf;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sep4_android.databinding.FragmentGraphBinding;

import com.patrykandpatryk.vico.core.entry.ChartEntryModel;
import com.patrykandpatryk.vico.view.chart.ChartView;
import com.patrykandpatryk.vico.view.chart.ComposedChartView;

public class GraphFragment extends Fragment {

    private GraphViewModelImpl mViewModel;

    private TextView tv_test;
    private Button btn_edit;
    private ChartView graph1,graph3 ;
     private ComposedChartView graph2;
    //private ArrayList<HealthData> testarray;

    private FragmentGraphBinding binding;
    //private HealthData HealthData;
    ChartEntryModel entryModel2 = entryModelOf(5f,15f,10f,2f,30f,25f,20f,15f,10f,2f,30f,25f);


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GraphViewModelImpl.class);
        binding = FragmentGraphBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding();
        tv_test.setText("Hej");
       // testarray.add(new HealthData(20,30,20,"20:20:20"));
        ChartEntryModel entryModel = entryModelOf(5f,15f,10f,2f,30f,25f);
        //ChartEntryModel entryModelHealthData = entryModelOf();
        graph1.setModel(entryModel);
        graph3.setModel(entryModel);

        onClickListeners();
        //TODO: Hvordan får vi vores data ind i grafen? - Med Labels
        //graph

        return root;


    }

    private void onClickListeners() {
        btn_edit.setOnClickListener(view -> {
            tv_test.setText("Blahblah");
            graph3.setModel(entryModel2);
        });
    }

    private void binding() {
        tv_test = binding.graphTv;
        btn_edit= binding.button;
        graph1 = binding.graphChartView;
        graph2 = binding.composedChart;
        graph3 = binding.graphChartViewWithAxis;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}