package com.example.sep4_android.ui.graph.lineCharts.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sep4_android.R;
import com.google.android.material.navigation.NavigationView;

public class FrontpageFragment extends Fragment {

    //FIXME: Bruges ikke?
    private FrontpageViewModel mViewModel;
    private FragmentFrontpageBinding binding;
    private Button btn_Co2, btn_Temp, btn_Humidity;
private Toolbar mToolbar;
private NavigationView navigationView;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FrontpageViewModel.class);
        binding = FragmentFrontpageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        bindings();
        //observers();
        onClickListeners();

        return root;
    }

    private void onClickListeners() {
        btn_Temp.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.temperatureDetailFragment);
        });
        btn_Co2.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.co2DeatilFragment);
        });
        btn_Humidity.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.humidityDeatilFragment);
        });
    }

    private void bindings() {
        btn_Co2 = binding.btnCo2;
        btn_Humidity = binding.btnHumidity;
        btn_Temp = binding.btnTemp;
    }



public void updateNavbar(){
     navigationView = (NavigationView) navigationView.findViewById(R.id.nav_view);
     View headerView = navigationView.getChildAt(3);

}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}