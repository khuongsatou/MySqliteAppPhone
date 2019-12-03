package com.example.danhbaver3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.danhbaver3.DanhBa.CREATE_TABLE;
import static com.example.danhbaver3.DanhBa.DROP_TABLE;

public class DanhBaHelper extends SQLiteOpenHelper {
    public DanhBaHelper(@Nullable Context context) {
        super(context, "danhbaDB.db", null, 1);
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
