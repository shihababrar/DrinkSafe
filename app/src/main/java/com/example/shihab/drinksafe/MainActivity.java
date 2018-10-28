package com.example.shihab.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = findViewById(R.id.splash_screen_progress_bar);
        new Thread(new Runnable() {
            public void run() {
                doWork();
                startApp();
                finish();
            }
        }).start();
    }
    private void doWork() {
        for (int progress = 0; progress < 100; progress += 10) {
            try {
                Thread.sleep(300);
                mProgress.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
               // Log.e("Error", getMessage().toString());
            }
        }
    }

    private void startApp() {
        Intent intent = new Intent(MainActivity.this, ImageSelection.class);
        startActivity(intent);
    }
}
