package com.computerberry.AppNew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Date;

public class BudgetLeftActivity extends AppCompatActivity {


    //chi tiết ngân sách, cho biết các khoản chi tiêu có trên hay dưới ngân sách hhay không> nhấp vào số ngân sách hiện tại
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

        //lấy từ activity_budget_left.xml > giao diện hiển thị số tiền có thể sử dụng hàng ngày,hàng tuần,năm
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

        //phân tách số tiền ,thêm 2 số 0 VD 10000000 thì 10,000,000.00
        if (remaining < 0) {
            //cho biết khi số tiền chi tiêu có vượt quá ngân sách hiện có hay không
            budgetLeftTextView.setText(String.format("%,.0f", -remaining) + "VNĐ");
            budgetLeftTextView.setTextColor(getResources().getColor(R.color.deficit));
            underOverTextView.setText("trên ngân sách.");
        } else {
            ///cho biết khi số tiền chi tiêu có vượt quá ngân sách hiện có hay không
            budgetLeftTextView.setText(String.format("%,.0f", remaining) + "VNĐ");
            budgetLeftTextView.setTextColor(getResources().getColor(R.color.green));
            underOverTextView.setText("dưới ngân sách.");
        }

        //hiển thị ngày, thay thế bởi id TewtView dateTextView
        dateTextView.setText(MyDBHandler.DATE_FORMAT_LOGS.format(HomeActivity.thisUser.getNextBudgetStartDate()));
        float daysLeftInBudget = (float) Math.ceil((HomeActivity.thisUser.getNextBudgetStartDate().getTime() - new Date().getTime()) / 1000.0 / 60.0 / 60.0 / 24.0);
        String timePeriod = HomeActivity.thisUser.getTimePeriod();

        if (timePeriod.equals("1 Năm") || timePeriod.equals("3 Thángg")) {
            monthlyAmountTextView.setText(String.format("%,.0f", remaining / daysLeftInBudget * 30) + "VNĐ");
            monthlyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        } else {
            monthlyAmountTextView.setText("N/A");
            monthlyAmountTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        if (timePeriod.equals("1 Năm") || timePeriod.equals("3 Thángg") || timePeriod.equals("1 Tháng") || timePeriod.equals("2 Tuầnn")) {
            weeklyAmountTextView.setText(String.format("%,.0f", remaining / daysLeftInBudget * 7) + "VNĐ");
            weeklyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        } else {
            weeklyAmountTextView.setText("N/A");
            weeklyAmountTextView.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        //chi tieu 24 giờ một lần
        if (!timePeriod.equals("24 Giờ") && !timePeriod.equals("15 Seconds")) {
            //remaining = 10,000,000/dáyLeftInBudgert
            dailyAmountTextView.setText(String.format("%,.0f", remaining / daysLeftInBudget * 1) + " VNĐ");
            dailyAmountTextView.setTextColor(getResources().getColor(R.color.green));
        } else {
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
