package com.poly.mycalendar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.data.NoteDayDAO;
import com.poly.mycalendar.model.DayItem;
import com.poly.mycalendar.utils.GloabalUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private List<DayItem> myDates;
    private Context context;
    private final OnItemListener onItemListener;
    private NoteDayDAO noteDayDAO;

    public CalendarAdapter(List<DayItem> myDates, Context context, OnItemListener onItemListener) {
        this.myDates = myDates;
        this.context = context;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_calendar_cell, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener, myDates);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        noteDayDAO = new NoteDayDAO(context);
        final DayItem dayItem = myDates.get(position);

        if (dayItem == null) {
            holder.dayOfMonth.setText("");
            holder.dayOfMonth.setBackgroundColor(Color.TRANSPARENT);

        } else {

            LocalDate dayOfMonth = dayItem.getDate();

            String date = String.valueOf(dayOfMonth.getDayOfMonth());
            holder.dayOfMonth.setText(date);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
            String formattedString = dayOfMonth.format(formatter);
            if (noteDayDAO.isNote(formattedString)) {
                holder.ivNote.setVisibility(View.VISIBLE);
            }
            if (dayOfMonth.equals(GloabalUtils.selectedDate)) {
                holder.dayOfMonth.setTextColor(Color.WHITE);
                holder.dayOfMonth.setBackgroundResource(R.drawable.day_green);


            } else if (dayItem.getStatus() == DayItem.RED) {
                holder.dayOfMonth.setTextColor(Color.WHITE);
                holder.dayOfMonth.setBackgroundResource(R.drawable.day_red);

            } else
                holder.dayOfMonth.setTextColor(Color.BLACK);


        }


    }

    @Override
    public int getItemCount() {
        return myDates.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, DayItem date);
    }
}
