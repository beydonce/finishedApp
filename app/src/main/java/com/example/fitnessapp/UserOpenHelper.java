package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class UserOpenHelper extends SQLiteOpenHelper {
//    public static final String databaseName = "SignLog.db";
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, "SignLog.db", null, 1);
//    }
//    @Override
//    public void onCreate(SQLiteDatabase MyDatabase) {
//        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
//        MyDB.execSQL("drop Table if exists users");
//    }
//    public Boolean insertData(String email, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("email", email);
//        contentValues.put("password", password);
//        long result = MyDatabase.insert("users", null, contentValues);
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//    public Boolean checkEmail(String email){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
//        if(cursor.getCount() > 0) {
//            return true;
//        }else {
//            return false;
//        }
//    }
//    public Boolean checkEmailPassword(String email, String password){
//        SQLiteDatabase MyDatabase = this.getWritableDatabase();
//        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
//        if (cursor.getCount() > 0) {
//            return true;
//        }else {
//            return false;
//        }
//    }

    public static final String DATABASENAME = "DBusers";//שם מסד נתונים
    public static final String TABLE_USER = "tblUser";//שם הטבלה
    public static final int DATABASEVERSION = 1;

    public static final String COLUMN_ID = "ID";//מפתח ראשי - מספור אוטומטי
    //פירוט שדות(שמות עמודות)
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASS = "pass";

    //יצירת טבלה
    private static final String CREATE_TABLE_ALL_USERS = "CREATE TABLE IF NOT EXISTS " +
            TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " VARCHAR, " + COLUMN_PASS + " VARCHAR " + ");";

    //מערך כולל שמות השדות(עמודות)
    String[] allColumns = {COLUMN_ID, COLUMN_NAME, COLUMN_PASS};
    //אובייקט מובנה אחראי מסד נתונים Sql Lite
    SQLiteDatabase database;

    public UserOpenHelper(Context context) {//פעולה בונה
        super(context, DATABASENAME, null, DATABASEVERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//יצירת מסד נתונים
        db.execSQL(CREATE_TABLE_ALL_USERS);
        Log.i("data", "Table car created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open() {//פתיחת מסד נתונים
        database = this.getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public User createUser(User c) {//יצירת רשומה- מכונית בתוך DB
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());

        long insertId = database.insert(UserOpenHelper.TABLE_USER, null, values);
        Log.i("data", "UserCar " + insertId + "insert to database");
        return c;
    }

    @SuppressLint("Range")
    public ArrayList<User> getAllUsers() {// שליפת רשימת מכוניות מתוך טבלה

        ArrayList<User> l = new ArrayList<User>();//אתחול רשימה
        //שאילתת בחירה
        Cursor cursor = database.query(UserOpenHelper.TABLE_USER, allColumns, null, null, null, null, null);
        //אם מספר שורות גדול מ0
        if (cursor.getCount() > 0) {
            //כל עוד שניתן להתקדם בטבלה
            while (cursor.moveToNext()) {
                //העתקת נתונים למשתנים
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));

                String pass = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));

                //יצירת מכונית
                User c = new User(name, pass);
                c.setId(id);
                //עדכון מפתח ראשי

                //הוספת מכונית לרשימת מכוניות
                l.add(c);
            }
        }
        Log.i("data", "כל המכוניות");
        return l;
    }

    public Boolean checkEmailPassword(String email, String password) {
        Cursor cursor = database.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_USER + " WHERE " + COLUMN_NAME + " = ? AND " + COLUMN_PASS + " = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getByEmail(String email) {
        Cursor cursor = database.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_USER + " WHERE " + COLUMN_NAME + " = ? " , new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public User getById(int id) {
        Cursor cursor = database.query(UserOpenHelper.COLUMN_NAME, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int idc = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                if (idc == id) {

                    String email = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                    String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASS));
//                    byte[] image = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));

                    User c = new User(idc, password, email);
                    return c;
                }
            }
        }
        return null;

    }

    public long deleteCarByRow(long rowId) {
        return database.delete(UserOpenHelper.TABLE_USER, COLUMN_ID + "=" + rowId, null);
    }

    public long updateByRow(User c) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getEmail());
        values.put(COLUMN_PASS, c.getPassword());
//        values.put(COLUMN_IMAGE, c.getImage());
        // Toast.makeText(null,u.toString(),Toast.LENGTH_LONG).show();
        return database.update(TABLE_USER, values, COLUMN_ID + "=" + c.getId(), null);
    }
    public long deleteAll()

    {
        return database.delete(UserOpenHelper.TABLE_USER, null, null);
    }

}