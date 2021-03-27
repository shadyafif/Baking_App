package com.example.baking_app_master.activities;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.baking_app_master.R;

public class SplashActivity extends AppCompatActivity {
    private static int splashInterval = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {


                ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cManager.getActiveNetworkInfo();
                if (nInfo != null && nInfo.isConnected()) {

                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    this.finish();

                } else {
                    Toast.makeText(SplashActivity.this, "بعد إذنك افحص شبكة الأنترنت", Toast.LENGTH_SHORT).show();


                    this.finish();
                }
            }

            private void finish() {


            }
        }, splashInterval);


    }
}
