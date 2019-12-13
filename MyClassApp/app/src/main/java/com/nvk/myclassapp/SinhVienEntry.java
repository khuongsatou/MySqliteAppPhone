package com.nvk.myclassapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienEntry {
    public static final String TABLE_NAME = "sv";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MSSV = "mssv";
    public static final String COLUMN_HOTEN = "ho_ten";
    public static final String COLUMN_DIEM = "diem_tk";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME + " ( "
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + COLUMN_MSSV + " TEXT ,"
            + COLUMN_HOTEN + " TEXT ,"
            + COLUMN_DIEM + " REAL )";

    public static final String DROP_TABLE = "DROP TABLE IF EXIST "+TABLE_NAME ;


    private Context context;
    private MyClassDBHelper helper;
    private int id;
    private String mssv;
    private String hoTen;
    private String diem;

    public SinhVienEntry(Context context) {
        helper = new MyClassDBHelper(context);
        this.context = context;
        this.id = 0;
        this.mssv = "";
        this.hoTen = "";
        this.diem = "";
    }


    public SinhVienEntry(Context context,String mssv, String hoTen, String diem) {
        this(context);
        this.mssv = mssv;
        this.hoTen = hoTen;
        this.diem = diem;
    }

    public SinhVienEntry(Context context,int id,String mssv, String hoTen, String diem) {
        this(context,mssv,hoTen,diem);
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MyClassDBHelper getHelper() {
        return helper;
    }

    public void setHelper(MyClassDBHelper helper) {
        this.helper = helper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public boolean Them(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MSSV,this.mssv);
        values.put(COLUMN_HOTEN,this.hoTen);
        values.put(COLUMN_DIEM,this.diem);
        return db.insert(TABLE_NAME,null,values) > 0;
    }

    public boolean CapNhat(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MSSV,this.mssv);
        values.put(COLUMN_HOTEN,this.hoTen);
        values.put(COLUMN_DIEM,this.diem);
        return db.update(TABLE_NAME,values,COLUMN_ID +" =?",new String[]{this.id+""}) > 0;
    }

    public boolean Xoa(){
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete(TABLE_NAME,COLUMN_ID +" =?",new String[]{this.id+""}) > 0;
    }

    public SinhVienEntry select(String mssv_v){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,COLUMN_MSSV+"=?",new String[]{mssv_v},null,null,null);
        SinhVienEntry sinhVienEntry =null;
        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String hoten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOTEN));
            String diem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIEM));
            sinhVienEntry = new SinhVienEntry(context,id,mssv_v,hoten,diem);
        }
        cursor.close();
        return sinhVienEntry;
    }
    public List<SinhVienEntry> selectALL(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        List<SinhVienEntry> sinhVienEntries = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String mssv = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MSSV));
            String hoten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOTEN));
            String diem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIEM));
            sinhVienEntries.add(new SinhVienEntry(context,id,mssv,hoten,diem));
        }
        cursor.close();
        return sinhVienEntries;
    }

    public boolean exist(String mssv_v){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,COLUMN_MSSV+"=?",new String[]{mssv_v},null,null,null);
        int count =cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
