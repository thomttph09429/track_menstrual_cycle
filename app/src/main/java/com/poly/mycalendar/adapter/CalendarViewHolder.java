package com.poly.mycalendar.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.model.DayItem;

import java.util.List;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final List<DayItem> days;

    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;
    public  final ImageView ivNote;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, List<DayItem> days)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        ivNote=itemView.findViewById(R.id.iv_isNote);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));

    }
}
