package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitTrackerContract;
import com.example.android.habittracker.data.HabitTrackerHelper;

public class MainActivity extends AppCompatActivity {

    HabitTrackerHelper mHabitTrackerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.insert_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        mHabitTrackerHelper = new HabitTrackerHelper(this); // So that table is created before SELECT
        displayHabitInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayHabitInfo(); //To display the information each time activity starts or recalled.
    }

    public void displayHabitInfo() {
        SQLiteDatabase db = mHabitTrackerHelper.getReadableDatabase();
        String[] projection = {
                HabitTrackerContract.HabitEntry._ID,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME,
                HabitTrackerContract.HabitEntry.COLUMN_HABIT_OCCURENCE
        };

        Cursor cursor = db.query(HabitTrackerContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        try {
            TextView textView = (TextView) findViewById(R.id.text_view);
            textView.setText("Habit Tracker details.\n");
            textView.append("**********************************\n");
            textView.append(
                    HabitTrackerContract.HabitEntry._ID + "\t"
                            + HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME + "\t"
                            + HabitTrackerContract.HabitEntry.COLUMN_HABIT_OCCURENCE + "\n"
            );

            int idColIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry._ID);
            int nameColIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME);
            int occurenceColIndex = cursor.getColumnIndex(HabitTrackerContract.HabitEntry.COLUMN_HABIT_OCCURENCE);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColIndex);
                String name = cursor.getString(nameColIndex);
                int occurence = cursor.getInt(occurenceColIndex);
                textView.append(id + "\t" + name + "\t" + occurence + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }

    public void insertData() {
        SQLiteDatabase db = mHabitTrackerHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_NAME, "Running");
        contentValues.put(HabitTrackerContract.HabitEntry.COLUMN_HABIT_OCCURENCE, 2);

        long returnValue = db.insert(HabitTrackerContract.HabitEntry.TABLE_NAME, null, contentValues);
        if (returnValue == -1)
            Toast.makeText(this, "Unable to insert into the Db.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Inserted data for habit!", Toast.LENGTH_SHORT).show();
    }
}
