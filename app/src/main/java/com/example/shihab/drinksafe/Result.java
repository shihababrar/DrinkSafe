package com.example.shihab.drinksafe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shihab.drinksafe.Class.IronTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Result extends AppCompatActivity implements View.OnClickListener {
    TextView tvArsenicEnglish,tvArsenicBangla;
    TextView tvIronEnglish,tvIronBangla;
    Button btnShowTips;
    private String latitude,longitude,address;
    private GpsTracker gpsTracker;
    private int a,r,g,b;
    private String mydate;
    private String densityLevel, ppmLevel;
    //private final String url = "http://safedrinktk.000webhostapp.com/safedrink/insert.php";
    //private final String url = "https://httpdrinksafe498tk.000webhostapp.com/files/insert.php";
    private final String url = "https://jachaibd.com/files/insert.php";

    public static final int redHigh0 = 145;
    public static final int greenHigh0 = 148;
    public static final int blueHigh0 = 146;
    public static final int redLow0 = 100;
    public static final int greenLow0 = 101;
    public static final int blueLow0 = 93;

    public static final int redHigh2 = 134;
    public static final int greenHigh2 = 115;
    public static final int blueHigh2 = 92;
    public static final int redLow2 = 123;
    public static final int greenLow2 = 96;
    public static final int blueLow2 = 76;

    public static final int redHigh4 = 117;
    public static final int greenHigh4 = 86;
    public static final int blueHigh4 = 66;
    public static final int redLow4 = 99;
    public static final int greenLow4 = 57;
    public static final int blueLow4 = 38;

    public static final int redHigh6 = 0;
    public static final int greenHigh6 = 0;
    public static final int blueHigh6 = 0;
    public static final int redLow6 = 0;
    public static final int greenLow6 = 0;
    public static final int blueLow6 = 0;

    public static final int redHigh8 = 151;
    public static final int greenHigh8 = 111;
    public static final int blueHigh8 = 59;
    public static final int redLow8 = 127;
    public static final int greenLow8 = 73;
    public static final int blueLow8 = 24;

    public static final int redHigh10 = 155;
    public static final int greenHigh10 = 88;
    public static final int blueHigh10 = 29;
    public static final int redLow10 = 102;
    public static final int greenLow10 = 60;
    public static final int blueLow10 = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvArsenicEnglish = findViewById(R.id.textViewColorCodesArsenicEnglish);
        tvArsenicBangla = findViewById(R.id.textViewColorCodesArsenicBangla);
        tvIronEnglish = findViewById(R.id.textViewColorCodesIronEnglish);
        tvIronBangla = findViewById(R.id.textViewColorCodesIronBangla);


        btnShowTips = findViewById(R.id.idButtonShowTips);
        btnShowTips.setOnClickListener(this);
        mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        getLocation();
        a = Integer.parseInt(getIntent().getStringExtra("alpha_key"));
        r = Integer.parseInt(getIntent().getStringExtra("red_key"));
        g = Integer.parseInt(getIntent().getStringExtra("green_key"));
        b = Integer.parseInt(getIntent().getStringExtra("blue_key"));
        address = getIntent().getStringExtra("key_address");
        Toast.makeText(Result.this,"A: "+a+" R: "+r+" G: "+g+" B: "+b,Toast.LENGTH_LONG).show();
        Toast.makeText(Result.this,"Result: Latitude: "+latitude+"\nLongitude: "+longitude+"\nAddress: "+address,Toast.LENGTH_LONG).show();
        Toast.makeText(Result.this,"Date: "+mydate,Toast.LENGTH_LONG).show();
        analyzeResultArsenic();
        analyzeResultIron();


    }

    private  void analyzeResultArsenic()
    {
        if((r>=161&&r<=181)&&(g>=167&&g<=185)&&(b>=165&&b<=177))
        {
            tvArsenicEnglish.setText("The water contains 0ml arsenic (100% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ০ মি.লি আর্সেনিক রয়েছে। (১০০ % বিশুদ্ধ) ");
            densityLevel= String.valueOf(0);
        }
        else if((r>=234&&r<=255)&&(g>=231&&g<=255)&&(b>=204&&b<=245))
        {
            tvArsenicEnglish.setText("The water contains 10ml arsenic (90% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ১০ মি.লি আর্সেনিক রয়েছে। (৯০ % বিশুদ্ধ)");
            densityLevel= String.valueOf(10);
        }
        else if((r>=229&&r<=252)&&(g>=224&&g<=251)&&(b>=105&&b<=151))
        {
            tvArsenicEnglish.setText("The sample water contains 25ml arsenic (75% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ২৫ মি.লি আর্সেনিক রয়েছে। (৭৫ % বিশুদ্ধ)");
            densityLevel= String.valueOf(25);
        }
        else if((r>=209&&r<=255)&&(g>=218&&g<=248)&&(b>=102&&b<=115))
        {
            tvArsenicEnglish.setText("The sample water contains 50ml arsenic(50% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ৫০ মি.লি আর্সেনিক রয়েছে। (৫০ % বিশুদ্ধ)");
            densityLevel= String.valueOf(50);
        }
        else if((r>=207&&r<=255)&&(g>=180&&g<=236)&&(b>=53&&b<=86))
        {
            tvArsenicEnglish.setText("The sample water contains 100ml arsenic (30% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ১০০ মি.লি আর্সেনিক রয়েছে। (৩০ % বিশুদ্ধ)");
            densityLevel= String.valueOf(100);
        }
        else if((r>=226&&r<=255)&&(g>=128&&g<=170)&&(b>=51&&b<=77))
        {
            tvArsenicEnglish.setText("The sample water contains 250ml arsenic (10% Pure)");
            tvArsenicBangla.setText("নমুনা পানিতে ২৫০ মি.লি আর্সেনিক রয়েছে। (১০ % বিশুদ্ধ)");
            densityLevel= String.valueOf(250);
        }
        else if((r>=175&&r<=204)&&(g>=101&&g<=127)&&(b>=51&&b<=51))
        {
            tvArsenicEnglish.setText("The sample water contains 500ml arsenic");
            tvArsenicBangla.setText("নমুনা পানিতে ৫০০ মি.লি আর্সেনিক রয়েছে। (০ % বিশুদ্ধ)");
            densityLevel= String.valueOf(500);
        }
        else
        {
            tvArsenicEnglish.setText("Arsenic Level: RGB value doesn't match with any Color Code of Arsenic\n");
            tvArsenicBangla.setText("আর্সেনিকের মাত্রা: আরজিবি মান আর্সেনিকের কোন রঙিন কোডের সাথে মেলে না");
            densityLevel="Unknown RGB Code";
        }
    }



    private void analyzeResultIron() {
        if( (r>=redLow0 && r<=redHigh0) && (g>=greenLow0 && g<=greenHigh0) && (b>=blueLow0 && b<=blueHigh0))
        {
            tvIronEnglish.setText ("0 ppm, Soft level iron detected! ");
            tvIronBangla.setText("0 পিপিএম, নরম স্তর লোহা সনাক্ত!");
            ppmLevel = String.valueOf(0.0);
        }


        /////////////////// 2.0 ppm soft////////////
        else if( (r>=redLow2 && r<=redHigh2) && (g>=greenLow2 && g<=greenHigh2) && (b>=blueLow2 && b<=blueHigh2) )
        {

            tvIronEnglish.setText("2.0 ppm, Soft Level iron detected!!");
            tvIronBangla.setText("২.০ পিপিএম, নরম স্তর লোহা সনাক্ত !!");
            ppmLevel = String.valueOf(2.0);
        }



        /////////////////// 4.0 ppm soft////////////
        else if( (r>=redLow4 && r<=redHigh4)&& (g>=greenLow4 && g<=greenHigh4) && (b>=blueLow4 && b<=blueHigh4))
        {
            tvIronEnglish.setText("4.0 ppm / mid-hard level iron ");
            tvIronBangla.setText("৪.০  পিপিএম / মধ্য-কঠিন স্তর লোহা \"");
            ppmLevel = String.valueOf(4.0);
        }


        /////////////////// 6.0 ppm mid-hard////////////
        else if( (r>=redLow6 && r<=redHigh6) && (g>=greenLow6 && g<=greenHigh6) && (b>=blueLow6 && b<=blueHigh6))
        {
            tvIronEnglish.setText("6.0 ppm / hard level iron ");
            tvIronBangla.setText("৬.০  পিপিএম / কঠিন স্তর লোহা সনাক্ত!");
            ppmLevel = String.valueOf(6.0);
        }

        //// 8.0 hard ppm
        else if( (r>=redLow8 && r<=redHigh8) && (g>=greenLow8 && g<=greenHigh8) && (b>=blueLow8 && b<=blueHigh8))
        {
            tvIronEnglish.setText("8.0 ppm / hard level iron ");
            tvIronBangla.setText("৮.০ পিপিএম / কঠিন স্তর লোহা সনাক্ত!");
            ppmLevel = String.valueOf(8.0);
        }



        /////////////////// 10.0 ppm hard////////////
        else if( (r>=redLow10 && r<=redHigh10) && (g>=greenLow10 && g<=greenHigh10) && (b>=blueLow10 && b<=blueHigh10))
        {
            tvIronEnglish.setText("10.0 ppm / Very hard level iron ");
            tvIronBangla.setText("১০.০ পিপিএম / খুবই কঠিন স্তর লোহা সনাক্ত!");
            ppmLevel = String.valueOf(10.0);
        }
        else {
            tvIronEnglish.setText("IronLevel: RGB value doesn't match with any Color Code of iron");
            tvIronBangla.setText("লোহা স্তর: আরজিবি মূল্য লোহার কোন রঙ কোডের সাথে মেলে না");

        }
    }


    private void getLocation() {
        gpsTracker = new GpsTracker(Result.this);
        if(gpsTracker.canGetLocation()){
            latitude = String.valueOf(gpsTracker.getLatitude());
            longitude = String.valueOf(gpsTracker.getLongitude());
            Toast.makeText(Result.this," Latitude: "+latitude+"Longitude: "+longitude,Toast.LENGTH_LONG).show();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }
    private void sendInformations() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Result.this, "Data Send!", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Result.this, "Network error: " + error, Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("latitude",latitude);
                params.put("longitude",longitude);
                params.put("address",address);
                params.put("mydate",mydate);
                params.put("densityLevel",densityLevel);
                params.put("ppmLevel",ppmLevel);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private boolean haveNetworkConnection(Context context)
    {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo)
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
            {
                if (ni.isConnected())
                {
                    haveConnectedWifi = true;
                    Log.v("WIFI CONNECTION ", "AVAILABLE");
                } else
                {
                    Log.v("WIFI CONNECTION ", "NOT AVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
            {
                if (ni.isConnected())
                {
                    haveConnectedMobile = true;
                    Log.v("INTERNET CONNECTION ", "AVAILABLE");
                } else
                {
                    Log.v("INTERNET CONNECTION ", "NOT AVAILABLE");
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    private void noInternetAlert()
    {
        AlertDialog.Builder noIntBuilder = new AlertDialog.Builder(Result.this);
        noIntBuilder.setTitle(" No Internet");
        noIntBuilder.setIcon(getResources().getDrawable(R.drawable.interneterror));
        noIntBuilder.setMessage("  Turn On Internet Connection");
        noIntBuilder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = noIntBuilder.create();
        alert.show();
    }
    private boolean showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device")
                .setCancelable(false)
                .setPositiveButton("Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        return  false;
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(Result.this,ImageSelection.class));
    }

    @Override
    public void onClick(View v) {
        if(v == btnShowTips)
        {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                if( haveNetworkConnection(Result.this)==false)
                {
                    noInternetAlert();

                }
                else {
                    sendInformations();
                    finish();
                    startActivity(new Intent(Result.this,ShowTips.class));
                    }
            }
            else if(haveNetworkConnection(Result.this)==false)
            {
                if ( !(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) )
                {
                    showGPSDisabledAlertToUser();
                }
                else {
                    noInternetAlert();
                }
            }
            else{
                showGPSDisabledAlertToUser();
            }
        }
        else {
            Toast.makeText(Result.this,"Error",Toast.LENGTH_LONG).show();
        }

    }


}

