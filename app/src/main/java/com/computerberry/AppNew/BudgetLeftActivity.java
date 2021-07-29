package com.computerberry.AppNew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Date;

public class BudgetLeftActivity extends AppCompatActivity {

    TextView budgetLeftTextView;
    TextView dailyAmountTextView;
    TextView weeklyAmountTextView;
    TextView monthlyAmountTextView;
    TextView dateTextView;
    TextView underOverTextView;
    TextView textView100;
    TextView textView101;
    TextView textView102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_budget_left);
        getSupportActionBar().hide();

        budgetLeftTextView = (TextView) findViewById(R.id.valueTextViewBudgetLeftActivity);
        dailyAmountTextView = (TextView) findViewById(R.id.dailyAmountTextView);
        weeklyAmountTextView = (TextView) findViewById(R.id.weeklyAmountTextView);
        monthlyAmountTextView = (TextView) findViewById(R.id.monthlyAmountTextView);
        underOverTextView = (TextView) findViewById(R.id.underOverTextViewBudgetLeftActivity);
        dateTextView = (TextView) findViewById(R.id.dateTextViewBudgetLeftActivity);
        textView100 = (TextView) findViewById(R.id.textView100);
        textView101 = (TextView) findViewById(R.id.textView101);
        textView102 = (TextView) findViewById(R.id.textView102);

        textView100.setTextColor(getResources().getColor(R.color.lightSeaGreen));
        textView101.setTextColor(getResources().getColor(R.color.mediumSeaGreen));
        textView102.setTextColor(getResources().getColor(R.color.darkSeaGreen));

        dateTextView = (TextView) findViewById(R.id.dateTextViewBudgetLeftActivity);
        Float remaining = HomeActivity.thisUser.getBudget() - HomeActivity.amountSpent;
        if (remaining < 0){
            budgetLeftTextView.setText(String.format("%.2f", -remaining)+"VNĐ");
            budgetLeftTextView.setTextColor(getResources().getColor(R.color.deficit));
            underOverTextView.setText("trên ngân sách.");
        }
        else{
            budgetLeftTextView.setText(String.format("%.2f", remaining)+"VNĐ");
            budgetLeftTextView.setTextColor(getResources().getColor(R.color.green));
            underOverTextView.setText("dưới ngân sách.");
        }
        dateTextView.setText(MyDBHandler.DATE_FORMAT_LOGS.format(HomeActivity.thisUser.getNextBudgetStartDate()));
        float daysLeftInBudget = (float) Math.ceil(( HomeActivity.thisUser.getNextBudgetStartDate().getTime() - new Date().getTime())/1000.0/60.0/60.0/24.0);
        String timePeriod = HomeActivity.thisUser.getTimePeriod();
        if (timePeriod.equals("1 Năm") || timePeriod.equals("3 Tháng")){
            monthlyAmountTextView.setText(String.format("%.2f", remaining/daysLeftInBudget * 30) +"VNĐ");
            monthlyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            monthlyAmountTextView.setText("N/A");
            monthlyAmountTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        if (timePeriod.equals("1 Năm") || timePeriod.equals("3 Tháng") || timePeriod.equals("1 Tháng")  || timePeriod.equals("2 Tuần")){
            weeklyAmountTextView.setText(String.format("%.2f", remaining/daysLeftInBudget * 7)+"VNĐ");
            weeklyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            weeklyAmountTextView.setText("N/A");
            weeklyAmountTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        if (!timePeriod.equals("24 giờ") && !timePeriod.equals("15 Giây")){
            dailyAmountTextView.setText(String.format("%.2f", remaining/daysLeftInBudget * 1)+"VNĐ");
            dailyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        }
        else{
            dailyAmountTextView.setText("N/A");
            dailyAmountTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
