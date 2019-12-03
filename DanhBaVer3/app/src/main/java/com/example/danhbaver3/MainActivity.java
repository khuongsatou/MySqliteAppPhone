package com.example.danhbaver3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnThemMoi, btnHuy,btnXoa,btnCapNhat;
    private EditText edtTen, edtSDT;

    DanhBa danhBa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        innitView();
    }

    private void innitView() {
        btnThemMoi = findViewById(R.id.btnThemMoi);
        btnHuy = findViewById(R.id.btnHuy);
        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);

        edtTen = findViewById(R.id.edtTen);
        edtSDT = findViewById(R.id.edtSDT);

        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
    }


    public void clearForm(View view) {
        edtTen.setText("");
        edtSDT.setText("");
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        edtTen.requestFocus();
    }

    private boolean checkEmptySDT() {
        if (edtSDT.getText().toString().isEmpty()) {
            edtSDT.requestFocus();
            Toast.makeText(this,"Bạn Chưa Nhập SDT",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    private boolean checkEmptyTEN() {
        if (edtTen.getText().toString().isEmpty()) {
            edtTen.requestFocus();
            Toast.makeText(this,"Bạn Chưa Nhập TÊN",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }


    public void themMoi(View view) {
        if (checkEmptyTEN()){
            return;
        }
        if (checkEmptySDT()){
            return;
        }
        danhBa = new DanhBa(this, edtTen.getText().toString(), edtSDT.getText().toString());
        if (danhBa.them()){
            Toast.makeText(this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
        }
        clearForm(view);
    }

    public void capNhat(View view) {
        if (checkEmptyTEN()){
            return;
        }
        if (checkEmptySDT()){
            return;
        }
        danhBa.setTen(edtTen.getText().toString());
        danhBa.setSdt(edtSDT.getText().toString());
        if (danhBa.capNhat()){
            Toast.makeText(this, "Cập Nhật Thành Công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Cập Nhật Thất Bại", Toast.LENGTH_SHORT).show();
        }
        clearForm(view);
    }


    public void tim(View view) {
        if (checkEmptySDT()){
            Toast.makeText(this, "Bạn Chưa Nhập", Toast.LENGTH_SHORT).show();
            return;
        }
        danhBa = new DanhBa(this);
        danhBa = danhBa.tim(edtSDT.getText().toString());
        boolean exist = (danhBa !=null);
        btnCapNhat.setEnabled(exist);
        btnXoa.setEnabled(exist);
        if (exist){
            edtTen.setText(danhBa.getTen());
            Toast.makeText(this, "Tìm Thấy", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Không tìm Thấy", Toast.LENGTH_SHORT).show();
        }

    }

    public void xoa(View view) {
        if (danhBa.xoa()){
            Toast.makeText(this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();
        }
        clearForm(view);
    }

    public void danhSach(View view) {
        startActivity(new Intent(this,DanhBaActivity.class));
    }
}
