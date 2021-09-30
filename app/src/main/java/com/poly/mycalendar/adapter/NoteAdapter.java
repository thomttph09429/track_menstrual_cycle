package com.poly.mycalendar.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.poly.mycalendar.R;
import com.poly.mycalendar.activity.SymptomActivity;
import com.poly.mycalendar.data.DataUserDAO;
import com.poly.mycalendar.data.NoteDayDAO;
import com.poly.mycalendar.model.Mood;
import com.poly.mycalendar.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MoodViewHolder> {
    private List<Mood> moods;
    private Context context;
    private String typeOfNote;
    private NoteDayDAO noteDayDAO;
    private String dateNote;
    private String id;

    public NoteAdapter(List<Mood> moods, Context context, String typeOfNote, String dateNote) {
        this.moods = moods;
        this.context = context;
        this.typeOfNote = typeOfNote;
        this.dateNote = dateNote;
    }

    @NonNull
    @Override
    public NoteAdapter.MoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        return new NoteAdapter.MoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MoodViewHolder holder, int position) {
        noteDayDAO = new NoteDayDAO(context);
        Mood mood = moods.get(position);
        holder.tvTitle.setText(mood.getTitle());
        holder.ivPhoto.setImageResource(mood.getImage());
        Note note = new Note();



//
            holder.itemView.setOnClickListener(v -> {
                id = typeOfNote + dateNote + position + "";

                if (holder.ivMark.getVisibility() == View.VISIBLE) {
                    holder.ivMark.setVisibility(View.GONE);
                    long result = noteDayDAO.deleteNote(id);
                    if (result > 0) {
                        Log.e("da xoa", result + "");
                    } else {

                    }
                } else if (holder.ivMark.getVisibility() == View.GONE) {

                    holder.ivMark.setVisibility(View.VISIBLE);

                    note.setId(id);
                    note.setDateNote(dateNote);
                    note.setTypeOfNote(typeOfNote);
                    note.setPositionItem(position);
                    long result = noteDayDAO.insert(note);
                    if (result > 0) {
                        Log.e("da them", id + "");

                    } else {
                        Log.e("that bai ", result + "");

                    }
                }

            });


    }


    @Override
    public int getItemCount() {
        return moods.size();
    }

    public class MoodViewHolder extends RecyclerView.ViewHolder {
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
