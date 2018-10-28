package com.example.shihab.drinksafe;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

public class EnterGallery extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 1 ;
    Button buttonShowResult;
    ImageView imageViewGallery,imageViewPick;
    TextView textViewRGB;
    public static String r1,g1,b1;
    public String code_alpha;
    public String code_red;
    public String code_green;
    public String code_blue;
    public String latitude;
    public String longitude;
    public  String address;
    private GpsTracker gpsTracker;
    LocationManager locationManager;
    public ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_gallery);
        buttonShowResult = findViewById(R.id.buttonShowResult);
        imageViewGallery = findViewById(R.id.imageViewGallery);
        imageViewPick= findViewById(R.id.imageViewPicked);
        textViewRGB = findViewById(R.id.textviewRGB);
        buttonShowResult.setOnClickListener(this);



        String imagePath = getIntent().getStringExtra("uri_result");
        if(imagePath!= null) {
            Uri imagePathUri = Uri.parse(imagePath);
            try {
                Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePathUri);
                imageViewGallery.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        pixelColor();

    }




    @Override
    public void onClick(View v) {
         if (v == buttonShowResult){
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
               if( haveNetworkConnection(EnterGallery.this)==false)
               {
                   noInternetAlert();

               }
               else {
                   getLocation();
                   onLocationReceived();
                   showProgressDialog();
               }

            }
            else if(haveNetworkConnection(EnterGallery.this)==false)
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
            Toast.makeText(EnterGallery.this,"Error",Toast.LENGTH_LONG).show();
        }

    }
    private void getLocation() {
        gpsTracker = new GpsTracker(EnterGallery.this);
        if(gpsTracker.canGetLocation()){
            latitude = String.valueOf(gpsTracker.getLatitude());
            longitude = String.valueOf(gpsTracker.getLongitude());
            // Toast.makeText(EnterGallery.this,"1st Latitude: "+latitude+"Longitude: "+longitude,Toast.LENGTH_LONG).show();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void onLocationReceived() {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);
            address= addresses.get(0).getAddressLine(0)+", "+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2);

         //  Toast.makeText(EnterGallery.this,"Address: "+address,Toast.LENGTH_LONG).show();

        }catch(Exception e)
        {

        }

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
        AlertDialog.Builder noIntBuilder = new AlertDialog.Builder(EnterGallery.this);
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




    private void showGPSDisabledAlertToUser() {
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
    }


    private  void showProgressDialog() {
     progressDialog = new ProgressDialog(EnterGallery.this);
     progressDialog.setTitle("  Calculating Result");
     progressDialog.setIcon(R.drawable.wait48);
     progressDialog.setMessage("Please Wait.....");
     progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
     progressDialog.show();
     Runnable runnable = new Runnable() {
         @Override
         public void run() {
             Intent intent = new Intent(EnterGallery.this,Result.class);
            intent.putExtra("alpha_key",code_alpha);
            intent.putExtra("red_key",code_red);
            intent.putExtra("green_key",code_green);
            intent.putExtra("blue_key",code_blue);
            //Toast.makeText(getApplicationContext(),"A: "+code_alpha+"R: "+code_red+"G: "+code_green+"B: "+code_blue,Toast.LENGTH_LONG).show();
            getLocation();
            onLocationReceived();
            intent.putExtra("key_latitude",latitude);
            intent.putExtra("key_longitude",longitude);
            intent.putExtra("key_address",address);
            finish();
            startActivity(intent);
         }
     };
        Handler handler = new Handler();
           handler.postDelayed(runnable,7000);
    }

    private void pixelColor() {
        imageViewGallery.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(!(imageViewGallery.getDrawable() ==  null)) {
                    int touchX = (int) motionEvent.getX();
                    int touchY = (int) motionEvent.getY();

                    imageViewGallery.buildDrawingCache();
                    Bitmap bitmap = imageViewGallery.getDrawingCache();

                    if(touchX > 0 && touchY > 0 && touchX < bitmap.getWidth() && touchY < bitmap.getHeight()) {
                        int pixelColor = bitmap.getPixel(touchX,touchY);
                         code_alpha= String.valueOf(Color.alpha(pixelColor));
                        code_red = String.valueOf(Color.red(pixelColor));
                         code_green= String.valueOf(Color.green(pixelColor));
                         code_blue= String.valueOf(Color.blue(pixelColor));
                        r1= code_red;
                        g1= code_green;
                        b1= code_blue;
                        textViewRGB.setText("Alpha "+ code_alpha+" Color: " + code_red +", "+ code_green+" ,"+code_blue);
                        imageViewPick.setBackgroundColor(pixelColor);


                    }

                }

                return true;

            }
        });
    }


    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(EnterGallery.this,ImageSelection.class));
    }


}
