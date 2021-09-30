package com.poly.mycalendar.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.adapter.NoteAdapter;
import com.poly.mycalendar.model.Mood;

import java.util.ArrayList;
import java.util.List;

public class SymptomActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvMood, rvSymptom, rvDischarge,rvActivity, rvOther, rvMedicine,rvSex;
    private String time;
    private TextView tvDate;
    private List<Mood> moods;
    private RelativeLayout seeMood, seeSymptoms;
    private Button btnDone;


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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        initAcitions();
        initSymptoms();
        initMood();
        initDischrge();
        initActivity();
        initOther();
        initSex();
        initMedicine();
    }

    private void initViews() {
        rvMood = findViewById(R.id.rv_mood);
        rvSymptom = findViewById(R.id.rv_symptom);
        rvDischarge = findViewById(R.id.rv_discharge);
        rvActivity = findViewById(R.id.rv_activity);
        rvOther = findViewById(R.id.rv_other);
        rvSex = findViewById(R.id.rv_sex);
        rvMedicine = findViewById(R.id.rv_medicine);
        tvDate = findViewById(R.id.tv_date);
        seeMood = findViewById(R.id.see_mood);
        seeSymptoms = findViewById(R.id.see_symptoms);
        btnDone = findViewById(R.id.btn_done);
    }

    private void initAcitions() {
        seeMood.setOnClickListener(this);
        seeSymptoms.setOnClickListener(this);
        btnDone.setOnClickListener(this);

    }

    private void initMood() {
        moods = new ArrayList<>();
        moods.add(new Mood("Nomal", R.drawable.ic_mood_normal));
        moods.add(new Mood("Happy", R.drawable.ic_mood_happy));
        moods.add(new Mood("Sensitive", R.drawable.ic_mood_swings));
        moods.add(new Mood("Sad", R.drawable.ic_mood_sad));
        moods.add(new Mood("Angry", R.drawable.ic_mood_angry));
        moods.add(new Mood("Tired", R.drawable.ic_mood_tired));
        moods.add(new Mood("Lonely", R.drawable.ic_mood_lonely));
        moods.add(new Mood("Tense", R.drawable.ic_mood_tense));
        moods.add(new Mood("Depressed", R.drawable.ic_mood_stressed));
        moods.add(new Mood("Frisky", R.drawable.ic_mood_frisky));
        moods.add(new Mood("Anxious", R.drawable.ic_mood_anxious));
        moods.add(new Mood("Panicky", R.drawable.ic_mood_panicky));

        LinearLayoutManager moodLayout = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvMood.setLayoutManager(moodLayout);
        rvMood.setHasFixedSize(true);
        NoteAdapter noteAdapter = new NoteAdapter(moods, this, "mood", time);
        rvMood.setAdapter(noteAdapter);


    }

    private void initSymptoms() {
        moods = new ArrayList<>();
        moods.add(new Mood("Cramps", R.drawable.ic_sy_cramps));
        moods.add(new Mood("Everything is fine", R.drawable.ic_sy_everythingis_fine));
        moods.add(new Mood("Acne", R.drawable.ic_sy_acne));
        moods.add(new Mood("PMS", R.drawable.ic_sy_pms));
        moods.add(new Mood("Headache", R.drawable.ic_sy_headache));
        moods.add(new Mood("Dizziness", R.drawable.ic_sy_dizziness));
        moods.add(new Mood("Breast pain", R.drawable.ic_sy_pain));
        moods.add(new Mood("Bloating", R.drawable.ic_sy_bloating));
        moods.add(new Mood("Body aches", R.drawable.ic_sy_body_ache));
        moods.add(new Mood("Neck aches", R.drawable.ic_sy_neckaches));
        moods.add(new Mood("Shoulder aches", R.drawable.ic_shoulder_ache));
        moods.add(new Mood("Backaches", R.drawable.ic_sy_backaches));
        moods.add(new Mood("Lower back pain", R.drawable.ic_sy_lower_back_pain));
        moods.add(new Mood("Nauses", R.drawable.ic_sy_nausea));
        moods.add(new Mood("Constipation", R.drawable.ic_sy_constipation));
        moods.add(new Mood("Diarrhea", R.drawable.ic_sy_diarrhea));
//
        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvSymptom.setLayoutManager(moodLayouts);
        rvSymptom.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "symptoms", time);
        rvSymptom.setAdapter(symptomAdapter);
    }

    private void initDischrge() {
        moods = new ArrayList<>();
        moods.add(new Mood("No Discharge", R.drawable.no_discharge));
        moods.add(new Mood("Spotting", R.drawable.spotting));
        moods.add(new Mood("Sticky", R.drawable.watery));
        moods.add(new Mood("Creamy", R.drawable.sticky));
        moods.add(new Mood("Unusual", R.drawable.unusual));
        moods.add(new Mood("Watery", R.drawable.watery));

        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvDischarge.setLayoutManager(moodLayouts);
        rvDischarge.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "discharge", time);
        rvDischarge.setAdapter(symptomAdapter);
    }
    private void initActivity() {
        moods = new ArrayList<>();
        moods.add(new Mood("Didn't exercise", R.drawable.didnt_exercise));
        moods.add(new Mood("Running", R.drawable.running));
        moods.add(new Mood("Cycling", R.drawable.cycling));
        moods.add(new Mood("Gym", R.drawable.gym));
        moods.add(new Mood("Yoga", R.drawable.yoga));
        moods.add(new Mood("Swimming", R.drawable.swimming));
        moods.add(new Mood("Team sports", R.drawable.teamsports));
        moods.add(new Mood("Aerobic & Dancing", R.drawable.aerobics_dancing));

        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvActivity.setLayoutManager(moodLayouts);
        rvActivity.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "physicalActivity", time);
        rvActivity.setAdapter(symptomAdapter);
    }
    private void initOther() {
        moods = new ArrayList<>();
        moods.add(new Mood("Travel", R.drawable.travel));
        moods.add(new Mood("Stress", R.drawable.stress));
        moods.add(new Mood("Disease or injury", R.drawable.disease_or_injury));
        moods.add(new Mood("Alcohol", R.drawable.alcohol));


        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvOther.setLayoutManager(moodLayouts);
        rvOther.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "other", time);
        rvOther.setAdapter(symptomAdapter);
    }

    private void initSex() {
        moods = new ArrayList<>();
        moods.add(new Mood("Nope", R.drawable.ic_sex_no_l));
        moods.add(new Mood("Unprotected", R.drawable.ic_sex_noprotect_l));
        moods.add(new Mood("Protected", R.drawable.ic_sex_protect_l));

        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvSex.setLayoutManager(moodLayouts);
        rvSex.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "sex", time);
        rvSex.setAdapter(symptomAdapter);
    }

    private void initMedicine() {
        moods = new ArrayList<>();
        moods.add(new Mood("Take pill", R.drawable.ic_take_pill));
        LinearLayoutManager moodLayouts = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvMedicine.setLayoutManager(moodLayouts);
        rvMedicine.setHasFixedSize(true);
        NoteAdapter symptomAdapter = new NoteAdapter(moods, this, "medicine", time);
        rvMedicine.setAdapter(symptomAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.see_mood:
                seeMoodDetail();
                break;
            case R.id.see_symptoms:
                break;
            case R.id.btn_done:
                finish();
                break;
            default:
                break;
        }

    }

    private void seeMoodDetail() {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("note", "Mood");
        startActivity(intent);
    }
}