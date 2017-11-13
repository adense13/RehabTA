package com.example.adrian.vibrationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RingklockaSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringklocka_settings);

        //get the spinner from the xml.
        Spinner dropdown = (Spinner)findViewById(R.id.vibration_spinner);
        //create a list of items for the spinner.

        String[] items = new String[]{"No vibration", "Vibration 1", "Vibration 2", "Vibration 3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //Set prompt message
        dropdown.setPrompt("Välj vibration");
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        Spinner connectionSpinner = findViewById(R.id.connection_spinner);
        String[] connectionItems = new String[]{"IoT Ringklocka"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, connectionItems);
        connectionSpinner.setPrompt("Välj device");
        connectionSpinner.setAdapter(adapter2);
    }
}
