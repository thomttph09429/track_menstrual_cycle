package com.poly.mycalendar.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.poly.mycalendar.GloabalUtils;
import com.poly.mycalendar.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.poly.mycalendar.GloabalUtils.getDate;


public class DaysFragment extends BottomSheetDialogFragment {

    private View view;
    private Button btnChoose;
    private CheckBox checkBox;
    private CalendarView calendar;
    private DaysFragment.ItemClickListenerd mListener;
    private DaysFragment.notRemember mNotRemember;

    private int day, month, year;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_days, container, false);
            initViews();
            isCheck();

            day = getArguments().getInt("day");
            month = getArguments().getInt("month");
            year = getArguments().getInt("year");

            calendar.setOnDateChangeListener((view1, years, months, dayOfMonth) -> {
                day = dayOfMonth;
                month = months;
                year = years;
            });

            btnChoose.setOnClickListener(v -> {
                mListener.onItemClickd(day, month, year);
                dismiss();
            });
            getValue();

        }
        return view;
    }

    private void getValue() {

            Calendar ca = Calendar.getInstance();
            ca.set(Calendar.YEAR, year);
            ca.set(Calendar.MONTH, month);
            ca.set(Calendar.DAY_OF_MONTH, day);
            long longTYpeVariable = ca.getTimeInMillis();
            calendar.setDate(longTYpeVariable);




    }

    private void initViews() {
        btnChoose = view.findViewById(R.id.btn_choose);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        calendar = view.findViewById(R.id.calendar);

    }

    private void isCheck() {
        checkBox.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                mNotRemember.onItemClickr("Check");
                        new AlertDialog.Builder(getContext())
                        .setMessage(" You can log the first day of your last period later.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {



                            }
                        })

                        .show();
                dismiss();
            }else {
                mNotRemember.onItemClickr("");

            }

        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListenerd) {
            mListener = (ItemClickListenerd) context;
        }
      if (context instanceof notRemember){
            mNotRemember = (notRemember) context;

        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ItemClickListenerd {
        void onItemClickd(int day, int month, int year);

    }
    public interface notRemember {
        void onItemClickr(String isChecked);

    }
}