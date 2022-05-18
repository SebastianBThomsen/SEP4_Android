package com.example.sep4_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHomeBinding;
import com.example.sep4_android.model.Measurement;
import com.example.sep4_android.ui.healthInspection.HealthInspectionViewModelImpl;

import java.util.List;

public class HomeFragment extends Fragment {

    HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private TextView textView;

    private LiveData<List<Measurement>> meassu;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModelImpl.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //FIXME: FIREBASE SKAL INDSÆTTES ET ELLER ANDET STED! - wupwup
        //FIXME: Display Average Temp, Co2, humidity between 2 timestamps!
        //viewModel.getHealthDataBetweenTimeStamps()

        bindings();
        setText();
        return root;
    }

    private void setText() {
        viewModel.getMeasures().observe(getViewLifecycleOwner(), measurements -> {
            if(measurements == null) return;
            textView.setText(""+measurements.get(0).getTimestamp());
        });
    }

    private void bindings() {
        textView = binding.textHome;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}