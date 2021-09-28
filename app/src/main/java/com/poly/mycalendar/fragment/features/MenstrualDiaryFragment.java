package com.poly.mycalendar.fragment.features;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.poly.mycalendar.R;
import com.poly.mycalendar.adapter.CalendarAdapter;
import com.poly.mycalendar.data.DataUserDAO;
import com.poly.mycalendar.model.CalendarItem;
import com.poly.mycalendar.utils.CustomView;
import com.poly.mycalendar.utils.GloabalUtils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static com.poly.mycalendar.utils.GloabalUtils.monthCurrent;
import static com.poly.mycalendar.utils.GloabalUtils.toDay;
import static com.poly.mycalendar.utils.GloabalUtils.yearCurrent;


public class MenstrualDiaryFragment extends Fragment implements CalendarAdapter.OnItemListener, View.OnClickListener {
    private View view;
    private DataUserDAO dataUserDAO;
    int cycleLength = 0;
    int periodLength = 0;
    private String dayStart = "";
    private int getDay;
    private int getMonth;
    private int getYear;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private Button btnPre, btnNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_menstrual_diary, container, false);
            initViews();
            getInfor();
            selectedDate = LocalDate.now();
            setMonthView();




        }
        return view;
    }

    private void getInfor() {
        dataUserDAO = new DataUserDAO(getContext());
        cycleLength = dataUserDAO.getCycle();
        periodLength = dataUserDAO.getPeriod();
        dayStart = dataUserDAO.getDayStart();
        getDay = Integer.parseInt(dayStart.substring(8, 10));
        getMonth = Integer.parseInt(dayStart.substring(5, 7));
        getYear = Integer.parseInt(dayStart.substring(0, 4));


    }

    private void initViews() {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        btnPre = view.findViewById(R.id.btn_pre);
        btnNext = view.findViewById(R.id.btn_next);
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
         ArrayList<CalendarItem> arr=new ArrayList<>();

        int count = cycleLength;
        int red = periodLength;
        Calendar c = Calendar.getInstance();
        int day = getDay;
        c.set(Calendar.DAY_OF_MONTH, getDay);
        c.set(Calendar.MONTH, getMonth);
        c.set(Calendar.YEAR, getYear);


        int step = 0;
        for (int i = 0; i < 365; i++) {
            long dates = c.getTimeInMillis();
            int status = CalendarItem.DEFAULT;
            if (step < red)
                status = CalendarItem.RED;
            arr.add(new CalendarItem(status, dates));
            step++;
            if (step > count)
                step = 0;
            c.add(day, 1);
        }
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, arr, this, getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();


        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");

            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction() {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction() {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre:
                previousMonthAction();
                break;
            case R.id.btn_next:
                nextMonthAction();
                break;
            default:
                break;
        }
    }
}