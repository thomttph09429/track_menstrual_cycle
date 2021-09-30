package com.poly.mycalendar.fragment.features;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.activity.SymptomActivity;
import com.poly.mycalendar.adapter.CalendarAdapter;
import com.poly.mycalendar.data.DataUserDAO;
import com.poly.mycalendar.model.DayItem;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.poly.mycalendar.utils.GloabalUtils.selectedDate;


public class MenstrualDiaryFragment extends Fragment implements View.OnClickListener, CalendarAdapter.OnItemListener {
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
    private Button btnPre, btnNext;
    private LinearLayout note;


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
        Log.e("day satr", dayStart + "");//2021-09-10

    }

    private void initViews() {
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView);
        monthYearText = view.findViewById(R.id.monthYearTV);
        btnPre = view.findViewById(R.id.btn_pre);
        btnNext = view.findViewById(R.id.btn_next);
        note = view.findViewById(R.id.ln_note);
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        note.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        List<DayItem> dayItems = daysInMonthArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(dayItems, getContext(), this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<DayItem> daysInMonthArray(LocalDate date) {
        List<DayItem> dayItems = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate prevMonth = selectedDate.minusMonths(1);
        LocalDate nextMonth = selectedDate.plusMonths(1);
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        YearMonth prevYearMonth = YearMonth.from(prevMonth);
        int prevDaysInMonth = prevYearMonth.lengthOfMonth();
        int step = 0;
        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                dayItems.add(null);

            } else {
                String dateEnd = "";
                int dayEnd = 0;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(dayStart));
                    c.add(Calendar.DATE, periodLength);
                    Date resultdate = new Date(c.getTimeInMillis());
                    dateEnd = sdf.format(resultdate);
                    dayEnd = Integer.parseInt(dateEnd.substring(8, 10));


                } catch (ParseException e) {
                    e.printStackTrace();
                }


                int status = DayItem.DEFAULT;


//                int day = i + dayOfWeek;
//
//                if (getMonth == selectedDate.getMonthValue()) {
//
//                    for (int j = getDay; j <dayEnd;j++){
//                        if (j == day)
//                            status = DayItem.RED;
//                        Log.e("ffff", j + "");
//
//                    }
//
//                }


//                dayItems.add(new DayItem(status, LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek), step == 0));
//                step++;
//                if (step >= cycleLength)
//                    step = 0;


                if (step < periodLength)
                    status = DayItem.RED;

                dayItems.add(new DayItem(status, LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek), step == 0));
                step++;
                if (step >= cycleLength)
                    step = 0;

            }
        }

        return dayItems;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pre:
                previousMonthAction();
                break;
            case R.id.btn_next:
                nextMonthAction();
                break;
            case R.id.ln_note:
                noteAction();
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void noteAction() {
        Intent intent = new Intent(getContext(), SymptomActivity.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = selectedDate.format(formatter);
        Log.e("ddkkkkkkkkk", formattedString + "");
        intent.putExtra("date", formattedString);
        getContext().startActivity(intent);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, DayItem date) {

        if (date != null) {
            LocalDate localDate = date.getDate();
            selectedDate = localDate;
            setMonthView();

        }
    }


}