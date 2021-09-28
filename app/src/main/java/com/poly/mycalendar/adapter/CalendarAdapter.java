package com.poly.mycalendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.model.CalendarItem;
import com.poly.mycalendar.utils.CustomView;
import com.poly.mycalendar.utils.GloabalUtils;

import java.util.ArrayList;
import java.util.Calendar;

import static com.poly.mycalendar.utils.GloabalUtils.monthCurrent;
import static com.poly.mycalendar.utils.GloabalUtils.toDay;
import static com.poly.mycalendar.utils.GloabalUtils.yearCurrent;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<String> daysOfMonth;
    private ArrayList<CalendarItem> list;

    public CalendarAdapter(ArrayList<String> daysOfMonth, ArrayList<CalendarItem> list, OnItemListener onItemListener, Context context) {
        this.daysOfMonth = daysOfMonth;
        this.list = list;
        this.onItemListener = onItemListener;
        this.context = context;
    }

    private final OnItemListener onItemListener;
    private Context context;


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        CalendarItem calendarItem = list.get(position);


        holder.dayOfMonth.setText(daysOfMonth.get(position));
        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(calendarItem.getDate());
        if (holder.dayOfMonth.getText().equals("")) {
            holder.cardView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (calendarItem.getStatus() == CalendarItem.RED){
            holder.dayOfMonth.setTextColor(Color.WHITE);
            holder.cardView.setCardBackgroundColor(Color.RED);
        }
        if(holder.dayOfMonth.getText().equals(String.valueOf(toDay))){
            holder.dayOfMonth.setTextColor(context.getResources().getColor(R.color.pink_2));
        }



    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
