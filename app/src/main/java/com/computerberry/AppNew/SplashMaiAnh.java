package com.computerberry.AppNew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashMaiAnh extends AppCompatActivity {

    //giao diện khi mở ứng dụng
    private final int SLEEP_TIMER = 8000; //thời gian chờ 8 giây

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_mai_anh);

        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }

    private class LogoLauncher extends Thread {
        public void run() {
            String message = "";

            //thử-bắt lỗi
            try {
                FileInputStream fileInputStream = openFileInput("user_info");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                while ((message = bufferedReader.readLine()) != null) {
                    stringBuffer.append(message);
                }
                message = stringBuffer.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(SLEEP_TIMER); //thời gian ngủ sau 8 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent startIntent;

            if (message.equals("")) {
                 startIntent = new Intent(SplashMaiAnh.this, FirstTimeActivity.class); //nếu lỗi trả về giao diện lỗi

            } else {
                 startIntent = new Intent(SplashMaiAnh.this, HomeActivity.class);  //nếu k có vấn đề gì trở lại trang chủ
            }


            startActivity(startIntent);
            SplashMaiAnh.this.finish(); //pha huy activity> back k hoat dong để ngăn splash chạy lại>
            ///// chỉ khi người dùng đóng app thì lần tiếp theo khi mở splash sẽ lại chạy
        }
    }
}