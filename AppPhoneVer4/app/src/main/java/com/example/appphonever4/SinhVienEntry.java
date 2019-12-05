package com.example.appphonever4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SinhVienEntry {
    public static final String TABLE_NAME = "danhba";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MASV = "masv";
    public static final String COLUMN_HOTEN = "hoten";
    public static final String COLUMN_DIEM = "diem";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +" ( "+
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_MASV + " TEXT,"+
            COLUMN_HOTEN + " TEXT,"+
            COLUMN_DIEM + " TEXT )";
    public static final String DROP_TABLE = "DROP IF EXITS TABLE " + TABLE_NAME;


    private Context context;
    private SinhVienHelper helper;
    private int id;
    private String maSV;
    private String hoTen;
    private String diem;

    public SinhVienEntry(Context context) {
        this.context = context;
        this.helper = new SinhVienHelper(context);
        this.id = 0;
        this.maSV = "";
        this.hoTen = "UnKnown";
        this.diem = "";

    }
    public SinhVienEntry(Context context, String maSV, String hoTen, String diem) {
        this(context);
        this.hoTen = hoTen;
        this.maSV = maSV;
        this.diem = diem;
    }

    public SinhVienEntry(Context context, int id, String maSV, String hoTen, String diem) {
        this(context,maSV,hoTen,diem);
        this.id = id;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public SinhVienHelper getHelper() {
        return helper;
    }

    public void setHelper(SinhVienHelper helper) {
        this.helper = helper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
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

    public boolean them(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MASV,maSV);
        values.put(COLUMN_HOTEN,hoTen);
        values.put(COLUMN_DIEM,diem);
        return db.insert(TABLE_NAME,null,values) > 0;
    }
    public boolean capNhat(){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MASV,maSV);
        values.put(COLUMN_HOTEN,hoTen);
        values.put(COLUMN_DIEM,diem);
        return db.update(TABLE_NAME,values,COLUMN_ID +"=?",new String[]{this.id+""}) > 0;
    }

    public boolean xoa(){
        SQLiteDatabase db = helper.getWritableDatabase();
        return db.delete(TABLE_NAME,COLUMN_ID +"=?",new String[]{this.id+""}) > 0;
    }

    public SinhVienEntry tim(String masv){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,COLUMN_MASV+"=?",new String[]{masv+""},null,null,COLUMN_HOTEN+" ASC");
        SinhVienEntry entry = null;
        if (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String hoten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOTEN));
            String diem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIEM));
            entry = new SinhVienEntry(context,id,masv,hoten,diem);
        }
        cursor.close();
        return entry;
    }

    public boolean tonTai(String masv){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,COLUMN_MASV+"=?",new String[]{maSV+""},null,null,COLUMN_HOTEN+" ASC");
        int result = cursor.getCount();
        cursor.close();
        return result > 0;
    }

    public List<SinhVienEntry> getList(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,COLUMN_HOTEN+" ASC");
        List<SinhVienEntry> sinhVienEntries = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String masv = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MASV));
            String hoten = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HOTEN));
            String diem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIEM));
            sinhVienEntries.add(new SinhVienEntry(context,id,masv,hoten,diem));
        }
        cursor.close();
        return sinhVienEntries;
    }
}
