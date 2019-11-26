package com.example.appphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DanhBaEntry {

    public static final String TABLE_NAME="danh_ba";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TEN="ten";
    public static final String COLUMN_SDT="sdt";
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME + " ( "
            +COLUMN_ID +" INTEGER PRIMARY KEY , "
            +COLUMN_TEN +" TEXT ,"
            +COLUMN_SDT + " TEXT )";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;





    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public DanhBaEntry(Context context) {
        this.helper = new DanhBaDBSqlHeper(context);
        this.id  =0;
        this.ten ="Unknown";
        this.sdt ="0306171362";
    }

    public DanhBaEntry(Context context, long id, String ten, String sdt) {
        this.context = context;
        this.helper = new DanhBaDBSqlHeper(context);
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
    }

    private long id;
    private String ten;
    private String sdt;
    private DanhBaDBSqlHeper helper;
    private Context context;



    public long themDanhBa(DanhBaEntry danhBaEntry){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID,danhBaEntry.getId());
        values.put(COLUMN_TEN,danhBaEntry.getTen());
        values.put(COLUMN_SDT,danhBaEntry.getSdt());

        long insertedID = sqLiteDatabase.insert(TABLE_NAME,null,values);
        return insertedID;

    }

    public DanhBaEntry timTheoSDT(String SDT)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
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

            obj = new DanhBaEntry(context,id,ten,so_dien_thoai);

        }
        cursor.close();
        return obj;
    }

    public int xoaDanhBa(DanhBaEntry obj)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String selcetion = COLUMN_SDT +"= ?";

        String[] selectionArg = {obj.getSdt()};

        int deleterow = db.delete(TABLE_NAME, selcetion, selectionArg);


        return deleterow;
    }

    public List<DanhBaEntry> getDanhBa()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection ={

                COLUMN_ID,
                COLUMN_TEN,
                COLUMN_SDT
        };
        //String selcetion = COLUMN_SDT +"= ?";

        String[] selectionArg = {};
        String sortOder = COLUMN_TEN + " ASC";
        Cursor cursor = db.query(TABLE_NAME, projection, null, selectionArg,null,null, sortOder);
        List<DanhBaEntry> danhBaEntries = new ArrayList<>();
        while (cursor.moveToNext())
        {
            int index =  cursor.getColumnIndexOrThrow(COLUMN_TEN);
            long id = cursor.getLong(index);
            String ten = cursor.getString(index);
            String so_dien_thoai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SDT));

            DanhBaEntry obj = new DanhBaEntry(context,id,ten,so_dien_thoai);
            danhBaEntries.add(obj);
        }
        cursor.close();
        return danhBaEntries;
    }
}
