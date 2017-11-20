package com.example.adrian.vibrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class BrandlarmSettings extends AppCompatActivity {
        Spinner vibrationSpinner;
        int test;
        boolean isVibrating = false;
        Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandlarm_settings);
        vibrationSpinner = findViewById(R.id.vibration_spinner);
        String[] vibrationItems = new String[]{"No vibration", "Vibration 1", "Vibration 2", "Vibration 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vibrationItems);
        vibrationSpinner.setPrompt("Välj vibration");
        vibrationSpinner.setAdapter(adapter);

        Spinner connectionSpinner = findViewById(R.id.connection_spinner);
        String[] connectionItems = new String[]{"IoT Brandlarm"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, connectionItems);
        connectionSpinner.setPrompt("Välj device");
        connectionSpinner.setAdapter(adapter2);

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("brandlarmVibration",-1);
        if(spinnerValue != -1) {
            // set the selected value of the spinner
            vibrationSpinner.setSelection(spinnerValue);
        }
    }

    public void previewVibration(View view){
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        ArrayList<long[]> pattern = (ArrayList<long[]>) getIntent().getSerializableExtra("vibrations");
        //Temp IF-sats för att förhindra crash
        if(vibrationSpinner.getSelectedItemPosition()<3){
            vibrate(pattern.get(vibrationSpinner.getSelectedItemPosition()));
        }

        Log.i("vibcheck", "vibrating");
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

    public void saveSettings(View view)
    {
        int userChoice = vibrationSpinner.getSelectedItemPosition();
        SharedPreferences sharedPref = getSharedPreferences("FileName",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("brandlarmVibration",userChoice);
        prefEditor.commit();

        Intent intent = new Intent(BrandlarmSettings.this, MainActivity.class);
        startActivity(intent);
    }


}
