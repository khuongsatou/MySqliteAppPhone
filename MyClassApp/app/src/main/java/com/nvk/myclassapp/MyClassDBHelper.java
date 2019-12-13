package com.nvk.myclassapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.nvk.myclassapp.SinhVienEntry.CREATE_TABLE;
import static com.nvk.myclassapp.SinhVienEntry.DROP_TABLE;

public class MyClassDBHelper extends SQLiteOpenHelper {
    public MyClassDBHelper(@Nullable Context context) {
        super(context, "“my_class.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
