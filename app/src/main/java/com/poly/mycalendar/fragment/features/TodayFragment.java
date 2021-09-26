package com.poly.mycalendar.fragment.features;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.data.DataUserDAO;
import com.poly.mycalendar.model.DataUser;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.poly.mycalendar.GloabalUtils;

import static com.poly.mycalendar.GloabalUtils.monthCurrent;
import static com.poly.mycalendar.GloabalUtils.toDay;
import static com.poly.mycalendar.GloabalUtils.yearCurrent;

public class TodayFragment extends Fragment {
    private View view;
    private CalendarView calendarView;
    private DataUserDAO dataUserDAO;
    int cycleLength = 0;
    int periodLength = 0;
    String dayStart = "";
    private TextView tvTimeLeft, tvdayStart, tvChangeDayStart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_today, container, false);
            initViews();
            initComponent();
            tvChangeDayStart.setOnClickListener(v -> {
                setDate(view);
            });

        }
        return view;
    }

    public void setDate(View v) {

        DatePickerDialog dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthofyear, int dayofmonth) {
            }
        }, yearCurrent, monthCurrent, toDay);
        dpd.show();
    }
    private void initViews() {
        calendarView = view.findViewById(R.id.calendar);
        tvTimeLeft = view.findViewById(R.id.tv_time_left);
        tvdayStart = view.findViewById(R.id.tv_start_day);
        tvChangeDayStart = view.findViewById(R.id.tv_change_day_start);
    }

    private void initComponent() {
        dataUserDAO = new DataUserDAO(getContext());
        cycleLength = dataUserDAO.getCycle();
        periodLength = dataUserDAO.getPeriod();
        dayStart = dataUserDAO.getDayStart();
        periodLength = dataUserDAO.getPeriod();
        dayStart = dataUserDAO.getDayStart();
        String dateInString = dayStart;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(dateInString));
            c.add(Calendar.DATE, cycleLength);

            Date resultdate = new Date(c.getTimeInMillis());
            dateInString = sdf.format(resultdate);
            tvdayStart.setText("Projected start date is " + dateInString + "");


            String timeCurrent = GloabalUtils.getDate(yearCurrent, monthCurrent, toDay);
            final SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date startDate = null;
            java.util.Date endDate = null;
            try {
                startDate = df.parse(timeCurrent);
                endDate = df.parse(dateInString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            long diff = endDate.getTime() - startDate.getTime();
            Log.e("Days: ", TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + "");
            tvTimeLeft.setText(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " Days left");


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void showTimeLeft() {

    }
}