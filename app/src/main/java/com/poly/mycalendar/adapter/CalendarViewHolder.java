package com.poly.mycalendar.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.utils.CustomView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public final TextView dayOfMonth;
    public final CardView cardView;
    private final CalendarAdapter.OnItemListener onItemListener;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        cardView = itemView.findViewById(R.id.carview);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
