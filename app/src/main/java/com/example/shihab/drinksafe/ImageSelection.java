package com.example.shihab.drinksafe;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageSelection extends AppCompatActivity implements View.OnClickListener{
    ImageView ivImageSelect;
    private static final int PICK_IMAGE = 1 ;
    Intent imageIntent;
    private GpsTracker gpsTracker;
    public String latitude;
    public String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        ivImageSelect = findViewById(R.id.imageViewImageSelect);
        ivImageSelect.setOnClickListener(this);



    }


    @Override
    public void onClick(View v) {
        if(v==ivImageSelect)
        {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                ActivityCompat.requestPermissions(ImageSelection.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                if (( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED )){
                         getLocation();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

                }

            }
            else
            {

                 ActivityCompat.requestPermissions(ImageSelection.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                if (( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                                PackageManager.PERMISSION_GRANTED )){
                    if(showGPSDisabledAlertToUser()== true) {
                       getLocation();
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                    }
                }

            }

        }
        else {
            Toast.makeText(ImageSelection.this,"Error",Toast.LENGTH_LONG).show();
        }


        }


    private void pixelColor() {
        ivImageSelect.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(!(ivImageSelect.getDrawable() ==  null)) {
                    int touchX = (int) motionEvent.getX();
                    int touchY = (int) motionEvent.getY();

                    ivImageSelect.buildDrawingCache();
                    Bitmap bitmap = ivImageSelect.getDrawingCache();

                    /*if(touchX > 0 && touchY > 0 && touchX < bitmap.getWidth() && touchY < bitmap.getHeight()) {
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


                    }*/

                }

                return true;

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            imageIntent = new Intent(ImageSelection.this,EnterGallery.class);
            imageIntent.putExtra("uri_result",uri.toString());
            finish();
            startActivity(imageIntent);

        }
    }
    private void getLocation() {
        gpsTracker = new GpsTracker(ImageSelection.this);
        if(gpsTracker.canGetLocation()){
            latitude = String.valueOf(gpsTracker.getLatitude());
            longitude = String.valueOf(gpsTracker.getLongitude());
            // Toast.makeText(EnterGallery.this,"1st Latitude: "+latitude+"Longitude: "+longitude,Toast.LENGTH_LONG).show();

        }else{
            gpsTracker.showSettingsAlert();
        }
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
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ImageSelection.this);
        builder.setTitle("Quit");
        builder.setIcon(getResources().getDrawable(R.drawable.exit2));
        builder.setMessage("Do you want to quit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

