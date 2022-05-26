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
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    private FragmentHomeBinding binding;
    private TextView textView;

    //Display data between timestamps
    private TextView startTime, endTime;
    private TextView tv_avgTemp, tv_avgCO2, tv_avgHumidity;
    private TextView tv_maxTemp, tv_maxCO2, tv_maxHumidity;
    private TextView tv_minTemp, tv_minCO2, tv_minHumidity;

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
                //Ugly way to get average, max and min!
                double co2Avg = 0;
                double tempAvg = 0;
                double humidityAvg = 0;

                double co2Max = measurements.get(0).getCo2();
                double tempMax = measurements.get(0).getTemperature();
                double humidityMax = measurements.get(0).getHumidity();

                double co2Min = measurements.get(0).getCo2();
                double tempMin = measurements.get(0).getTemperature();;
                double humidityMin = measurements.get(0).getHumidity();;

                for (Measurement measurement: measurements) {
                    co2Avg += measurement.getCo2();
                    tempAvg += measurement.getTemperature();
                    humidityAvg += measurement.getHumidity();

                    if(measurement.getCo2()>co2Max)
                        co2Max = measurement.getCo2();
                    if(measurement.getTemperature()>tempMax)
                        tempMax = measurement.getTemperature();
                    if(measurement.getHumidity()>humidityMax)
                        humidityMax = measurement.getHumidity();

                    if(measurement.getCo2()<co2Min)
                        co2Min = measurement.getCo2();
                    if(measurement.getTemperature()<tempMin)
                        tempMin = measurement.getTemperature();
                    if(measurement.getHumidity()<humidityMin)
                        humidityMin = measurement.getHumidity();
                }
                //Getting average!
                co2Avg /= measurements.size();
                tempAvg /= measurements.size();
                humidityAvg /= measurements.size();

                DecimalFormat df = new DecimalFormat("##.0");
                tv_avgCO2.setText(""+df.format(co2Avg));
                tv_avgHumidity.setText(""+df.format(humidityAvg));
                tv_avgTemp.setText(""+df.format(tempAvg));

                tv_minCO2.setText(""+co2Min);
                tv_minHumidity.setText(""+humidityMin);
                tv_minTemp.setText(""+tempMin);

                tv_maxCO2.setText(""+co2Max);
                tv_maxHumidity.setText(""+humidityMax);
                tv_maxTemp.setText(""+tempMax);
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
        //Average
        tv_avgTemp = binding.tvAvgTemp;
        tv_avgHumidity = binding.tvAvgHumidity;
        tv_avgCO2 = binding.tvAvgCO2;

        //Max
        tv_maxTemp = binding.tvMaxTemp;
        tv_maxHumidity = binding.tvMaxHumidity;
        tv_maxCO2 = binding.tvMaxCO2;

        //Min
        tv_minTemp = binding.tvMinTemp;
        tv_minHumidity = binding.tvMinHumidity;
        tv_minCO2 = binding.tvMinCO2;

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