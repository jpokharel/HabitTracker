package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by jiwanpokharel89 on 11/19/2017.
 */

public final class HabitTrackerContract {

    private HabitTrackerContract() {
    }

    public static final class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habitsTable";
        public static final String _ID = "_id";
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_OCCURENCE = "occurence";

    }
}
