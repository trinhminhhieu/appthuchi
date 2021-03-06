package com.computerberry.AppNew;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    //biểu đồ phân tích các khoản chi tiêu
    PieChart pieChart;

    ConstraintLayout myLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_analysis);
        getSupportActionBar().hide();

        setupPieChart();

    }

    //biểu đồ chi tiêu lấy từ mã nguồn mở com.github.PhilJay:MPAndroidChart:v3.0.3
    private void setupPieChart(){
        //Populate a list of pieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        HashMap<String, Float> map = new HashMap<>();
        List<String> hexCodes = new ArrayList<>();
        for (int i = 0; i < HomeActivity.entries.size(); i++){
            if (!map.containsKey(HomeActivity.entries.get(i).get_tag().getText())){
                hexCodes.add("#" + HomeActivity.entries.get(i).get_tag().getCol());
                map.put(HomeActivity.entries.get(i).get_tag().getText(), 0.0f);
            }
            map.put(HomeActivity.entries.get(i).get_tag().getText(), map.get(HomeActivity.entries.get(i).get_tag().getText()) + HomeActivity.entries.get(i).get_value());
        }
        String[] cols = hexCodes.toArray(new String[hexCodes.size()]);
        for (String s : map.keySet()){
            pieEntries.add(new PieEntry(map.get(s), s));
        }
        int[] colors = new int[cols.length];
        for (int i = 0; i < colors.length; i ++){
            colors[i] = Color.parseColor(cols[i]);
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextSize(16f);
        dataSet.setSliceSpace(3);
        PieData data = new PieData(dataSet);

        //xuất dữ liệu ra biểu đồ
        pieChart = (PieChart) findViewById(R.id.pieChart);
        pieChart.setData(data);
        pieChart.setCenterText("Tổng chi tiêu của bạn");
        pieChart.animateY(1000);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
