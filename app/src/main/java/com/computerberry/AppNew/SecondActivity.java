package com.computerberry.AppNew;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    //sự kiện nhấp chuột tại trang thông tin về app> sẽ chuyển hướng người dùng đến liên kết đã chèn vào button

    CardView websiteButton;
    CardView githubButton;
    CardView GPlayButton;
    ImageView BSJ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();


        websiteButton = (CardView) findViewById(R.id.websiteButton);
        githubButton = (CardView) findViewById(R.id.githubButton);
        GPlayButton = (CardView) findViewById(R.id.GPlayButton);
        BSJ = (ImageView) findViewById(R.id.BSJImageView);
        final MediaPlayer BSJMP = MediaPlayer.create(this, R.raw.thanks);

        BSJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BSJMP.start();
            }
        });

        //chuyển hướng đến website> nút Website
        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webaddress = Uri.parse("https://www.hieusoft.com");

                Intent launchWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                if (launchWebsite.resolveActivity(getPackageManager()) != null){
                    startActivity(launchWebsite);
                }
            }
        });

        //chuyern hướng app store> nút Ios
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webaddress = Uri.parse("https://github.com/trinhminhhieu");

                Intent launchWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                if (launchWebsite.resolveActivity(getPackageManager()) != null){
                    startActivity(launchWebsite);
                }
            }
        });

        //chuyern huong đến goohle play > nút Android
        GPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webaddress = Uri.parse("https://play.google.com/store");

                Intent launchWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                if (launchWebsite.resolveActivity(getPackageManager()) != null){
                    startActivity(launchWebsite);
                }
            }
        });

    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
