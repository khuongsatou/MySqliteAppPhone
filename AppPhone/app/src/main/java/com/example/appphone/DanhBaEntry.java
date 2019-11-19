package com.example.appphone;

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

    public DanhBaEntry() {
        this.id  =0;
        this.ten ="Unknown";
        this.sdt ="0306171362";
    }

    public DanhBaEntry(long id, String ten, String sdt) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
    }

    private long id;
    private String ten;
    private String sdt;
}
