package com.poly.mycalendar.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poly.mycalendar.R;

public class CycleFragment extends BottomSheetDialogFragment {
    private NumberPicker pickerCicle;
    private View view;
    private Button btnChoose;
    private int defaultCycle = 28;
    private int userChoose = 28;
    private ItemClickListener mListener;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            userChoose = getArguments().getInt("key");
            view = inflater.inflate(R.layout.fragment_cycle, container, false);
            initViews();

            getValue();


            pickerCicle.setOnValueChangedListener((picker, oldVal, newVal) -> {
                defaultCycle = newVal;
            });
            btnChoose.setOnClickListener(v -> {
                mListener.onItemClick(defaultCycle);
                dismiss();
            });

        }
        return view;
    }

    private void initViews() {
        pickerCicle = view.findViewById(R.id.picker_cicle);
        btnChoose = view.findViewById(R.id.btn_choose);
    }

    private void getValue() {
        pickerCicle.setMaxValue(100);
        pickerCicle.setMinValue(0);
        pickerCicle.setWrapSelectorWheel(true);
        if (userChoose==defaultCycle) {
            pickerCicle.setValue(defaultCycle);
        } else {
            pickerCicle.setValue(userChoose);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ItemClickListener {
        void onItemClick(int number);

    }


}