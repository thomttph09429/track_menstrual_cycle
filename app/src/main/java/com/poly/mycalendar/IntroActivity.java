package com.poly.mycalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.poly.mycalendar.IntroViewPagerAdapter;
import com.poly.mycalendar.MainActivity;
import com.poly.mycalendar.R;
import com.poly.mycalendar.activity.SetUpActivity;
import com.poly.mycalendar.model.ScreenItem;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenViewPager;
    private TabLayout tabIndicator;
    private Button btnNext;
    private int indicatorPosition;
    private List<ScreenItem> itemList;
    private Button btnGetStarted;
    private Animation animGetStarted;
    private TextView tvSkip;
    private static String pref_key_name = "main_preferences";
    private static String pref_is_intro_opened = "isIntroOpened";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        checkIntroStatusOnPreferences();
        initComponents();
        setMockData();
        setupViewPager();
        setBtnNextEvent();
        setTabIndicatorListener();
        setBtnGetStarted();
        setSkipEvent();
    }
    private void setupViewPager() {
        //Setup ViewPager
        screenViewPager = findViewById(R.id.screen_viewpager);
        IntroViewPagerAdapter introViewPagerAdapter = new IntroViewPagerAdapter(this, itemList);
        screenViewPager.setAdapter(introViewPagerAdapter);
        // Setup TabLayout with ViewPager
        tabIndicator.setupWithViewPager(screenViewPager);
    }

    private void setMockData() {
        itemList = new ArrayList<>();
        itemList.add(new ScreenItem("Track & predict", "your next period, ovulation and fertility ", R.drawable.ic_calender1));
        itemList.add(new ScreenItem("Get real-time", "notification", R.drawable.ic_calender2));
        itemList.add(new ScreenItem("One-time Setup", "various mood & symptoms tracking", R.drawable.ic_calender3));
        itemList.add(new ScreenItem("Handbook ", "of diet and nutrition in the menstrual cycle", R.drawable.ic_calender4));

    }
    private void initComponents() {
        tabIndicator = findViewById(R.id.tab_indicator);
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tvSkip = findViewById(R.id.tv_skip);
        animGetStarted = AnimationUtils.loadAnimation(btnGetStarted.getContext(), R.anim.button_getstarted_anim);
    }

    private void setBtnNextEvent(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                indicatorPosition = screenViewPager.getCurrentItem();
                if (indicatorPosition < itemList.size()) {
                    indicatorPosition++;
                    screenViewPager.setCurrentItem(indicatorPosition);
                }

                // The last screen of ViewPager
                if (indicatorPosition == itemList.size()-1){
                    loadLastScreen();
                }
            }
        });
    }

    private void setBtnGetStarted(){
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(btnGetStarted.getContext(), SetUpActivity.class));
                saveIntroStatusOnPreferences();
                finish();
            }
        });
    }
    private void setSkipEvent() {
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenViewPager.setCurrentItem(itemList.size());
            }
        });
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.GONE);
        tabIndicator.setVisibility(View.INVISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        btnGetStarted.setAnimation(animGetStarted);
    }

    private void setTabIndicatorListener(){
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == itemList.size()-1) {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void saveIntroStatusOnPreferences() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(pref_key_name, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(pref_is_intro_opened, true);
        editor.apply();
    }

    private void checkIntroStatusOnPreferences(){
        if (getPrefIntroStatus()) {
            startActivity(new Intent(this, SetUpActivity.class));
            finish();
        }
    }

    private boolean getPrefIntroStatus() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(pref_key_name, MODE_PRIVATE);
        return preferences.getBoolean(pref_is_intro_opened, false);
    }
}