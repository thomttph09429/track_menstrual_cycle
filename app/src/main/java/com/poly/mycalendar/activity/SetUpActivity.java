package com.poly.mycalendar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.poly.mycalendar.GloabalUtils;
import com.poly.mycalendar.R;
import com.poly.mycalendar.data.DataUserDAO;
import com.poly.mycalendar.fragment.CycleFragment;
import com.poly.mycalendar.fragment.DaysFragment;
import com.poly.mycalendar.fragment.PeriodFragment;
import com.poly.mycalendar.fragment.YearOfBirthFragment;
import com.poly.mycalendar.model.DataUser;

import java.util.ArrayList;
import java.util.List;

import static com.poly.mycalendar.GloabalUtils.monthCurrent;
import static com.poly.mycalendar.GloabalUtils.toDay;
import static com.poly.mycalendar.GloabalUtils.yearCurrent;
import static com.poly.mycalendar.data.DataUserDAO.TABLE_NAME;

public class SetUpActivity extends AppCompatActivity implements View.OnClickListener, CycleFragment.ItemClickListener, PeriodFragment.ItemClickListenerp, DaysFragment.ItemClickListenerd, YearOfBirthFragment.ItemClickListenery, DaysFragment.notRemember {
    BottomSheetBehavior sheetBehavior;
    private RelativeLayout cycleDialog;
    private Button btnPeriodStart, btnCycle, btnPeriod, btnYearOfBirth, btnNext;
    private int cycle = 28;
    private int period = 5;
    private int dayChoose = toDay;
    private int monthChoose = monthCurrent;
    private int yearChoose = yearCurrent;
    private int yearOfBirthChoose = 1999;
    private DataUserDAO dataUserDAO;
    private String dates="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        initViews();
        initActions();
        if (dataUserDAO.checkAlreadyExist()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }


    private void initViews() {
        cycleDialog = findViewById(R.id.bottom_sheet);
        btnCycle = findViewById(R.id.btn_cycle);
        btnPeriod = findViewById(R.id.btn_period);
        btnPeriodStart = findViewById(R.id.btn_period_start);
        btnYearOfBirth = findViewById(R.id.btn_year_of_birth);
        btnNext = findViewById(R.id.btn_next);

    }

    private void initActions() {
        btnCycle.setOnClickListener(this);
        btnPeriod.setOnClickListener(this);
        btnPeriodStart.setOnClickListener(this);
        btnYearOfBirth.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        dataUserDAO = new DataUserDAO(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cycle:
                CycleFragment cycleFragment = new CycleFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("key", cycle);
                cycleFragment.setArguments(bundle);
                cycleFragment.show(getSupportFragmentManager(), "cycle");

                break;
            case R.id.btn_period:

                PeriodFragment periodFragment = new PeriodFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("key", period);
                periodFragment.setArguments(bundle1);
                periodFragment.show(getSupportFragmentManager(), "period");
                break;
            case R.id.btn_period_start:
                DaysFragment daysFragment = new DaysFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("day", dayChoose);
                bundle2.putInt("month", monthChoose);
                bundle2.putInt("year", yearChoose);

                daysFragment.setArguments(bundle2);
                daysFragment.show(getSupportFragmentManager(), "days");
                break;
            case R.id.btn_year_of_birth:
                YearOfBirthFragment year = new YearOfBirthFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("key", yearOfBirthChoose);
                year.setArguments(bundle3);
                year.show(getSupportFragmentManager(), "year");
                break;
            case R.id.btn_next:
                String text1 = btnCycle.getText().toString();
                String text2 = btnPeriod.getText().toString();
                String text3 = btnPeriodStart.getText().toString();
                String text4 = btnYearOfBirth.getText().toString();

                if (!text1.equalsIgnoreCase("choose") && !text2.equalsIgnoreCase("choose") && !text3.equalsIgnoreCase("choose") && !text4.equalsIgnoreCase("choose")) {

                    insertDataUser();
                } else {
                    Toast.makeText(this,"Please enter information!", Toast.LENGTH_SHORT).show();

                }
                break;

            default:
                break;
        }

    }

    private void insertDataUser() {
        DataUser dataUser = new DataUser();
        dataUser.setId(0);
        dataUser.setCycle(cycle);
        dataUser.setPeriod(period);
        dataUser.setDayStart(dates);
        dataUser.setYearOfBirth(yearOfBirthChoose);
        long result = dataUserDAO.insert(dataUser);
        if (result > 0) {

            Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
        }

    }

    @Override
    public void onItemClick(int number) {
        cycle = number;
        btnCycle.setText(number + "");

    }


    @Override
    public void onItemClickp(int number) {
        period = number;
        btnPeriod.setText(number + "");

    }


    @Override
    public void onItemClicky(int yearOfBirth) {
        yearOfBirthChoose = yearOfBirth;
        btnYearOfBirth.setText(yearOfBirth + "");

    }


    @Override
    public void onItemClickr(String isChecked) {
        btnPeriodStart.setText("I am not sure");




    }

    @Override
    public void onItemClickd(int day, int month, int year, String date) {

        dayChoose = day;
        monthChoose = month;
        yearChoose = year;
        dates=date;
        btnPeriodStart.setText(date);



    }
}