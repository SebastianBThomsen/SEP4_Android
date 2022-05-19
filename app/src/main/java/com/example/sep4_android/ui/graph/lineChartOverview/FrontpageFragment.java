package com.example.sep4_android.ui.graph.lineChartOverview;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sep4_android.R;
import com.example.sep4_android.databinding.FragmentFrontpageBinding;

public class FrontpageFragment extends Fragment {

    private FrontpageViewModel mViewModel;
    private FragmentFrontpageBinding binding;
    private CardView cardView_Co2,cardView_temp,cardView_humidity;
private Button btn_Co2,btn_Temp,btn_Humidity;
    public static FrontpageFragment newInstance() {
        return new FrontpageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FrontpageViewModel.class);
        binding = FragmentFrontpageBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        bindings();
        //observers();
        onClickListeners();
        return root;
           }

    private void onClickListeners() {
        btn_Temp.setOnClickListener(view -> {
            System.out.println("Test virker knappen?");
            Navigation.findNavController(view).navigate(R.id.lineFragment);
        });
        btn_Co2.setOnClickListener(view -> {
            System.out.println("Test virker knappen?");
            Navigation.findNavController(view).navigate(R.id.co2Fragment);
        });
        btn_Humidity.setOnClickListener(view -> {
            System.out.println("Test virker knappen?");
            Navigation.findNavController(view).navigate(R.id.humidityFragment);
        });
    }

    private void bindings() {
        cardView_Co2 = binding.cardViewCo2;
        cardView_humidity = binding.cardViewHumidity;
        cardView_temp = binding.cardViewTemp;

        btn_Co2 = binding.btnCo2;
        btn_Humidity = binding.btnHumidity;
        btn_Temp = binding.btnTemp;

    }


}