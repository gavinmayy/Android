package com.warrantix.main.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Warrantix.db";
    private static final int DATABASE_VERSION = 17;
    private static DatabaseHelper _instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void initialize(Context context) {
        _instance = new DatabaseHelper(context);
    }

    public static synchronized DatabaseHelper instance() {
        return _instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL(Table.Message.CREATE_TABLE);
            database.execSQL(String.format(Table.UPDATE_TRIGGER,
                    Table.Message.TABLE_NAME,
                    Table.Message.TABLE_NAME,
                    Table.Message.TABLE_NAME));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Table.Message.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
}