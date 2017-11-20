package com.example.adrian.vibrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BrandlarmSettings extends AppCompatActivity {
        Spinner vibrationSpinner;
        int test;
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
