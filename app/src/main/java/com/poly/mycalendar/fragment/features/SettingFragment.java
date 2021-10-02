package com.poly.mycalendar.fragment.features;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.mycalendar.R;
import com.poly.mycalendar.activity.SettingPassActivity;
import com.poly.mycalendar.activity.TipsActivity;
import com.poly.mycalendar.data.DataUserDAO;

public class SettingFragment extends Fragment implements View.OnClickListener {
    private LinearLayout editPeriodLength, edtCycleLenth;
    private View view;
    private NumberPicker pickerCycle, pickerPeriod;
    private Button btnPeriodCancel, btnPeriodSave, btnCycleCancel, btnCycleSave;
    private LinearLayout choosePeriod, chooseCycle, choosePregnancy, shareApp, feedBack, rateUs, settingPass, tips;
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
        shareApp = view.findViewById(R.id.share_app);
        feedBack = view.findViewById(R.id.feedback);
        rateUs = view.findViewById(R.id.rate_us);
        choosePregnancy = view.findViewById(R.id.choose_pregnancy);
        settingPass = view.findViewById(R.id.setting_pass);
        tips = view.findViewById(R.id.tips);

    }

    private void initAction() {
        editPeriodLength.setOnClickListener(this);
        edtCycleLenth.setOnClickListener(this);

        btnCycleSave.setOnClickListener(this);
        btnCycleCancel.setOnClickListener(this);
        btnPeriodSave.setOnClickListener(this);
        btnPeriodCancel.setOnClickListener(this);
        choosePregnancy.setOnClickListener(this);
        shareApp.setOnClickListener(this);
        feedBack.setOnClickListener(this);
        rateUs.setOnClickListener(this);
        settingPass.setOnClickListener(this);
        tips.setOnClickListener(this);

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
            newCycle = newVal;
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
            case R.id.choose_pregnancy:
                switchPregnancy();

                break;
            case R.id.share_app:
                shareApp();

                break;
            case R.id.feedback:
                feedBack();

                break;
            case R.id.rate_us:
                rate();

                break;
            case R.id.setting_pass:
                settingPass();

                break;
            case R.id.tips:
                tips();

                break;
            default:
                break;
        }

    }

    private void tips() {
        startActivity(new Intent(getContext(), TipsActivity.class));
    }

    private void settingPass() {
        startActivity(new Intent(getContext(), SettingPassActivity.class));
    }

    private void feedBack() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + "abcxyz@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about Anime Wallpapers application");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear...,");

        try {
            getContext().startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }

    private void rate() {
        String appPackageName = getContext().getPackageName();

        try {
            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException e) {
            getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    private void shareApp() {
        String appPackageName = getContext().getPackageName();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void switchPregnancy() {

    }

}

