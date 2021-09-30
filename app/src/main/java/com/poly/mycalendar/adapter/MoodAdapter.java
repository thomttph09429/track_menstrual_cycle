package com.poly.mycalendar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.model.Mood;

import java.util.List;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MoodViewHolder> {
  private   List<Mood> moods;
    private   Context context;

    public MoodAdapter(List<Mood> moods, Context context) {
        this.moods = moods;
        this.context = context;
    }

    @NonNull
    @Override
    public MoodAdapter.MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new MoodAdapter.MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodAdapter.MoodViewHolder holder, int position) {
        Mood mood=moods.get(position);
        holder.tvTitle.setText(mood.getTitle());
        holder.ivPhoto.setImageResource(mood.getImage());
        holder.itemView.setOnClickListener(v -> {
            if (holder.ivMark.getVisibility()==View.VISIBLE){
                holder.ivMark.setVisibility(View.GONE);
            }else if (holder.ivMark.getVisibility()==View.GONE){
                holder.ivMark.setVisibility(View.VISIBLE);
            }

        });

    }

    @Override
    public int getItemCount() {
        return moods.size();
    }
    public  class MoodViewHolder  extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView ivPhoto, ivMark;


        public MoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPhoto = itemView.findViewById(R.id.iv_image);
            ivMark = itemView.findViewById(R.id.iv_mark);

        }
    }
}
