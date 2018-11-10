package com.example.tringuyen.project2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.*;
import android.database.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Main2Activity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private dataBaseHelper dbHelper = null;
    final static String _ID = "_id";
    final static String WORKOUT_NAME = "workout_name";
    final static String WORKOUT_WEIGHT = "workout_weight";
    final static String WORKOUT_REPS = "workout_reps";
    final static String WORKOUT_SETS = "workout_sets";
    final static String WORKOUT_NOTES = "workout_notes";
    final static String[] all_columns = { _ID, WORKOUT_NAME, WORKOUT_WEIGHT, WORKOUT_REPS, WORKOUT_SETS, WORKOUT_NOTES};

    EditText nameEdit, weightEdit, repsEdit, setsEdit, notesEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHelper = new dataBaseHelper(this);

        nameEdit = findViewById(R.id.nameEditText);
        weightEdit = findViewById(R.id.WeightEditText);
        repsEdit = findViewById(R.id.RepsEditText);
        setsEdit = findViewById(R.id.SetsEditText);
        notesEdit = findViewById(R.id.NotesEditText);
    }

    public void addMoreExercise(View v){

        String name = nameEdit.getText().toString();
        String weight= weightEdit.getText().toString();
        String reps = repsEdit.getText().toString();
        String sets = setsEdit.getText().toString();
        String notes= notesEdit.getText().toString();

        db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORKOUT_NAME, name);
        values.put(WORKOUT_WEIGHT, weight);
        values.put(WORKOUT_REPS, reps);
        values.put(WORKOUT_SETS, sets);
        values.put(WORKOUT_NOTES, notes);

        db.insert(dbHelper.NAME, null, values);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
