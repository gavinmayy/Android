package com.warrantix.main.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBOHelper {

    public static long insert(Context context, String tableName, ContentValues contentValues) {
        SQLiteDatabase sqlDB = null;
        try {
            sqlDB = DatabaseHelper.instance().getWritableDatabase();

            long row_id = sqlDB.insert(tableName, null, contentValues);
            if (row_id > 0) {
                return row_id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

        }

        return -1;
    }

    public static long updateMe(Context context, String tableName, ContentValues contentValues, String update_id) {
        SQLiteDatabase sqlDB = null;
        try {
            sqlDB = DatabaseHelper.instance().getWritableDatabase();
            long row_id = sqlDB.update(tableName, contentValues,
                    Table.ID + "=" + update_id, null);
            if (row_id > 0) {
                return row_id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();


        } finally {


        }

        return -1;
    }
    public static long delete(Context context, String tableName, String delete_id) {
        SQLiteDatabase sqlDB = null;
        try {
            sqlDB = DatabaseHelper.instance().getWritableDatabase();
            long row_id = sqlDB.delete(tableName,
                    Table.ID + " = " + delete_id, null);
            if (row_id > 0) {
                return row_id;
            } else {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
        return -1;
    }
}