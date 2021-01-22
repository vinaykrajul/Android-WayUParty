package com.acculytixs.mobile.wayuparty.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import com.acculytixs.mobile.wayuparty.R;
import com.koushikdutta.ion.Ion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        imageView = findViewById(R.id.splash_image);
        Ion.with(imageView).load("file:///android_asset/splash.gif");

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.side_in_left,R.anim.side_in_right);
                finish();
            }
        }, 4000);
    }
}
