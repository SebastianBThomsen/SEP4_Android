package com.example.sep4_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sep4_android.databinding.FragmentHomeBinding;
import com.example.sep4_android.model.DateHandler;
import com.example.sep4_android.model.persistence.entities.Measurement;
import com.example.sep4_android.repositories.HealthRepositoryWeb;
import com.google.android.material.datepicker.MaterialDatePicker;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private TextView textView;

    //Display data between timestamps
    private TextView startTime, endTime;
    private TextView tv_co2, tv_humidity, tv_temperature;

    private Button btn_submitTime;

    //DatePickers
    private MaterialDatePicker materialDatePickerStart, materialDatePickerEnd;
    private Button btn_startDate, btn_endDate;
    private long startDate, endDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModelImpl.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //FIXME: FIREBASE SKAL INDSÆTTES ET ELLER ANDET STED! - wupwup
        //FIXME: Display Average Temp, Co2, humidity between 2 timestamps!

        materialDatePickerStart = DateHandler.getMaterialDatePicker();
        materialDatePickerEnd = DateHandler.getMaterialDatePicker();

        bindings();
        observers();

        return root;
    }

    private void observers() {
        viewModel.getTestMeasurements().observe(getViewLifecycleOwner(), measurements -> {
            if(measurements!=null){
                String co2 = "";
                for (Measurement measurement: measurements) {
                    co2 += measurement.getCo2() + ", ";
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

        startTime = binding.tvStartTime;
        endTime = binding.tvEndTime;

        btn_submitTime = binding.btnSubmit;
        btn_submitTime.setOnClickListener(this::submitTime);

        btn_startDate = binding.btnStartDate;
        btn_endDate = binding.btnEndDate;

        btn_startDate.setOnClickListener(this::setStartDate);
        btn_endDate.setOnClickListener(this::setEndDate);


    }

    private void setStartDate(View view) {
        materialDatePickerStart.show(getActivity().getSupportFragmentManager(), "test");

        //When accepting chosen date, display in view!
        materialDatePickerStart.addOnPositiveButtonClickListener(selection -> {
            startDate = (Long) selection;
            startTime.setText(DateHandler.fromLongToString((Long) selection));
        });
    }

    private void setEndDate(View view) {
        materialDatePickerEnd.show(getActivity().getSupportFragmentManager(), "test");

        //When accepting chosen date, display in view!
        materialDatePickerEnd.addOnPositiveButtonClickListener(selection -> {
            endDate = (Long) selection;
            endTime.setText(DateHandler.fromLongToString((Long) selection));
        });
    }

    private void submitTime(View view) {
        viewModel.setTimestamp(startDate, endDate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}