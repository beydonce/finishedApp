package com.example.fitnessapp.PrivateWorkout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public abstract class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fitfit.db";
    public static final int DATABASE_VERSION = 1;

    protected SQLiteDatabase database;

    private final Context context;

    public DatabaseOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public void open() {
        //Database is already opened, no need to open it again
        if (database != null) return;

        database = this.getReadableDatabase();
        Log.i("data", "Database connection open");
    }

    @Override
    public synchronized void close() {
        super.close();

        //Database is already closed, no need to close it again
        if (database == null) return;

        database.close();
        database = null;
    }

    public boolean isOpen() {
        if (database == null) return false;
        return database.isOpen();
    }
}
