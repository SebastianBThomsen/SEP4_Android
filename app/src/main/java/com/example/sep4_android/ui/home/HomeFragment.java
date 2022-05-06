package com.example.sep4_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHomeBinding;
import com.example.sep4_android.ui.healthInspection.HealthInspectionViewModelImpl;

public class HomeFragment extends Fragment {

    HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModelImpl.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //FIXME: FIREBASE SKAL INDSÆTTES ET ELLER ANDET STED! - wupwup

        bindings();
        setText();
        return root;
    }

    private void setText() {
        textView.setText("Virker jeg?");
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