package com.example.sep4_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHomeBinding;
import com.example.sep4_android.model.persistence.entities.Measurement;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private TextView textView;

    //Display data between timestamps
    private EditText startTime, endTime;
    private TextView tv_co2, tv_humidity, tv_temperature;

    private Button btn_submitTime;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModelImpl.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //FIXME: FIREBASE SKAL INDSÆTTES ET ELLER ANDET STED! - wupwup
        //FIXME: Display Average Temp, Co2, humidity between 2 timestamps!

        bindings();
        observers();
        return root;
    }

    private void observers() {
        viewModel.getHealthDataBetweenTimestampsLocal("1652876333", "1652876666").observe(getViewLifecycleOwner(), measurements -> {
            if(measurements!=null){
                String co2 = "";
                for (Measurement measurement: measurements) {
                    co2 += measurement.getCo2();
                }
                tv_co2.setText(co2);
            }

            //Average Temp
            //Observe på dennne under i stedet!
            //viewModel.getAverageMeasurement()
            /* tv_co2.setText("" + measurement.getCo2());
            tv_humidity.setText("" + measurement.getHumidity());
            tv_temperature.setText("" + measurement.getTemperature());
            textView.setText(measurement.getTimestampString());

             */
        });
    }

    private void bindings() {
        textView = binding.textHome;

        //Displaying data between timestamps
        tv_co2 = binding.tvCo2;
        tv_temperature = binding.tvTemperatures;
        tv_humidity = binding.tvHumidity;

        startTime = binding.editTextStartTime;
        endTime = binding.editTextEndTime;

        btn_submitTime = binding.btnSubmit;
        btn_submitTime.setOnClickListener(this::submitTime);
    }

    private void submitTime(View view) {
        viewModel.getHealthDataBetweenTimestampsLocal(startTime.getText().toString(), endTime.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}