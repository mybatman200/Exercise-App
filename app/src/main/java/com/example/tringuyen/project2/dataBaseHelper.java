package com.example.tringuyen.project2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class dataBaseHelper extends SQLiteOpenHelper {
    final static String NAME = "todo";
    final static String WORKOUT_NAME = "workout_name";
    final static String WORKOUT_WEIGHT = "workout_weight";
    final static String WORKOUT_REPS = "workout_reps";
    final static String WORKOUT_SETS = "workout_sets";
    final static String WORKOUT_NOTES = "workout_notes";
    final private static String CREATE_CMD =
            "CREATE TABLE "+NAME+" (" + MainActivity._ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + MainActivity.WORKOUT_NAME + " TEXT NOT NULL," +
                    MainActivity.WORKOUT_WEIGHT + " TEXT NOT NULL," + MainActivity.WORKOUT_REPS + " TEXT NOT NULL,"+
                    MainActivity.WORKOUT_SETS + " TEXT NOT NULL," + MainActivity.WORKOUT_NOTES + " TEXT NOT NULL)";

    final private static Integer VERSION = 1;
    final private Context context;

    public dataBaseHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
        ContentValues values = new ContentValues();

        values.put(WORKOUT_NAME, "Push up");
        values.put(WORKOUT_WEIGHT, "100");
        values.put(WORKOUT_REPS, "15");
        values.put(WORKOUT_SETS, "3");
        values.put(WORKOUT_NOTES, "3 4 5 6");
        db.insert(NAME,null,values);

        ContentValues values1 = new ContentValues();

        values1.put(WORKOUT_NAME, "Sit up");
        values1.put(WORKOUT_WEIGHT, "1000");
        values1.put(WORKOUT_REPS, "150");
        values1.put(WORKOUT_SETS, "30");
        values1.put(WORKOUT_NOTES, "3 4 5 6 7");
        db.insert(NAME,null,values1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    void deleteDatabase ( ) {
        context.deleteDatabase(NAME);
    }
}


