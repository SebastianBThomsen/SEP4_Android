package com.example.sep4_android.ui.graph.DesignForGraph;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraphDesign {

    public void setAvg(LineChart lineChart, Float sum1) {
        LimitLine llXAxis = new LimitLine(sum1,"Average");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(15f);
        llXAxis.setTextColor(Color.rgb(255,255,255));
        YAxis xAxis = lineChart.getAxisLeft();
        xAxis.addLimitLine(llXAxis); // add x-axis limit line
        xAxis.enableGridDashedLine(10f, 10f, 0f);
    }

    public void lineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setValueTextSize(16f);

        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setColor(Color.rgb(76, 175, 80));
        lineDataSet.setFillColor(Color.rgb(76, 175, 80));
    }

    public void lineData(LineData lineData) {
        lineData.setDrawValues(false);
    }

    public void lineChartDesign(LineChart lineChart) {
        lineChart.fitScreen();
        lineChart.setScaleEnabled(true);
        lineChart.getDescription().setEnabled(false);
        lineChart.setBackgroundColor(Color.rgb(37, 36, 36));//Set as a gray
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setTextColor(Color.parseColor("#ffffff")); // left y-axis
        lineChart.getAxisRight().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getXAxis().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getLegend().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getDescription().setTextColor(Color.parseColor("#ffffff"));
        lineChart.getData().setValueTextColor(Color.parseColor("#ffffff"));
        lineChart.setBackgroundColor(Color.rgb(37, 36, 36));//Set as a gray
    }

    public void compareLineChartDesign(LineChart lineChart) {
        lineChartDesign(lineChart);

    }

    public void compareLineDataSet(LineDataSet lineDataSet) {
        lineDataSet(lineDataSet);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawCircles(true);
    }
}

