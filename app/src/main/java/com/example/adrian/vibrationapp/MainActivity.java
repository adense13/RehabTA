package com.example.adrian.vibrationapp;

//import android.graphics.Camera;
import android.hardware.Camera;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {

    Vibrator vibrator;
    boolean isVibrating = false;
    boolean isLight = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        //---BUTTONS--///
        Button btn_pattern1 = findViewById(R.id.btn_pattern1);
        btn_pattern1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long[] pattern = {3000, 1000, 2000, 1000, 2000, 1000, 500, 1000, 500, 1000, 500, 1000};
                vibrate(pattern);
            }
        });

        Button btn_pattern2 = findViewById(R.id.btn_pattern2);
        btn_pattern2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long[] pattern = {0, 500, 50, 500, 50, 500, 1000, 1000, 50, 1000, 50, 1000, 1000, 500, 50, 500, 50, 500, 1000};
                vibrate(pattern);
            }
        });

        Button btn_flashlight = findViewById(R.id.Flashlight1);
        btn_pattern2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Camera cam = Camera.open();
                Camera.Parameters parameters = cam.getParameters();
                //stänger av
                if(isLight){
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    cam.setParameters(parameters);
                    cam.stopPreview();
                    isLight = false;

                    //sätter på
                }else{
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(parameters);
                    cam.startPreview();
                    isLight = true;
                }

            }
        });

    }

    private void vibrate(long[] pattern){
        if(isVibrating){
            isVibrating = false;
            vibrator.cancel();
        }
        else {
            isVibrating = true;
            //VibrationEffect vibrationEffect = Parcelable.Creator<VibrationEffect>();
            vibrator.vibrate(pattern, -1); //DEPRECATED METHOD :((( pls find the correct way to do dis
        }
    }
}
