package com.example.appphonever4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.appphonever4.SinhVienEntry.CREATE_TABLE;
import static com.example.appphonever4.SinhVienEntry.DROP_TABLE;

public class SinhVienHelper  extends SQLiteOpenHelper {
    public SinhVienHelper(@Nullable Context context) {
        super(context, "SinhVienDB.db", null, 1);
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
