package com.poly.mycalendar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.poly.mycalendar.databinding.ActivityTipsBinding;


public class TipsActivity extends AppCompatActivity {
    private ActivityTipsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        ininActions();

    }


    private void ininActions() {
        binding.tip1.setOnClickListener(v ->
                {
                    if (binding.tvContent1.getVisibility() == View.GONE){
                        binding.lnTip1.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent1.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip1.setBackgroundColor(Color.WHITE);
                        binding.tvContent1.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip2.setOnClickListener(v ->
                {
                    if (binding.tvContent2.getVisibility() == View.GONE){
                        binding.lnTip2.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent2.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip2.setBackgroundColor(Color.WHITE);
                        binding.tvContent2.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip3.setOnClickListener(v ->
                {
                    if (binding.tvContent3.getVisibility() == View.GONE){
                        binding.lnTip3.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent3.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip3.setBackgroundColor(Color.WHITE);
                        binding.tvContent3.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip4.setOnClickListener(v ->
                {
                    if (binding.tvContent4.getVisibility() == View.GONE){
                        binding.lnTip4.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent4.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip4.setBackgroundColor(Color.WHITE);
                        binding.tvContent4.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip5.setOnClickListener(v ->
                {
                    if (binding.tvContent5.getVisibility() == View.GONE){
                        binding.lnTip5.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent5.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip5.setBackgroundColor(Color.WHITE);
                        binding.tvContent5.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip6.setOnClickListener(v ->
                {
                    if (binding.tvContent6.getVisibility() == View.GONE){
                        binding.lnTip6.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent6.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip6.setBackgroundColor(Color.WHITE);
                        binding.tvContent6.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip7.setOnClickListener(v ->
                {
                    if (binding.tvContent7.getVisibility() == View.GONE){
                        binding.lnTip7.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent7.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip7.setBackgroundColor(Color.WHITE);
                        binding.tvContent7.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip8.setOnClickListener(v ->
                {
                    if (binding.tvContent8.getVisibility() == View.GONE){
                        binding.lnTip8.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent8.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip8.setBackgroundColor(Color.WHITE);
                        binding.tvContent8.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip9.setOnClickListener(v ->
                {
                    if (binding.tvContent9.getVisibility() == View.GONE){
                        binding.lnTip9.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent9.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip9.setBackgroundColor(Color.WHITE);
                        binding.tvContent9.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip10.setOnClickListener(v ->
                {
                    if (binding.tvContent10.getVisibility() == View.GONE){
                        binding.lnTip10.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent10.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip10.setBackgroundColor(Color.WHITE);
                        binding.tvContent10.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip11.setOnClickListener(v ->
                {
                    if (binding.tvContent11.getVisibility() == View.GONE){
                        binding.lnTip11.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent11.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip11.setBackgroundColor(Color.WHITE);
                        binding.tvContent11.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip12.setOnClickListener(v ->
                {
                    if (binding.tvContent12.getVisibility() == View.GONE){
                        binding.lnTip12.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent12.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip12.setBackgroundColor(Color.WHITE);
                        binding.tvContent12.setVisibility(View.GONE);
                    }

                }

        );
        binding.tip13.setOnClickListener(v ->
                {
                    if (binding.tvContent13.getVisibility() == View.GONE){
                        binding.lnTip1.setBackgroundColor(Color.parseColor("#F8F3F5"));
                        binding.tvContent13.setVisibility(View.VISIBLE);
                    }else {
                        binding.lnTip13.setBackgroundColor(Color.WHITE);
                        binding.tvContent13.setVisibility(View.GONE);
                    }

                }

        );
    }




}