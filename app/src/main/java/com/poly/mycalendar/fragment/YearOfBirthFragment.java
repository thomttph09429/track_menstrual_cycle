package com.poly.mycalendar.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poly.mycalendar.GloabalUtils;
import com.poly.mycalendar.R;

import java.util.Calendar;

import static com.poly.mycalendar.GloabalUtils.yearCurrent;


public class YearOfBirthFragment extends BottomSheetDialogFragment {

    private NumberPicker pickerYear;
    private View view;
    private Button btnChoose;
    private int defaultYear = 1999;
    private int userChoose = 1999;
    private YearOfBirthFragment.ItemClickListenery mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_year_of_birth, container, false);
            userChoose = getArguments().getInt("key");

            initViews();
            getValue();
            pickerYear.setOnValueChangedListener((picker, oldVal, newVal) -> {
                defaultYear = newVal;
            });
            btnChoose.setOnClickListener(v -> {
                mListener.onItemClicky(defaultYear);
                dismiss();
            });

        }
        return view;
    }


    private void initViews() {
        pickerYear = view.findViewById(R.id.picker_year);
        btnChoose = view.findViewById(R.id.btn_choose);
    }
    private void getValue() {
        pickerYear.setMaxValue(yearCurrent);
        pickerYear.setMinValue(1931);
        pickerYear.setWrapSelectorWheel(false);
        pickerYear.setValue(1999);
        if (userChoose==defaultYear) {
            pickerYear.setValue(defaultYear);
        } else {
            pickerYear.setValue(userChoose);
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListenery) {
            mListener = (ItemClickListenery) context;
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
    public interface ItemClickListenery {
        void onItemClicky(int yearOfBirth);

    }
}