package com.poly.mycalendar.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.adapter.MoodAdapter;
import com.poly.mycalendar.adapter.SymptomAdapter;
import com.poly.mycalendar.model.Mood;
import com.poly.mycalendar.model.Symptoms;

import java.util.ArrayList;
import java.util.List;

public class SymptomActivity extends AppCompatActivity {
    private RecyclerView rvMood, rvSymptom, rv_Discharge;
    private String time;
    private TextView tvDate;
    private List<Mood> moods;
    private  List<Symptoms> symptoms;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        setTitle("");
        Intent intent = getIntent();
        time = intent.getStringExtra("date");
        tvDate.setText(String.valueOf(time));
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        initMood();
        initSymptoms();
    }

    private void initViews() {
        rvMood = findViewById(R.id.rv_mood);
        rvSymptom = findViewById(R.id.rv_symptom);
        rv_Discharge = findViewById(R.id.rv_discharge);
        tvDate = findViewById(R.id.tv_date);

    }

    private void initMood() {
        moods = new ArrayList<>();
        moods.add(new Mood("Nomal",R.drawable.ic_normal));
        moods.add(new Mood("Happy",R.drawable.ic_happy));
        moods.add(new Mood("Sensitive",R.drawable.ic_sensitive));
        moods.add(new Mood("Sad",R.drawable.ic_sad));
        moods.add(new Mood("Angry",R.drawable.ic_angry));
        moods.add(new Mood("Tired",R.drawable.ic_tired));
        moods.add(new Mood("Lonely",R.drawable.ic_lonely));
        moods.add(new Mood("Anxious",R.drawable.ic_anxious));
        LinearLayoutManager moodLayout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvMood.setLayoutManager(moodLayout);
        rvMood.setHasFixedSize(true);
        MoodAdapter moodAdapter = new MoodAdapter(moods, this);
        rvMood.setAdapter(moodAdapter);




    }
    private void initSymptoms() {
        moods = new ArrayList<>();
        moods.add(new Mood("Headache",R.drawable.ic_headache));
        moods.add(new Mood("Everything is fine",R.drawable.ic_like));
        moods.add(new Mood("Sensitive",R.drawable.ic_sensitive));
        moods.add(new Mood("Sad",R.drawable.ic_sad));
        moods.add(new Mood("Angry",R.drawable.ic_angry));
        moods.add(new Mood("Tired",R.drawable.ic_tired));
        moods.add(new Mood("Lonely",R.drawable.ic_lonely));
        moods.add(new Mood("Anxious",R.drawable.ic_anxious));
        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvSymptom.setLayoutManager(moodLayouts);
        rvSymptom.setHasFixedSize(true);
        SymptomAdapter symptomAdapter = new SymptomAdapter(moods, this);
        rvSymptom.setAdapter(symptomAdapter);
    }
}