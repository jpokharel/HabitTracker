package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jiwanpokharel89 on 11/19/2017.
 */

public class HabitTrackerHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habittracker.db";
    private static final int DATABASE_VERSION = 1;

    public HabitTrackerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE " + HabitTrackerContract.HabitEntry.TABLE_NAME + " ("
                + HabitTrackerContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitTrackerContract.HabitEntry.COLUMN_HABIT_OCCURENCE + " INTEGER NOT NULL DEFAULT 1);";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This function can contain alter table or modify SQL statements, as needed.
    }
}
