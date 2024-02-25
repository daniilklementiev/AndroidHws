package com.example.hws;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = findViewById(R.id.lineChart);

        ArrayList<Entry> bitcoinPrices = new ArrayList<>();
        bitcoinPrices.add(new Entry(0, 10000)); // Добавьте данные о курсе биткоина за каждый год
        bitcoinPrices.add(new Entry(1, 15000));
        bitcoinPrices.add(new Entry(2, 20000));
        bitcoinPrices.add(new Entry(3, 25000));
        bitcoinPrices.add(new Entry(4, 30000));

        LineDataSet lineDataSet = new LineDataSet(bitcoinPrices, "Bitcoin Price");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);

        Description description = new Description();
        description.setText("Bitcoin Price over 5 years");
        lineChart.setDescription(description);
        lineChart.animateXY(2000, 2000);
        lineChart.invalidate();
    }
}


