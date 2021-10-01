package com.poly.mycalendar.fragment.features;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.data.DataUserDAO;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private LinearLayout editPeriodLength, edtCycleLenth, settingNotifi;
    private View view;
    private NumberPicker pickerCycle, pickerPeriod;
    private Button btnPeriodCancel, btnPeriodSave, btnCycleCancel, btnCycleSave;
    private LinearLayout choosePeriod, chooseCycle;
    private DataUserDAO dataUserDAO;
    private int cycleLength;
    private int periodLength;
    private int newCycle;
    private int newPeriod;
    private TextView tvPeriod, tvCycle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_setting, container, false);
            initViews();
            initAction();
        }
        return view;
    }


    private void initViews() {
        editPeriodLength = view.findViewById(R.id.edit_period_length);
        edtCycleLenth = view.findViewById(R.id.edit_cycle_length);
        settingNotifi = view.findViewById(R.id.setting_notifi);
        chooseCycle = view.findViewById(R.id.choose_cycle_length);
        choosePeriod = view.findViewById(R.id.choose_period_length);
        pickerCycle = view.findViewById(R.id.picker_cycle);
        pickerPeriod = view.findViewById(R.id.picker_period);
        btnCycleSave = view.findViewById(R.id.btn_cycle_save);
        btnCycleCancel = view.findViewById(R.id.btn_cycle_cancel);
        btnPeriodCancel = view.findViewById(R.id.btn_period_cancel);
        btnPeriodSave = view.findViewById(R.id.btn_period_save);
        tvPeriod = view.findViewById(R.id.tv_period);
        tvCycle = view.findViewById(R.id.tv_cycle);
    }

    private void initAction() {
        editPeriodLength.setOnClickListener(this);
        edtCycleLenth.setOnClickListener(this);
        settingNotifi.setOnClickListener(this);

        btnCycleSave.setOnClickListener(this);
        btnCycleCancel.setOnClickListener(this);
        btnPeriodSave.setOnClickListener(this);
        btnPeriodCancel.setOnClickListener(this);
        dataUserDAO = new DataUserDAO(getContext());
        periodLength = dataUserDAO.getPeriod();
        cycleLength = dataUserDAO.getCycle();
        tvPeriod.setText(periodLength + " days");
        tvCycle.setText(cycleLength + " days");

    }


    private void PeriodLength() {
        pickerPeriod.setMaxValue(14);
        pickerPeriod.setMinValue(0);
        pickerPeriod.setWrapSelectorWheel(true);
        pickerPeriod.setValue(periodLength);
        pickerPeriod.setOnValueChangedListener((picker, oldVal, newVal) -> {
            newPeriod = newVal;

        });
        if (choosePeriod.getVisibility() == View.VISIBLE) {
            choosePeriod.setVisibility(View.GONE);


        } else if (choosePeriod.getVisibility() == View.GONE) {
            choosePeriod.setVisibility(View.VISIBLE);


        }
    }

    private void CycleLength() {
        pickerCycle.setMaxValue(100);
        pickerCycle.setMinValue(0);
        pickerCycle.setWrapSelectorWheel(true);
        pickerCycle.setValue(cycleLength);

        pickerCycle.setOnValueChangedListener((picker, oldVal, newVal) -> {
            newCycle=newVal;
        });
        if (chooseCycle.getVisibility() == View.VISIBLE) {
            chooseCycle.setVisibility(View.GONE);



        } else if (chooseCycle.getVisibility() == View.GONE) {
            chooseCycle.setVisibility(View.VISIBLE);

        }

    }


//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_period_length:
                PeriodLength();
                break;
            case R.id.edit_cycle_length:
                CycleLength();
                break;
            case R.id.setting_notifi:
                break;
            case R.id.btn_cycle_cancel:
                chooseCycle.setVisibility(View.GONE);


                break;
            case R.id.btn_cycle_save:
              dataUserDAO.updateCycle(newCycle);
           chooseCycle.setVisibility(View.GONE);

                break;
            case R.id.btn_period_save:
                dataUserDAO.updatePeriod(newPeriod);
                choosePeriod.setVisibility(View.GONE);
                break;
            case R.id.btn_period_cancel:
                choosePeriod.setVisibility(View.GONE);

                break;
            default:
                break;
        }

    }

}

