package com.poly.mycalendar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.poly.mycalendar.R;
import com.poly.mycalendar.fragment.CycleFragment;
import com.poly.mycalendar.fragment.PeriodFragment;

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetBehavior sheetBehavior;
    private RelativeLayout cycleDialog;
    private Button btnPeriodStart, btnCycle, btnPeriod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        initViews();
        initActions();
    }


    private void initViews() {
        cycleDialog=findViewById(R.id.bottom_sheet);
        btnCycle=findViewById(R.id.btn_cycle);
        btnPeriod=findViewById(R.id.btn_period);
        btnPeriodStart=findViewById(R.id.btn_period_start);

    }

    private void initActions() {
        btnCycle.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);
        btnPeriodStart.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cycle:
                CycleFragment cycleFragment= new CycleFragment();
                cycleFragment.show(getSupportFragmentManager(),"");
                break;
            case R.id.btn_period:
                PeriodFragment periodFragment= new PeriodFragment();
                periodFragment.show(getSupportFragmentManager(),"");
                break;
            default:
                break;
        }

    }
}