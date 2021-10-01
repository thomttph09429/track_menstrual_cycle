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

public class PeriodFragment extends BottomSheetDialogFragment {

    private NumberPicker pickPeriod;
    private View view;
    private Button btnChoose;
    private int defaultPeriod = 5;
    private int userChoose = 5;
    private ItemClickListenerp mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            userChoose = getArguments().getInt("key");

            view = inflater.inflate(R.layout.fragment_period, container, false);
            initViews();
            getValue();

            pickPeriod.setOnValueChangedListener((picker, oldVal, newVal) -> {
                defaultPeriod = newVal;
            });
            btnChoose.setOnClickListener(v -> {
                mListener.onItemClickp(defaultPeriod);
                dismiss();
            });

        }
        return view;
    }

    private void initViews() {
        pickPeriod = view.findViewById(R.id.picker_period);
        btnChoose = view.findViewById(R.id.btn_choose);
    }

    private void getValue() {
        pickPeriod.setMaxValue(14);
        pickPeriod.setMinValue(1);
        pickPeriod.setWrapSelectorWheel(true);
        if (userChoose==defaultPeriod) {
            pickPeriod.setValue(defaultPeriod);
        } else {
            pickPeriod.setValue(userChoose);
        }

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListenerp) {
            mListener = (ItemClickListenerp) context;
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
    public interface ItemClickListenerp {
        void onItemClickp(int number);

    }
}