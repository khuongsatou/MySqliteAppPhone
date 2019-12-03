package com.example.danhbaver3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DanhBa {
    private static final String TABLE_NAME = "danh_ba";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN = "ten";
    private static final String COLUMN_SDT = "sdt";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME +"( "+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_TEN + " TEXT,"+
            COLUMN_SDT+ " TEXT )";
    public static final  String DROP_TABLE = "DROP TABLE IF EXITS "+TABLE_NAME;

    private Context context;
    private DanhBaHelper helper;
    private int id;
    private String ten;
    private String sdt;

    public DanhBa(Context context) {
        this.context = context;
        this.helper = new DanhBaHelper(context);
        this.id = 0;
        this.ten = "UnKnown";
        this.sdt = "";
    }
    public DanhBa(Context context, String ten, String sdt) {
        this(context);
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
    }

    public DanhBa(Context context, int id, String ten, String sdt) {
        this(context,ten,sdt);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public boolean them(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN,this.ten);
        values.put(COLUMN_SDT,this.sdt);
        return db.insert(TABLE_NAME,null,values) > 0;
    }

    public boolean xoa(){
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{this.id+""}) > 0;
    }

    public boolean capNhat(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEN,this.ten);
        values.put(COLUMN_SDT,this.sdt);
        return db.update(TABLE_NAME,values,COLUMN_ID+"=?",new String[]{this.id+""}) > 0;
    }

    public DanhBa tim(String sdt){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_TEN,
                COLUMN_SDT
        };
        String selection = COLUMN_SDT + "=?";
        String[] selectionArgs = {sdt};
        String sortOrder = COLUMN_TEN +" ASC";
        Cursor cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
        DanhBa danhBa = null;
        if (cursor.moveToNext()){
            int id  = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String ten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEN));
            danhBa = new DanhBa(context,id,ten,sdt);

        }
        cursor.close();
        return danhBa;
    }

    public List<DanhBa> getList(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] projection = {
                COLUMN_ID,
                COLUMN_TEN,
                COLUMN_SDT
        };
        String sortOrder = COLUMN_TEN +" ASC";
        Cursor cursor = db.query(TABLE_NAME,projection,null,null,null,null,sortOrder);
        List<DanhBa> danhBas = new ArrayList<>();

        while (cursor.moveToNext()){
            int id  = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String ten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEN));
            String sdt = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SDT));
            danhBas.add(new DanhBa(context,id,ten,sdt));
        }
        cursor.close();
        return danhBas;
    }
}
