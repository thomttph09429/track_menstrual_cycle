package com.poly.mycalendar.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
//    public boolean isNoteId(String id) {
//        String truyvan = "SELECT * FROM  " + TABLE_NAME + " WHERE  " + "id" + " = '" + id + "'";
//
//        Cursor cursor = db.rawQuery(truyvan, null);
//        if (cursor.getCount() != 0) {
//            return true;
//
//        } else {
//            return false;
//        }
//    }
    public boolean isMask(String title) {
        String truyvan = "SELECT * FROM  " + TABLE_NAME + " WHERE  " + "title" + " = '" + title + "'";

        Cursor cursor = db.rawQuery(truyvan, null);
        if (cursor.getCount() != 0) {
            return true;

        } else {
            return false;
        }
    }
//    public List<Note> sortName() {
//        List<Note> noteList = new ArrayList<>();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + "name" + " COLLATE NOCASE ASC;", null);
//
//        cursor.moveToFirst();
//
//        while (cursor.isAfterLast() == false) {
//            Note vehicle = new Note(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4));
//            noteList.add(vehicle);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return noteList;
//    }
}
