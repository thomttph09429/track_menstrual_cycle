package com.poly.mycalendar.data;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class FavoriteDAO {
    public static final String TABLE_NAME = "dataUser";
    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public static final String CREATE_TABLE_VEHICLE = "create table favorite (photo text primary key  )";

    public FavoriteDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    public long delete(String id) {
        long result = db.delete(TABLE_NAME, "photo" + "=?", new String[]{id});
        return result;

    }



}
