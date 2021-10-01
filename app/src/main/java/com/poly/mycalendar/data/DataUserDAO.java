package com.poly.mycalendar.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.poly.mycalendar.model.DataUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.os.Build.ID;


public class DataUserDAO {
    public static final String TABLE_NAME = "dataUser";


    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public static final String CREATE_TABLE_USERDATA = "create table dataUser (id integer  primary key , cycle integer, period integer , dayStart text ,yearOfBirth integer)";


    public DataUserDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public long insert(DataUser dataUser) {
        ContentValues values = new ContentValues();
        values.put("cycle", dataUser.getCycle());
        values.put("period", dataUser.getPeriod());
        values.put("dayStart", dataUser.getDayStart());
        values.put("yearOfBirth", dataUser.getYearOfBirth());
        long result = db.insert(TABLE_NAME, null, values);
        return result;

    }

    public boolean checkAlreadyExist() {
        Cursor c = db.rawQuery("SELECT * FROM dataUser", null);
        if (c.getCount() > 0) {
            return true;
        } else
            return false;
    }

    public int getCycle() {
        String query = new String("select cycle from dataUser where ID = 1");
        Cursor result = db.rawQuery(query, null);

        int returnCycle = 0;

        if (result.moveToFirst()) {
            returnCycle = result.getInt(result.getColumnIndex("cycle"));
        }
        result.close();

        return returnCycle;
    }

    public boolean updatePeriod(int period) {
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + "period" + " =  " + period);
        return true;

    }
    public boolean updateCycle(int cycle) {
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + "cycle" + " =  " + cycle);
        return true;

    }
    public int getPeriod() {
        String query = new String("select period from dataUser where ID = 1");
        Cursor result = db.rawQuery(query, null);

        int returnPeriod = 0;

        if (result.moveToFirst()) {
            returnPeriod = result.getInt(result.getColumnIndex("period"));
        }
        result.close();

        return returnPeriod;
    }

    public String getDayStart() {
        String query = new String("select dayStart from dataUser where ID = 1");
        Cursor result = db.rawQuery(query, null);

        String returnsStart = "";

        if (result.moveToFirst()) {
            returnsStart = result.getString(result.getColumnIndex("dayStart"));
        }
        result.close();

        return returnsStart;
    }

    public int getYearOfBirth() {
        String query = new String("select yearOfBirth from dataUser where ID = 1");
        Cursor result = db.rawQuery(query, null);

        int returnsStart = 0;

        if (result.moveToFirst()) {
            returnsStart = result.getInt(result.getColumnIndex("yearOfBirth"));
        }
        result.close();

        return returnsStart;
    }
}
