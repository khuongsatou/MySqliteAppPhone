package com.example.appphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtTen, edtSoDienThoai;
    private DanhBaEntry entry = null;
    private Button btnXoa,btnCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTen = findViewById(R.id.edtTen);
        edtSoDienThoai = findViewById(R.id.edtSoDienThoai);
        btnXoa   = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        this.entry = new DanhBaEntry(this);
    }

    public void clearForm(View view) {
        edtSoDienThoai.setText("");
        edtTen.setText("");
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
        edtTen.requestFocus();
    }

    public void Them(View view) {
        String ten = edtTen.getText().toString().trim();
        String sdt = edtSoDienThoai.getText().toString().trim();
        if (ten.equals("") || sdt.equals("")) {
            clearForm(view);
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập", Toast.LENGTH_LONG).show();
            return;
        }
        entry.setTen(ten);
        entry.setSdt(sdt);
        long id = entry.themDanhBa(entry);
        if (id > 0) {
            clearForm(view);
            Toast.makeText(getApplicationContext(), "Bạn Đã Thêm Thành công và ID: " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bạn Đã Thêm Thất bại", Toast.LENGTH_LONG).show();
        }
    }

    public void tim_theo_SDT(View view) {
        btnCapNhat.setEnabled(true);
        btnXoa.setEnabled(true);
        String soDienThoai = edtSoDienThoai.getText().toString();
        if (soDienThoai.equals("")) {
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập", Toast.LENGTH_LONG).show();
            return;
        }
        entry = entry.timTheoSDT(soDienThoai);
        boolean isExist = (entry != null);
        if (isExist) {
            Toast.makeText(getApplicationContext(), "Tìm thấy số điện thoại: "+entry.getId(), Toast.LENGTH_LONG).show();
            edtTen.setText(entry.getTen());
            edtSoDienThoai.setText(entry.getSdt());
        } else {
            clearForm(view);
            Toast.makeText(getApplicationContext(), "Không tìm thấy số điện thoại", Toast.LENGTH_LONG).show();
        }

    }

    public void xoa_danhBa(View view) {
        if (edtSoDienThoai.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập", Toast.LENGTH_LONG).show();
            return;
        }
        int count = entry.xoaDanhBa(edtSoDienThoai.getText().toString());
        if (count > 0) {
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Đã xóa số điện thoại: ", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), " số điện thoại chưa được xóa", Toast.LENGTH_LONG).show();
        }
    }

    public void DanhBa(View view) {
        startActivity(new Intent(this, DanhBaActivity.class));
    }

    public void CapNhat(View view) {
        String ten = edtTen.getText().toString().trim();
        String sdt = edtSoDienThoai.getText().toString().trim();
        if (ten.isEmpty() || sdt.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Bạn Chưa Nhập", Toast.LENGTH_LONG).show();
            return;
        }
        DanhBaEntry entry = new DanhBaEntry(this);
        entry.setTen(ten);
        entry.setSdt(sdt);
        long id = this.entry.capNhatDanhBa(entry);
        if (id > 0) {
            Toast.makeText(getApplicationContext(), "Bạn Đã Thêm Thành công và ID: " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bạn Đã Thêm Thất bại", Toast.LENGTH_LONG).show();
        }
    }
}
