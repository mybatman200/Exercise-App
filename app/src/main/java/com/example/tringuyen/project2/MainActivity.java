package com.example.tringuyen.project2;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private dataBaseHelper dbHelper = null;
    SimpleCursorAdapter myAdapter;
    ListView mlist;
    EditText elem;
    Cursor mCursor;
    String longClickString;
    android.app.AlertDialog actions;
    final static String _ID = "_id";
    final static String WORKOUT_NAME = "workout_name";
    final static String WORKOUT_WEIGHT = "workout_weight";
    final static String WORKOUT_REPS = "workout_reps";
    final static String WORKOUT_SETS = "workout_sets";
    final static String WORKOUT_NOTES = "workout_notes";
    final static String[] all_columns = { _ID, WORKOUT_NAME, WORKOUT_WEIGHT, WORKOUT_REPS, WORKOUT_SETS, WORKOUT_NOTES};

    public final static String WORKOUT_ID_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_ID_1";
    public final static String WORKOUT_NAME_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_NAME_1";
    public final static String WORKOUT_WEIGHT_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_INTENT_1";
    public final static String WORKOUT_REPS_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_REPS_1";
    public final static String WORKOUT_SETS_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_SETS_1";
    public final static String WORKOUT_NOTES_INTENT_1 = "com.example.tringuyen.project2.WORKOUT_WEIGHT_NOTES_1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist = findViewById(R.id.listView);
        dbHelper = new dataBaseHelper(this);

        mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Touched " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                Object temp = myAdapter.getItem(position);
                String tempView = ((TextView) view).getText().toString();

                mCursor.moveToPosition(position);

                String tempPos = Integer.toString(position);
                //get values from database
                String workoutID = mCursor.getString(mCursor.getColumnIndex(_ID));
                String workoutWeight = mCursor.getString( mCursor.getColumnIndex(WORKOUT_WEIGHT));
                String workoutReps =  mCursor.getString( mCursor.getColumnIndex(WORKOUT_REPS));
                String workoutSets =  mCursor.getString( mCursor.getColumnIndex(WORKOUT_SETS));
                String workoutNotes =  mCursor.getString( mCursor.getColumnIndex(WORKOUT_NOTES));


                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra(WORKOUT_ID_INTENT_1,workoutID );
                intent.putExtra(WORKOUT_NAME_INTENT_1, ((TextView) view).getText());
                intent.putExtra(WORKOUT_WEIGHT_INTENT_1, workoutWeight);
                intent.putExtra(WORKOUT_REPS_INTENT_1, workoutReps);
                intent.putExtra(WORKOUT_SETS_INTENT_1, workoutSets);
                intent.putExtra(WORKOUT_NOTES_INTENT_1, workoutNotes);
                startActivity(intent);
            }
        });

        mlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "Long touched " + id, Toast.LENGTH_SHORT).show();

                String tempString = ((TextView) view).getText().toString();
                longClickString = Long.toString(id);

                actions.show();

                return true;
            }}
        );

        DialogInterface.OnClickListener actionListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //db.delete(dbHelper.NAME, "workout_name=?", new String[]{longClickString});
                        db.delete(dbHelper.NAME, "_id=?", new String[]{longClickString});
                        Toast.makeText(getApplicationContext(), "Delete" + longClickString, Toast.LENGTH_LONG).show();

                        db = dbHelper.getWritableDatabase();
                        mCursor = db.query(dbHelper.NAME, all_columns, null, null, null, null, null);
                        myAdapter.swapCursor(mCursor);
                        mlist.setAdapter(myAdapter);
                        break;

                    default:
                        break;
                }
            }
        };

        android.app.AlertDialog.Builder builder = new
                android.app.AlertDialog.Builder(this);
        builder.setTitle("Are you sure you want to delete this item?");
        String[] options = {"Delete"};
        builder.setItems(options, actionListener);
        builder.setNegativeButton("Cancel", null);
        actions = builder.create();

    }


    private class LoadNewLists extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected Cursor doInBackground(Void... params){
            db = dbHelper.getWritableDatabase();
            mCursor = db.query(dbHelper.NAME, all_columns, null, null, null, null,
                    null);
            return mCursor;
        }

        @Override
        protected void onPostExecute(Cursor result){
            super.onPostExecute(result);
            myAdapter = new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    mCursor,
                    new String[] {WORKOUT_NAME, WORKOUT_WEIGHT, WORKOUT_REPS, WORKOUT_SETS,  WORKOUT_NOTES },
                    new int[] { android.R.id.text1});

            mlist.setAdapter(myAdapter);
        }

    }

    public void onResume(){
        super.onResume();
        new LoadNewLists().execute();
        /*db = dbHelper.getWritableDatabase();
        mCursor = db.query(dbHelper.NAME, all_columns, null, null, null, null,
                null);
        myAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                mCursor,
                new String[] {WORKOUT_NAME, WORKOUT_WEIGHT, WORKOUT_REPS, WORKOUT_SETS,  WORKOUT_NOTES },
                new int[] { android.R.id.text1});

        mlist.setAdapter(myAdapter);*/

    }

    public void onPause() {
        super.onPause();
        if (db != null) db.close();
        mCursor.close();
    }

    public void addMoreExercise(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }




}
