package com.computerberry.AppNew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LogsActivity extends AppCompatActivity {

//lớp nhật ký ngân sách
    ListView logsListView;
    String[] items;
    String[] amounts;
    Tag[] tags;
    Boolean[] newBool;
    String colors[];
    int budgetDayNum;
    Date cur;
    String[] rotation = {"ffcdd2", "f8bbd0", "e1bee7", "d1c4e9", "c5cae9", "bbdefb", "b3e5fc", "b2ebf2",
                        "b2dfdb", "c8e6c9", "dcedc8", "f0f4c3", "fff9c4", "ffecb3", "ffe0b2", "ffccbc"};
    //https://material.io/tools/color/#!/?view.left=0&view.right=0&primary.color=FFCDD2 column 2 (100)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logs);

        getSupportActionBar().setTitle("Nhật ký ngân sách của bạn\n");
        getSupportActionBar().hide();

        logsListView = (ListView) findViewById(R.id.logsListView);

        //Điền mảng
        int numEntries = HomeActivity.entries.size();

        items = new String[numEntries];
        amounts = new String[numEntries];
        newBool = new Boolean[numEntries];
        tags = new Tag[numEntries];
        colors = new String[numEntries];
        ArrayList<Entry> entries = HomeActivity.entries;
        Collections.reverse(entries);

        //vòng lặp danh mục
        for (int i = 0; i < numEntries; i++){
            Entry e = entries.get(i);
            tags[i] = e.get_tag();

            if (i == 0){
                budgetDayNum = (int) (Math.random() * rotation.length);
                cur = e.get_date();
            } else{
                if (!DateUtils.isSameDay(e.get_date(), cur)){
                    budgetDayNum += 1;
                    budgetDayNum %= rotation.length - 1;
                    cur = e.get_date();
                }
            }
            colors[i] = rotation[budgetDayNum];

            if (e.get_date().after(new Date(System.currentTimeMillis() - 21600000)) && e.get_date().before(new Date())) {
                if (e.get_date().after((new Date(System.currentTimeMillis() - 60000)))){
                    items[i] = (new Date().getTime() - e.get_date().getTime()) / 1000 + " Giây Trước";
                } else if (e.get_date().after((new Date(System.currentTimeMillis() - 3600000)))) {
                    items[i] = (new Date().getTime() - e.get_date().getTime()) / 1000 / 60 + " Phút Trước";
                } else {
                    items[i] = (new Date().getTime() - e.get_date().getTime()) / 1000 / 60 / 60 + " Giờ Trước";
                }
            }
            else if (DateUtils.isSameDay(e.get_date(), new Date())){
                items[i] = "Hôm nay - " + MyDBHandler.DATE_FORMAT_TIME.format(e.get_date());
            } else if (DateUtils.isSameDay(e.get_date(), new Date(System.currentTimeMillis() - 86400000))){ //24 hours in ms
                items[i] = "Hôm qua - " + MyDBHandler.DATE_FORMAT_TIME.format(e.get_date());
            } else if (DateUtils.isSameDay(e.get_date(), new Date(System.currentTimeMillis() + 86400000))){
                items[i] = "Ngày mai - " + MyDBHandler.DATE_FORMAT_TIME.format(e.get_date());
            } else {
                items[i] = MyDBHandler.DATE_FORMAT_LOGS.format(e.get_date());
            }

            if (e.get_value() < 0){
                amounts[i] = "-" + String.format("%,.2f", -e.get_value()) + "VNĐ";
            }
            else {
                amounts[i] = "+" + String.format("%,.2f", e.get_value()) + "VNĐ";
            }
            newBool[i] = (e.get_date().before(new Date()))&&(HomeActivity.now.getTime() - e.get_date().getTime() < 1000 * 60 * 60); //1000ms * 60sec * 60min = 1 Hour
        }

        ItemAdapter itemAdapter = new ItemAdapter(this, items, amounts, newBool, tags, colors);
        logsListView.setAdapter(itemAdapter);

        logsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showDetailActivity = new Intent(getApplicationContext(), DetailActivity.class);
                showDetailActivity.putExtra("com.computerberry.AppNew.ITEM_INDEX", i);
                startActivity(showDetailActivity);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public static void main(String[] args){
        String[] rotation = {"ffcdd2", "f8bbd0", "e1bee7", "d1c4e9", "c5cae9", "bbdefb", "b3e5fc", "b2ebf2",
                "b2dfdb", "c8e6c9", "dcedc8", "f0f4c3", "fff9c4", "ffecb3", "ffe0b2", "ffccbc"};
    for (int i =0 ; i< 100000; i++){
        int a = (int) (Math.random() * rotation.length);


    }
    }


}
