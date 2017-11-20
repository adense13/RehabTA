package com.example.adrian.vibrationapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RingklockaSettings extends AppCompatActivity {
    Spinner vibrationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringklocka_settings);

        //get the spinner from the xml.
        vibrationSpinner = findViewById(R.id.vibration_spinner);
        //create a list of items for the spinner.

        String[] items = new String[]{"No vibration", "Vibration 1", "Vibration 2", "Vibration 3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //Set prompt message
        vibrationSpinner.setPrompt("Välj vibration");
        //set the spinners adapter to the previously created one.
        vibrationSpinner.setAdapter(adapter);

        Spinner connectionSpinner = findViewById(R.id.connection_spinner);
        String[] connectionItems = new String[]{"IoT Ringklocka"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, connectionItems);
        connectionSpinner.setPrompt("Välj device");
        connectionSpinner.setAdapter(adapter2);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        SharedPreferences sharedPref = getSharedPreferences("FileName",MODE_PRIVATE);
        int spinnerValue = sharedPref.getInt("ringklockaVibration",-1);
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
        prefEditor.putInt("ringklockaVibration",userChoice);
        prefEditor.commit();

        Intent intent = new Intent(RingklockaSettings.this, MainActivity.class);
        startActivity(intent);
    }
}
