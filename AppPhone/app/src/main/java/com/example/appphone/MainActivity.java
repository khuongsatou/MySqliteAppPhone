package com.example.appphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtTen,edtSoDienThoai;
    DanhBaDBSqlHeper heper;
    private DanhBaEntry entry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen=  findViewById(R.id.edtTen);
        edtSoDienThoai= findViewById(R.id.edtSoDienThoai);
        heper = new DanhBaDBSqlHeper(this);
    }

    public void clearForm(View view) {
        edtSoDienThoai.setText("");
        edtTen.setText("");

        edtTen.requestFocus();
    }

    public void Them(View view) {
        String ten = edtTen.getText().toString().trim();
        String sdt = edtSoDienThoai.getText().toString().trim();
        DanhBaEntry entry = new DanhBaEntry();
        entry.setTen(ten);
        entry.setSdt(sdt);
        long id = heper.themDanhBa(entry);
        if (id > 0){
            Toast.makeText(getApplicationContext(),"Bạn Đã Thêm Thành công và ID: "+id,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Bạn Đã Thêm Thất bại",Toast.LENGTH_LONG).show();
        }
    }

    public void tim_theo_SDT(View view) {


            entry = heper.timTheoSDT(edtSoDienThoai.getText().toString());

            boolean isExist = (entry != null);
            if (isExist){
                Toast.makeText(getApplicationContext(),"Tìm thấy số điện thoại: ",Toast.LENGTH_LONG).show();
                edtTen.setText(entry.getTen());
            }else{
                Toast.makeText(getApplicationContext(),"Không tìm thấy số điện thoại",Toast.LENGTH_LONG).show();
            }

    }

    public void xoa_danhBa(View view) {
        int count = heper.xoaDanhBa(entry);

        if (count > 0){
            Toast.makeText(getApplicationContext(),"Đã xóa số điện thoại: ",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext()," số điện thoại chưa được xóa",Toast.LENGTH_LONG).show();
        }
    }
}
