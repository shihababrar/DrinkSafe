package com.example.shihab.drinksafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowTips extends AppCompatActivity {
    TextView tvEnglish,tvBangla, tvEnglish_2, tvBangla_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tips);
        tvEnglish = findViewById(R.id.textViewShowTipsEnglish);
        tvBangla = findViewById(R.id.textViewShowTipsBangla);
        tvEnglish_2 = findViewById(R.id.textViewShowTipsEnglish_2);
        tvBangla_2 = findViewById(R.id.textViewShowTipsBangla_2);

        tvEnglish.setText("Tips For removing Arsenic:\nYou can remove arsenic from your water by using a water cooler with reverse osmosis built-in, a process that forces water through a semi-permeable membrane under pressure that will leave contaminants behind and dispense more pure and healthy drinking water.");
        tvBangla.setText("\nআর্সেনিক অপসারণের জন্য পরামর্শ:\nরিভার্স অসিমোসিস বিল্ট ইন দিয়ে পানির কুলার ব্যবহার করে আপনি পানি থেকে আর্সেনিক সরিয়ে ফেলতে পারেন, একটি প্রক্রিয়া যা চাপের মধ্যে একটি আধা-বহনযোগ্য ঝিল্লি দিয়ে পানি সঞ্চার করে যা দূষণকারীকে পিছনে ফেলে দেবে এবং বিশুদ্ধ ও সুস্বাস্থ্যের পানীয় জল বিতরণ করবে।");
        tvEnglish_2.setText("Tips for removing Iron:\nThe only safe and effective way to remove iron from the water is by utilizing an iron filter. A Katolox filtration system is able to remove both forms of iron, magnesium and hydrogen sulfide present in well water");
        tvBangla_2.setText("\nলোহা অপসারণের জন্য পরামর্শ:\nলোহা ফিল্টার ব্যবহার করে পানি থেকে লোহা অপসারণের একমাত্র নিরাপদ এবং কার্যকর উপায়। একটি ক্যাটলক্স পরিস্রাবণ সিস্টেম ভাল জল উপস্থিত লোহা, ম্যাগনেসিয়াম এবং হাইড্রোজেন সালফাইড উভয় ফর্ম অপসারণ করতে পারবেন");
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ShowTips.this,ImageSelection.class));
    }
}
