package com.example.shihab.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTips extends AppCompatActivity {
    TextView tvEnglish,tvBangla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips);
        tvEnglish = findViewById(R.id.textViewShowTipsEnglish);
        tvBangla = findViewById(R.id.textViewShowTipsBangla);

        tvEnglish.setText("Tips:\nYou can remove arsenic from your water by using a water cooler with reverse osmosis built-in, a process that forces water through a semi-permeable membrane under pressure that will leave contaminants behind and dispense more pure and healthy drinking water.");
        tvBangla.setText("\nপরামর্শ:\nরিভার্স অসিমোসিস বিল্ট ইন দিয়ে পানির কুলার ব্যবহার করে আপনি পানি থেকে আর্সেনিক সরিয়ে ফেলতে পারেন, একটি প্রক্রিয়া যা চাপের মধ্যে একটি আধা-বহনযোগ্য ঝিল্লি দিয়ে পানি সঞ্চার করে যা দূষণকারীকে পিছনে ফেলে দেবে এবং বিশুদ্ধ ও সুস্বাস্থ্যের পানীয় জল বিতরণ করবে।");

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ShowTips.this,ImageSelection.class));
    }
}
