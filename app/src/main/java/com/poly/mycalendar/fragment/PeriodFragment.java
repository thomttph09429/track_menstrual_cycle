package com.poly.mycalendar.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poly.mycalendar.R;

public class PeriodFragment extends BottomSheetDialogFragment {

    private NumberPicker pickPeriod;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_period, container, false);
            pickPeriod = view.findViewById(R.id.picker_period);
            pickPeriod.setMaxValue(100); // max value 100
            pickPeriod.setMinValue(0);   // min value 0
            pickPeriod.setWrapSelectorWheel(false);
        }
        return view;
    }
}