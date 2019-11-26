package com.example.appphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.appphone.DanhBaEntry.COLUMN_ID;
import static com.example.appphone.DanhBaEntry.COLUMN_SDT;
import static com.example.appphone.DanhBaEntry.COLUMN_TEN;
import static com.example.appphone.DanhBaEntry.TABLE_NAME;

public class DanhBaDBSqlHeper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="danhba";



    public DanhBaDBSqlHeper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DanhBaEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DanhBaEntry.DROP_TABLE);

        onCreate(db);
    }

}
