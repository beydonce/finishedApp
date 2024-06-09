package com.example.fitnessapp.PrivateWorkout;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fitnessapp.PrivateWorkout.Sets.Set;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class WorkoutOpenHelper extends DatabaseOpenHelper {

    public static final String TABLE_NAME = "Workouts";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SETS = "sets";
    public static final String COLUMN_TOTAL_TIME = "total_time";
    public static final String COLUMN_CREATOR_ID = "creator_id";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_SETS, COLUMN_TOTAL_TIME, COLUMN_CREATOR_ID};

    private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " VARCHAR, "
            + COLUMN_SETS + " VARCHAR, "
            + COLUMN_TOTAL_TIME + " INTEGER, "
            + COLUMN_CREATOR_ID + " INTEGER "
            + ");";

    public WorkoutOpenHelper(Context context) {//פעולה בונה
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Workout insert(Workout workout) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, workout.getWorkoutName());
        values.put(COLUMN_SETS, encodeSetList(workout.getSets()));
        values.put(COLUMN_TOTAL_TIME, workout.getTotalTime());
        values.put(COLUMN_CREATOR_ID, workout.getCreatorId());

        long insertID = database.insert(TABLE_NAME, null, values);
        workout.setId(insertID);
        return workout;
    }

    @SuppressLint("Range")
    private Workout getWorkoutFromCursor(Cursor cursor) {
        return new Workout(
                cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                decodeSetList(cursor.getString(cursor.getColumnIndex(COLUMN_SETS))),
                cursor.getInt(cursor.getColumnIndex(COLUMN_CREATOR_ID))
        );
    }

    public ArrayList<Workout> getAllWorkouts() {
        ArrayList<Workout> list = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Workout workout = getWorkoutFromCursor(cursor);
                list.add(workout);
            }
        }

        cursor.close();
        return list;
    }

    private static String encodeSetList(List<Set> sets) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(sets);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    private static List<Set> decodeSetList(String s) {
        byte[] blob = Base64.getDecoder().decode(s);
        try {
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(blob));
            List<Set> sets = (List<Set>) ois.readObject();
            ois.close();
            return sets;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}
