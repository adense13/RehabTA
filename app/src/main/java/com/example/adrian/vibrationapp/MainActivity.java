package com.example.adrian.vibrationapp;

//import android.graphics.Camera;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import java.security.Policy;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;
    boolean isLight = false;
    ArrayList<long[]> patterns = new ArrayList<>();
    ArrayList<String> lights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //Populating Patterns Array
        createPatterns();
        //Populating Lights Array
        createLights();
        //Views
        View brandlarmView = findViewById(R.id.brandlarm_view);
        brandlarmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("MainPref",0);
                SharedPreferences.Editor prefEdit = sharedPref.edit();
                EditText textView2 = findViewById(R.id.textView2);
                prefEdit.putString("title2", textView2.getText().toString());
                prefEdit.commit();
                Log.i("title2", textView2.getText().toString());
                Intent intent = new Intent(MainActivity.this, Settings.class);
                intent.putExtra("vibrations", patterns);
                intent.putExtra("lights", lights);
                startActivity(intent);
            }
        });

        View ringklockaView = findViewById(R.id.ringklocka_view);
        ringklockaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("MainPref",MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = sharedPref.edit();
                EditText textView3 = findViewById(R.id.textView3);
                prefEdit.putString("title3", textView3.getText().toString());
                prefEdit.commit();
                Intent intent = new Intent(MainActivity.this, Settings.class);
                intent.putExtra("vibrations", patterns);
                intent.putExtra("lights", lights);
                startActivity(intent);
            }
        });

        //---TEST BUTTONS---/// To be removed
        //Button btn_pattern1 = findViewById(R.id.btn_pattern1);
        //btn_pattern1.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        vibrate(patterns.get(1));
        //    }
        //});

        //Button btn_pattern2 = findViewById(R.id.btn_pattern2);
        //btn_pattern2.setOnClickListener(new View.OnClickListener() {
        //    public void onClick(View v) {
        //        vibrate(patterns.get(2));
        //    }
        //});
        //ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 1);
        //Button btn_flashlight = findViewById(R.id.Flashlight1);
        /*final Camera cam = Camera.open();
        final Camera.Parameters parameters = cam.getParameters();
        btn_flashlight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    //stänger av
                    if (isLight) {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        cam.setParameters(parameters);
                        cam.stopPreview();
                        isLight = false;

                        //sätter på
                    } else {
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        cam.setParameters(parameters);
                        cam.startPreview();
                        isLight = true;
                    }

            }
        });*/

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        SharedPreferences sharedPref = getSharedPreferences("MainPref",MODE_PRIVATE);
        EditText textView2 = findViewById(R.id.textView2);
        EditText textView3 = findViewById(R.id.textView3);
        String text2 = sharedPref.getString("title2","Default");
        Log.i("Text2", text2);
        String text3 = sharedPref.getString("title3","Default");
        if(text2!="Default"){
            textView2.setText(text2);
        }
        if(text3!="Default"){
            textView3.setText(text3);
        }

    }

    private void createLights(){
        lights.add("No Light Settings");
        lights.add("Red, Neutral");
        lights.add("Red, Blinking");
        lights.add("Blue, Neutral");
        lights.add("Blue, Blinking");
    }

    private void createPatterns(){
        //Ingen vibration
        long[] pattern0 ={0, 0};
        patterns.add(pattern0);

        long[] pattern1 = {0, 1000, 2000, 1000, 2000, 1000, 500, 1000, 500, 1000, 500, 1000, 2000};
        patterns.add(pattern1);

        long[] pattern2 = {0, 500, 50, 500, 50, 500, 1000, 1000, 50, 1000, 50, 1000, 1000, 500, 50, 500, 50, 500, 1000, 500};
        patterns.add(pattern2);

        long[] pattern3 = {0, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000};
        patterns.add(pattern3);

        long[] pattern4 = {0, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000, 2000, 1000};
        patterns.add(pattern4);

        long[] pattern5 = {0, 1000, 500, 1000, 500, 1000, 500, 1000, 500, 1000, 500, 1000, 500};
        patterns.add(pattern5);
    }

    private void vibrate(long[] pattern){

        vibrator.cancel();
        Log.i("hej", "hej");
        vibrator.vibrate(pattern,0); //DEPRECATED METHOD :((( pls find the correct way to do dis

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i("tag","access to camera is OK");
    }

}
