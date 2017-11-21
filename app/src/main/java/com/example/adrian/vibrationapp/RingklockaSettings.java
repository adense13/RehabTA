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

public class RingklockaSettings extends AppCompatActivity {
    Spinner vibrationSpinner;
    boolean isVibrating = false;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringklocka_settings);

        //get the spinner from the xml.
        vibrationSpinner = findViewById(R.id.vibration_spinner);
        //create a list of items for the spinner.

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

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vibrationItems);
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

    public void previewVibration(View view){
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        ArrayList<long[]> pattern = (ArrayList<long[]>) getIntent().getSerializableExtra("vibrations");
        vibrate(pattern.get(vibrationSpinner.getSelectedItemPosition()));
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
        prefEditor.putInt("ringklockaVibration",userChoice);
        prefEditor.commit();

        Intent intent = new Intent(RingklockaSettings.this, MainActivity.class);
        startActivity(intent);
    }
}
