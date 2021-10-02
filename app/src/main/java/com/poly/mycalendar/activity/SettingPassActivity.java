package com.poly.mycalendar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.poly.mycalendar.R;

public class SettingPassActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private Switch swichPin, switchFingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_pass);
        intViews();
        swichPin.setOnCheckedChangeListener(this);
    }

    private void intViews() {
        swichPin= (Switch)findViewById(R.id.switch_pin);
        switchFingerprint=findViewById(R.id.switch_fingerprint);


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            Toast.makeText(this,"check", Toast.LENGTH_SHORT).show();
        } else {
            //do stuff when Switch if OFF
        }
    }
}