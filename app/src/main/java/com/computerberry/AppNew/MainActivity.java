package com.computerberry.AppNew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button addButton;
    Button removeButton;
    EditText inputEditText;
    TextView resultsTextView;
    TagDBHandler dbHandler;
    Button secondActivityButton;
    TextView welcomeTextView;

    TextView previouslyInitTextView;
    Button previouslyInitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("DigiMoney\\MainActivity");

        //tham chiếu vùng

        removeButton = (Button) findViewById(R.id.removeButton);
        inputEditText = (EditText) findViewById(R.id.amountEditText);
        resultsTextView = (TextView) findViewById(R.id.resultsTextView);
        dbHandler = new TagDBHandler(this, null, null, 1);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        previouslyInitTextView = (TextView) findViewById(R.id.previouslyInitTextView);
        previouslyInitButton = (Button) findViewById(R.id.previouslyInitButton);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        //end tham chiếu vùng

        //in thông tin từ bảng SQLite
        printDatabase();

        String firstName = HomeActivity.thisUser.getFirstName();
        welcomeTextView.setText("Chào mừng bạn, " + firstName + "!");

        removeButton.setEnabled(false);

        //khu vực Nút Khi nhấp chuột

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.dequeue();
                printDatabase();
            }
        });

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(startIntent);
            }
        });

        //endregion

    } // end of onCreate

    //in cơ sử dữ liệu
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        resultsTextView.setText(dbString);
        inputEditText.setText("");
    }

    //hoàn thành > finish đóng
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
