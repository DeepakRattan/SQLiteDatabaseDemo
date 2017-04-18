package com.example.deepakrattan.sqlitedatabasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by deepak.rattan on 4/7/2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;

    //Define the Schema
    public static final String DATABASE_NAME = "emp.db";
    public static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "Employee";
    public static final String UID = "_id";
    public static final String NAME = "Name";
    public static final String PASSWORD = "Password";
    public static final String PHONE = "Phone";
    public static final String EMAIL = "Email";

    //QUERY
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT ," + PASSWORD + " TEXT," + PHONE + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + "";

    private static final String ALTER_EMPLOYEE_1 = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + EMAIL + " TEXT ";

    //Constructor
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Toast.makeText(context, "Constructor called", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(context, "onCreate() called", Toast.LENGTH_LONG).show();
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "onUpgrade() called", Toast.LENGTH_LONG).show();
       // db.execSQL(DROP_TABLE);
        if(oldVersion<3)
            db.execSQL(ALTER_EMPLOYEE_1);
       // onCreate(db);
    }
}
