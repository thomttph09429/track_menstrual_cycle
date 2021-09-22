package com.poly.mycalendar.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poly.mycalendar.R;

public class CycleFragment extends BottomSheetDialogFragment {
    private NumberPicker pickerCicle;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_cycle, container, false);
            pickerCicle= view.findViewById(R.id.picker_cicle);
            pickerCicle.setMaxValue(100); // max value 100
            pickerCicle.setMinValue(0);   // min value 0
            pickerCicle.setWrapSelectorWheel(false);
        }
        return view;
    }
}