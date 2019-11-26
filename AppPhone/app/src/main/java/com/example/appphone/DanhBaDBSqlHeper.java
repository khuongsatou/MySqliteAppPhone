package com.example.appphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.session.PlaybackState;

import androidx.annotation.Nullable;

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


    public long themDanhBa(DanhBaEntry danhBaEntry){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID,danhBaEntry.getId());
        values.put(COLUMN_TEN,danhBaEntry.getTen());
        values.put(COLUMN_SDT,danhBaEntry.getSdt());

        long insertedID = sqLiteDatabase.insert(TABLE_NAME,null,values);
        return insertedID;

    }

    public DanhBaEntry timTheoSDT(String SDT)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection ={

                COLUMN_ID,
                COLUMN_TEN,
                COLUMN_SDT
        };
        String selcetion = COLUMN_SDT +"= ?";

        String[] selectionArg = {SDT};
        String sortOder = COLUMN_TEN + " ASC";
        Cursor cursor = db.query(TABLE_NAME, projection, selcetion, selectionArg,null,null, sortOder);

        DanhBaEntry obj = null;
        while (cursor.moveToNext())
        {
            int index =  cursor.getColumnIndexOrThrow(COLUMN_TEN);
            long id = cursor.getLong(index);
            String ten = cursor.getString(index);
            String so_dien_thoai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SDT));

            obj = new DanhBaEntry(id,ten,so_dien_thoai);

        }
        cursor.close();
        return obj;
    }

    public int xoaDanhBa(DanhBaEntry obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String selcetion = COLUMN_SDT +"= ?";

        String[] selectionArg = {obj.getSdt()};

        int deleterow = db.delete(TABLE_NAME, selcetion, selectionArg);


        return deleterow;
    }
}
