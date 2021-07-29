package com.computerberry.AppNew;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class FirstTimeInfoActivity extends AppCompatActivity {

    CardView onwardsButton;
    TextView onwardsButtonTextView;
    Spinner timePeriodSpinner;
    RadioButton saveMoneyRadioButton;
    RadioButton maintainABudgetRadioButton;
    EditText budgetEditText;


    public String firstName;
    public String lastName;
    Context c;
    //firstname(String),secondname(String),saveMoney(Bool),budgetReset(String),budget(Float),appSetupDate(String),currentBudgetStartDate(String),nextBudgetDate(String)
    public static final int FIRST_NAME = 0;
    public static final int LAST_NAME = 1;
    public static final int SAVE_MONEY = 2;
    public static final int BUDGET_RESET = 3;
    public static final int BUDGET = 4;
    public static final int APP_SETUP_DATE = 5;
    public static final int CURRENT_BUDGET_START_DATE = 6;
    public static final int NEXT_BUDGET_DATE = 7;

    public static Tag[] defaultTags = {
                            new Tag("9E9E9E", "Khác"),
                            new Tag("9E9E9E", "Thu nhập"),
                            new Tag("FFEB3B", "Quà tặng"),
                            new Tag("FFEB3B", "Quyên góp"),
                            new Tag("795548", "Thuế"),
                            new Tag("795548", "Chuyển hàng"),
                            new Tag("795548", "In ấn"),
                            new Tag("795548", "Văn phòng phẩm"),
                            new Tag("795548", "Đỗ xe"),
                            new Tag("795548", "Gas"),
                            new Tag("4CAF50", "Chuyến bay"),
                            new Tag("4CAF50", "Tàu hoả"),
                            new Tag("607D8B", "Uber"),
                            new Tag("009688", "Thuê"),
                            new Tag("009688", "Tiện ích"),
                            new Tag("009688", "Nhà trẻ"),
                            new Tag("009688", "Điện thoại"),
                            new Tag("03A9F4", "Sách giáo khoa"),
                            new Tag("03A9F4", "Học phí"),
                            new Tag("3F51B5", "Bác sĩ"),
                            new Tag("3F51B5", "Nha sĩ"),
                            new Tag("3F51B5", "Gym"),
                            new Tag("9C27B0", "Tóc"),
                            new Tag("9C27B0", "Phụ kiện"),
                            new Tag("9C27B0", "Quần áo"),
                            new Tag("E91E63", "Sách"),
                            new Tag("E91E63", "Trò chơi điện tử"),
                            new Tag("E91E63", "Phim"),
                            new Tag("E91E63", "Âm nhạc"),
                            new Tag("E91E63", "Sở thích"),
                            new Tag("F44336", "Cửa hàng tạp hóa"),
                            new Tag("F44336", "Xăng"),
                            new Tag("F44336", "Đồ ăn nhanh"),
                            new Tag("F44336", "Mua xe"),
                            new Tag("F44336", "Rượu")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_time_info);
        getSupportActionBar().hide();
        getSupportActionBar().setTitle("Thêm ngân sách mới");

        budgetEditText = (EditText) findViewById(R.id.budgetEditText);
        onwardsButton = (CardView) findViewById(R.id.onwardsButton);
        onwardsButtonTextView = (TextView) findViewById(R.id.onwardsButtonTextView);
        final EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        final EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);

        timePeriodSpinner = (Spinner) findViewById(R.id.timePeriodSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.times_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timePeriodSpinner.setAdapter(adapter);

        saveMoneyRadioButton = (RadioButton) findViewById(R.id.saveMoneyRadioButton);
        maintainABudgetRadioButton = (RadioButton) findViewById(R.id.maintainABudgetRadioButton);
        maintainABudgetRadioButton.setChecked(true);
        saveMoneyRadioButton.setEnabled(false);

        c = this;

        if (getIntent().hasExtra("com.computerberry.AppNew.INFO")){
            onwardsButtonTextView.setText("Lưu cài đặt");
            getSupportActionBar().setTitle("Cài đặt");

            try{
                String message = "";
                FileInputStream fileInputStream = openFileInput("user_info");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while ((message = bufferedReader.readLine()) != null){
                    stringBuffer.append(message);
                }
                String result = stringBuffer.toString();
                String strArr[] = result.split(",");

                firstNameEditText.setText(strArr[FIRST_NAME]);
                lastNameEditText.setText(strArr[LAST_NAME]);

                if(Boolean.parseBoolean(strArr[SAVE_MONEY])){
                    saveMoneyRadioButton.setChecked(true);
                    maintainABudgetRadioButton.setChecked(false);
                }
                else{
                    saveMoneyRadioButton.setChecked(false);
                    maintainABudgetRadioButton.setChecked(true);
                }
                for(int i= 0; i < timePeriodSpinner.getAdapter().getCount(); i++)
                {
                    if(timePeriodSpinner.getAdapter().getItem(i).toString().contains(strArr[3]))
                    {
                        timePeriodSpinner.setSelection(i);
                        break;
                    }
                }
                budgetEditText.setText(strArr[BUDGET]);

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        onwardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                String resetTimePeriod = timePeriodSpinner.getSelectedItem().toString();
                String budget = budgetEditText.getText().toString();
                String m = "";
                if (!firstName.equals("") &&
                        !lastName.equals("") &&
                        (saveMoneyRadioButton.isChecked() || maintainABudgetRadioButton.isChecked()) &&
                        !budget.equals("")){

                         try {
                            FileInputStream fileInputStream = openFileInput("user_info");
                            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            while ((m = bufferedReader.readLine()) != null){
                                stringBuffer.append(m);
                            }
                            m = stringBuffer.toString();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        if (m.equals("")){
                            TagDBHandler tagDBHandler = new TagDBHandler(c, null, null, 1);
                            for (Tag t : defaultTags){
                                tagDBHandler.addEntry(t);
                            }
                        }

                    //STORE AS:
                    /*
                    user_info

                        firstname(String),secondname(String),saveMoney(Bool),budgetReset(String),budget(Float),appSetupDate(String),currentBudgetStartDate(String),nextBudgetDate(String)

                     */
                    float budgetFloat = Float.parseFloat(budget);

                    if (m.equals("")){ //THIS IS THE FIRST TIME THISUSER IS BEING CREATED
                        Date nextBudgetDate = new Date();
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(nextBudgetDate);
                        createNextBudgetDate(cal, resetTimePeriod);
                        nextBudgetDate = cal.getTime();

                        HomeActivity.thisUser = new User(firstName, lastName, saveMoneyRadioButton.isChecked(), resetTimePeriod, budgetFloat, new Date(), new Date(), nextBudgetDate);
                    }
                    else{ //THIS USER HAS BEEN PREVIOUSLY CREATED
                        String[] mArray = m.split(",");
                        if (!budget.equals(mArray[BUDGET]) || !resetTimePeriod.equals(mArray[BUDGET_RESET])){ //IF there is a change between this budget and last budget
                            Date nextBudgetDate = new Date();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(nextBudgetDate);
                            createNextBudgetDate(cal, resetTimePeriod);
                            nextBudgetDate = cal.getTime();
                            Date appCreatedDate = new Date();
                            try{
                                appCreatedDate = MyDBHandler.DATE_FORMAT_CALENDAR.parse(mArray[APP_SETUP_DATE]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            HomeActivity.thisUser = new User(firstName, lastName, saveMoneyRadioButton.isChecked(), resetTimePeriod, budgetFloat,appCreatedDate, new Date(), nextBudgetDate);
                        }
                        else {
                            Date appCreatedDate = new Date();
                            Date budgetStartDate = new Date();
                            Date nextBudgetDate = new Date();
                            try {
                                appCreatedDate = MyDBHandler.DATE_FORMAT_CALENDAR.parse(mArray[APP_SETUP_DATE]);
                                budgetStartDate = MyDBHandler.DATE_FORMAT_CALENDAR.parse(mArray[CURRENT_BUDGET_START_DATE]);
                                nextBudgetDate = MyDBHandler.DATE_FORMAT_CALENDAR.parse(mArray[NEXT_BUDGET_DATE]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            HomeActivity.thisUser = new User(firstName, lastName, saveMoneyRadioButton.isChecked(), resetTimePeriod, budgetFloat, appCreatedDate, budgetStartDate, nextBudgetDate);
                        }
                    }


                    String message = "" + HomeActivity.thisUser.getFirstName() + "," +
                            HomeActivity.thisUser.getLastName() + "," +
                            HomeActivity.thisUser.isSaveMoney() + "," +
                            HomeActivity.thisUser.getTimePeriod() + "," +
                            HomeActivity.thisUser.getBudget() + "," +
                            HomeActivity.thisUser.getAppSetupDate() + "," +
                            HomeActivity.thisUser.getCurrentBudgetStartDate() + "," +
                            HomeActivity.thisUser.getNextBudgetStartDate();

                    String file_name = "user_info";
                    try {
                        FileOutputStream fileOutputStream = openFileOutput(file_name, MODE_PRIVATE);
                        fileOutputStream.write(message.getBytes());
                        fileOutputStream.close();
                        Snackbar snackbar = Snackbar.make(view, "Thông tin người dùng đã lưu/cập nhật", Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        TextView sbTextView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                        sbTextView.setTextColor(getResources().getColor(R.color.green));
                        snackbar.show();
                        new CountDownTimer(700, 700) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                // do something after 1s
                            }

                            @Override
                            public void onFinish() {
                                Intent startMainActivity = new Intent(FirstTimeInfoActivity.this, HomeActivity.class);
                                startActivity(startMainActivity);
                                FirstTimeInfoActivity.this.finish();
                            }

                        }.start();
                        //Toast.makeText(getApplicationContext(), "User Info Saved/Updated", Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Intent startMainActivity = new Intent(FirstTimeInfoActivity.this, HomeActivity.class);
//                    startActivity(startMainActivity);
//                    FirstTimeInfoActivity.this.finish();
                }
                else{
                    Snackbar snackbar = Snackbar.make(view, "Một hoặc nhiều trường không hợp lệ!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

            }
        });
    }

    private void createNextBudgetDate(Calendar cal, String resetTimePeriod){
        if (resetTimePeriod.equals("24 Giờ")){
            cal.add(Calendar.DATE, 1);
        } else if (resetTimePeriod.equals("15 Giây")) {
            cal.add(Calendar.SECOND, 15);
        } else if (resetTimePeriod.equals("3 Ngày")){
            cal.add(Calendar.DATE, 3);
        } else if (resetTimePeriod.equals("1 Tuần")){
            cal.add(Calendar.DATE, 7);
        } else if (resetTimePeriod.equals("2 Tuần")){
            cal.add(Calendar.DATE, 14);
        } else if (resetTimePeriod.equals("1 Tháng")){
            cal.add(Calendar.MONTH, 1);
        } else if (resetTimePeriod.equals("3 Tháng")){
            cal.add(Calendar.MONTH, 3);
        } else {
            cal.add(Calendar.YEAR, 1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        c = this;
    }

    @Override
    public void finish(){
        super.finish();
        if (getIntent().hasExtra("com.computerberry.AppNew.INFO")) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    public static void main(String[] args){
        ArrayList<Tag> t = new ArrayList<>();


        Tag tag = new Tag("FFF", "white");
        t.add(tag);
        System.out.println(t.contains(tag));
    }
}
