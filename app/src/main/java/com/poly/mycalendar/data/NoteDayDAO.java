package com.poly.mycalendar.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;

import com.poly.mycalendar.model.DataUser;
import com.poly.mycalendar.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDayDAO {
    public static final String TABLE_NAME = "note";


    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public static final String CREATE_TABLE_NOTE = "create table note (id text primary key,dateNote text,typeOfNote text,positionItem integer)";


    public NoteDayDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }


    public long insert(Note note) {
        ContentValues values = new ContentValues();
        values.put("id", note.getId());
        values.put("dateNote", note.getDateNote());
        values.put("typeOfNote", note.getTypeOfNote());
        values.put("positionItem", note.getPositionItem());
        long result = db.insert(TABLE_NAME, null, values);
        return result;

    }


    public List<Note> getAllNoteInDay(String dateNote) {
        List<Note> noteList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id",
                        "dateNote", "typeOfNote", "positionItem"}, "dateNote" + "=?",
                new String[]{String.valueOf(dateNote)}, null, null, null, null);

        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Note note = new Note(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            noteList.add(note);
            cursor.moveToNext();
        }
        cursor.close();
        return noteList;
    }

    public long deleteNote(String id) {
        long result = db.delete(TABLE_NAME, "id" + "=?", new String[]{id});
        return result;

    }

    public boolean isNote(String dayNote) {
        String truyvan = "SELECT * FROM  " + TABLE_NAME + " WHERE  " + "dateNote" + " = '" + dayNote + "'";

        Cursor cursor = db.rawQuery(truyvan, null);
        if (cursor.getCount() != 0) {
            return true;

        } else {
            return false;
        }
    }
    public boolean isNoteId(String id) {
        String truyvan = "SELECT * FROM  " + TABLE_NAME + " WHERE  " + "id" + " = '" + id + "'";

        Cursor cursor = db.rawQuery(truyvan, null);
        if (cursor.getCount() != 0) {
            return true;

        } else {
            return false;
        }
    }
}
