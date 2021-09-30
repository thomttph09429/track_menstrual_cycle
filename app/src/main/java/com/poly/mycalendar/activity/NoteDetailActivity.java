package com.poly.mycalendar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.poly.mycalendar.R;

public class NoteDetailActivity extends AppCompatActivity {
    String  statusNote;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        initViews();

        Intent intent = getIntent();
        statusNote = intent.getStringExtra("note");
        tvTitle.setText(String.valueOf(statusNote));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void initViews() {
        tvTitle=findViewById(R.id.tv_title);
    }
    private void initMoodDetail(){

    }
}