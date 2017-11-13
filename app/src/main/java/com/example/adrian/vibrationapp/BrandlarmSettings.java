package com.example.adrian.vibrationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BrandlarmSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandlarm_settings);

        Spinner vibrationSpinner = findViewById(R.id.vibration_spinner);
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
}
