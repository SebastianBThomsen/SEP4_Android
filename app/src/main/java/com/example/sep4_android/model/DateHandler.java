package com.example.sep4_android.model;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    public static String fromLongToString(long ms){
        Date date = new Date(ms);
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy - hh:mm:ss");
        return dateFormat.format(date);
    }

    public static long fromStringToLong(String date)
    {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");

        long milliseconds = 0;
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public static MaterialDatePicker getMaterialDatePicker(){
        //Material Design
        //https://material.io/components/date-pickers/android#using-date-pickers

        //Setting calenderConstraints, so you can scroll to month after current!
        CalendarConstraints.Builder calenderConstraint = new CalendarConstraints.Builder();
        calenderConstraint.setEnd(MaterialDatePicker.todayInUtcMilliseconds());

        //Setting calenderConstraint validator, so a date beyond current date + 1 day cannot be chosen!
        CalendarConstraints.DateValidator dateValidatorMax = DateValidatorPointBackward.before(MaterialDatePicker.todayInUtcMilliseconds()+86400000);
        calenderConstraint.setValidator(dateValidatorMax);

        MaterialDatePicker materialDatePicker =
                MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setCalendarConstraints(calenderConstraint.build())
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
                        .build();

        return materialDatePicker;
    }
}
