package com.poly.mycalendar.fragment.features;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.poly.mycalendar.GloabalUtils;
import com.poly.mycalendar.R;
import com.poly.mycalendar.data.DataUserDAO;
import com.savvi.rangedatepicker.CalendarPickerView;
import com.savvi.rangedatepicker.SubTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.poly.mycalendar.GloabalUtils.monthCurrent;
import static com.poly.mycalendar.GloabalUtils.toDay;
import static com.poly.mycalendar.GloabalUtils.yearCurrent;


public class MenstrualDiaryFragment extends Fragment {
    private View view;
    private CalendarPickerView calendar;
    private DataUserDAO dataUserDAO;
    int cycleLength = 0;
    int periodLength = 0;
    private String dayStart = "";
    private int getDay;
    private int getMonth;
    private int getYear;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_menstrual_diary, container, false);
            initViews();
            getInfor();
            initComponents();
        }
        return view;
    }

    private void getInfor() {
        dataUserDAO = new DataUserDAO(getContext());
        cycleLength = dataUserDAO.getCycle();
        periodLength = dataUserDAO.getPeriod();
        dayStart = dataUserDAO.getDayStart();
        getDay = Integer.parseInt(dayStart.substring(8, 10));
        Log.e("ngay do la", getDay + "");
        getMonth = Integer.parseInt(dayStart.substring(5, 7));
        getYear = Integer.parseInt(dayStart.substring(0, 4));

    }

    private void initViews() {
        calendar = view.findViewById(R.id.calendar_view);

    }

    private void initComponents() {
        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 12);
        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, -1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        calendar.deactivateDates(list);
        String dateInString = dayStart;


        ArrayList<Date> arrayList = new ArrayList<>();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");


        Calendar c = Calendar.getInstance();
        try {
            c.setTime(dateformat.parse(dateInString));
            c.add(Calendar.DATE, cycleLength);

            java.sql.Date resultdate = new java.sql.Date(c.getTimeInMillis());
            dateInString = dateformat.format(resultdate);

          for (int j=getMonth;j<12;j++){



          }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        try {
            String date = "";
            for (int i = getDay; i < getDay + periodLength; i++) {
                date = yearCurrent + "/" + getMonth + "/" + i;
                Date newdate2 = dateformat.parse(date);


                arrayList.add(newdate2);


            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withDeactivateDates(list)
//                .withSubTitles(getSubTitles())
                .withHighlightedDates(arrayList);

        calendar.scrollToDate(new Date());

    }

    private ArrayList<SubTitle> getSubTitles() {
        final ArrayList<SubTitle> subTitles = new ArrayList<>();
        final Calendar tmrw = Calendar.getInstance();
        tmrw.add(Calendar.DAY_OF_MONTH, 1);
        subTitles.add(new SubTitle(tmrw.getTime(), "₹1000"));
        return subTitles;
    }
}