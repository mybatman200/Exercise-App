package com.example.tringuyen.project2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    EditText weightEdit, repsEdit, notesEdit, setsEdit;
    TextView nameText;
    private dataBaseHelper dbHelper = null;
    private SQLiteDatabase db = null;

    final static String WORKOUT_NAME = "workout_name";
    final static String WORKOUT_WEIGHT = "workout_weight";
    final static String WORKOUT_REPS = "workout_reps";
    final static String WORKOUT_SETS = "workout_sets";
    final static String WORKOUT_NOTES = "workout_notes";
    final static String[] all_columns = { WORKOUT_NAME, WORKOUT_WEIGHT, WORKOUT_REPS, WORKOUT_SETS, WORKOUT_NOTES};

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        final String name = intent.getStringExtra(MainActivity.WORKOUT_NAME_INTENT_1);
        final String weight = intent.getStringExtra(MainActivity.WORKOUT_WEIGHT_INTENT_1);
        final String sets = intent.getStringExtra(MainActivity.WORKOUT_SETS_INTENT_1);
        final String reps = intent.getStringExtra(MainActivity.WORKOUT_REPS_INTENT_1);
        final String notes = intent.getStringExtra(MainActivity.WORKOUT_NOTES_INTENT_1);
        final String idString = intent.getStringExtra(MainActivity.WORKOUT_ID_INTENT_1);



        id = idString;

        dbHelper = new dataBaseHelper(this);


        nameText = findViewById(R.id.nameUpdate);
        nameText.setText(name);

        weightEdit = findViewById(R.id.weightUpdate);
        weightEdit.setText(weight);

        setsEdit = findViewById(R.id.setsUpdate);
        setsEdit.setText(sets);

        repsEdit = findViewById(R.id.repsUpdate);
        repsEdit.setText(reps);

        notesEdit = findViewById(R.id.notessUpdate);
        notesEdit.setText(notes);
    }

    public void updateValues(View v){

        String nameUpdate = nameText.getText().toString();
        String weightUpdate = weightEdit.getText().toString();
        String setsUpdate = setsEdit.getText().toString();
        String repsUpdate = repsEdit.getText().toString();
        String noteUpdate = notesEdit.getText().toString();

//testing values
      /*  TextView weightText= findViewById(R.id.WeightText);
        TextView repsText = findViewById(R.id.RepsText);
        TextView setsText =findViewById(R.id.SetsText);
        TextView noteText = findViewById(R.id.NoteText);
        weightText.setText(weightUpdate);
        repsText.setText(repsUpdate);
        setsText.setText(setsUpdate);
        noteText.setText(noteUpdate);*/
///////

        db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(WORKOUT_NAME,nameUpdate);
        contentValues.put(WORKOUT_WEIGHT,weightUpdate);
        contentValues.put(WORKOUT_REPS, repsUpdate);
        contentValues.put(WORKOUT_SETS, setsUpdate);
        contentValues.put(WORKOUT_NOTES, noteUpdate);

        //String where =

        db.update(dbHelper.NAME,contentValues,"_id="+id,null);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}
