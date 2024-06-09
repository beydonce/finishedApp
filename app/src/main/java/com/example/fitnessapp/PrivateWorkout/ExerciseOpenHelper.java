package com.example.fitnessapp.PrivateWorkout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fitnessapp.PrivateWorkout.Exercise;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseOpenHelper extends DatabaseOpenHelper {

    public static final String TABLE_NAME = "Exercises";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DIFFICULTY = "difficulty";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_DIFFICULTY};

    public static HashMap<String, Integer> exercises = new HashMap<>();

    public static final String CREATE_TABLE_STATEMENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " VARCHAR, " +
                    COLUMN_DIFFICULTY + " INTEGER " +
                    ");";

    public static final String REMOVE_EXERCISES_STATEMENT = "DELETE FROM " + TABLE_NAME + ";";
    public static final String RESET_AUTO_INCREMENT_STATEMENT = "DELETE from sqlite_sequence where name='" + TABLE_NAME + "';";

    public static String CREATE_INIT_EXERCISES =
            "INSERT INTO " + TABLE_NAME + "("
                    + COLUMN_NAME + ","
                    + COLUMN_DIFFICULTY + ") VALUES"
                    + " ('Push-Up', 2),"
                    + " ('Squat', 2),"
                    + " ('Pull-Up', 3),"
                    + " ('Rows', 2),"
                    + " ('Bicep-Curl', 2),"
                    + " ('Bench-Press', 4),"
                    + " ('Dips', 3),"
                    + " ('Lunges', 2),"
                    + " ('Leg-Press', 3),"
                    + " ('Sit-Up', 1),"
                    + " ('Leg-Raise', 3),"
                    + " ('Crunch', 2);";

    public ExerciseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getReadableDatabase();
    }

    public Exercise insert(Exercise exercise) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, exercise.getName());
        values.put(COLUMN_DIFFICULTY, exercise.getDifficulty());

        long insertID = database.insert(this.TABLE_NAME, null, values);
        Log.i("data", "Exercise " + insertID + "insert to database");
        return exercise;
    }

    @SuppressLint("Range")
    private Exercise getExerciseFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        int difficulty = cursor.getInt(cursor.getColumnIndex(COLUMN_DIFFICULTY));
        Exercise exercise = new Exercise(id, name, difficulty);

        return exercise;
    }

    public Exercise get(long id) {
        String where = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        Cursor cursor = database.query(TABLE_NAME, ALL_COLUMNS, where, whereArgs, null, null, null);

        if (!cursor.moveToFirst()) return null;
        Exercise exercise = getExerciseFromCursor(cursor);

        cursor.close();
        return exercise;
    }

    @SuppressLint("Range")
    public ArrayList<Exercise> getAllExercises() {
        ArrayList<Exercise> list = new ArrayList<>();
        Cursor cursor = database.query(this.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Exercise exercise = getExerciseFromCursor(cursor);
                list.add(exercise);
            }
        }

        cursor.close();
        Log.i("data", "All Exercises");
        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("ExerciseOpenHelper On Create!");
        sqLiteDatabase.execSQL(CREATE_TABLE_STATEMENT);
        Log.i("data", "Table \'" + TABLE_NAME + "\' Created!");
        exctodb(sqLiteDatabase);
        sqLiteDatabase.execSQL(CREATE_INIT_EXERCISES);
        Log.i("data", "Exercises Created!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void exctodb(SQLiteDatabase database) {


//        database.execSQL(CREATE_TABLE_STATEMENT);
        Log.i("data", "Table \'" + TABLE_NAME + "\' Created");
        database.execSQL(REMOVE_EXERCISES_STATEMENT);
        Log.i("data", "Removed All Exercises");
        database.execSQL(RESET_AUTO_INCREMENT_STATEMENT);
        Log.i("data", "Rest Auto Increment");
    }


}