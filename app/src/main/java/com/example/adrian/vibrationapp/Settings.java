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

public class Settings extends AppCompatActivity {
    Spinner vibrationSpinner;
    Spinner connectionSpinner;
    Spinner lightSpinner;
    boolean isVibrating = false;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        vibrationSpinner = findViewById(R.id.vibration_spinner);
        ArrayList<long[]> pattern = (ArrayList<long[]>) getIntent().getSerializableExtra("vibrations");


        int nbrOfVibrations = pattern.size();
        String[] vibrationItems = new String[nbrOfVibrations];
        for(int i=0; i<nbrOfVibrations; i++){
            if(i==0){
                vibrationItems[i] = "No vibration";
            }else{
                vibrationItems[i] = "Vibration " + i;
            }

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vibrationItems);
        vibrationSpinner.setPrompt("Choose vibration");
        vibrationSpinner.setAdapter(adapter);
        connectionSpinner = findViewById(R.id.connection_spinner);
        String[] connectionItems = new String[]{"IoT Brandlarm", "IoT Ringklocka"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, connectionItems);
        connectionSpinner.setPrompt("Choose device");
        connectionSpinner.setAdapter(adapter2);

        ArrayList<String> lights = (ArrayList<String>)getIntent().getSerializableExtra("lights");
        int nbrOfLights = lights.size();
        String[] lightItems = new String[nbrOfLights];
        for(int i=0; i<nbrOfLights; i++){
            lightItems[i] = lights.get(i);
        }
        lightSpinner = findViewById(R.id.light_spinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lightItems);
        lightSpinner.setPrompt("Choose light setting");
        lightSpinner.setAdapter(adapter3);

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
        int deviceValue = sharedPref.getInt("device", -1);
        if( deviceValue!=-1){
            connectionSpinner.setSelection(deviceValue);
        }
        int lightValue = sharedPref.getInt("light", -1);
        if(lightValue!=-1){
            lightSpinner.setSelection(lightValue);
        }
    }

    public void previewVibration(View view){
        ArrayList<long[]> pattern = (ArrayList<long[]>) getIntent().getSerializableExtra("vibrations");
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrate(pattern.get(vibrationSpinner.getSelectedItemPosition()));
        Log.i("vibcheck", "vibrating");
    }

    private void vibrate(long[] pattern){
        vibrator.cancel();
        Log.i("hej", "hej");
        vibrator.vibrate(pattern,0);
        isVibrating = true;
    }

    public void cancelVibration(View view){
        if(isVibrating==true){
            vibrator.cancel();
            isVibrating = false;
        }

    }

    public void saveSettings(View view) {
        int vibrationChoice = vibrationSpinner.getSelectedItemPosition();
        int deviceChoice = connectionSpinner.getSelectedItemPosition();
        int lightChoice = lightSpinner.getSelectedItemPosition();
        SharedPreferences sharedPref = getSharedPreferences("FileName",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("brandlarmVibration",vibrationChoice);
        prefEditor.putInt("device", deviceChoice);
        prefEditor.putInt("light", lightChoice);
        prefEditor.commit();
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }

}
