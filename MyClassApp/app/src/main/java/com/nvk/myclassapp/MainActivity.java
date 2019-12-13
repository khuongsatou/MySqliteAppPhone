package com.nvk.myclassapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtMSSV,edtDiemTK,edtHoTen;
    private Button btnCapNhat,btnXoa;
    private SinhVienEntry sinhVienEntry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        edtMSSV = findViewById(R.id.edtMSSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtDiemTK = findViewById(R.id.edtDiemTK);
        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);

        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
    }


    public void Huy(View view) {
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);

        edtHoTen.setText("");
        edtMSSV.setText("");
        edtDiemTK.setText("");

        edtMSSV.requestFocus();
    }

    private boolean checkEmptyMSSV(){
        if (edtMSSV.getText().toString().trim().isEmpty()){
            edtMSSV.setError("Chưa Nhập");
            edtMSSV.requestFocus();
            return true;
        }
        return false;
    }
    private boolean checkEmptyHoTen(){
        if (edtHoTen.getText().toString().trim().isEmpty()){
            edtHoTen.setError("Chưa Nhập");
            edtHoTen.requestFocus();
            return true;
        }
        return false;
    }
    private boolean checkEmptyDiem(){
        if (edtDiemTK.getText().toString().trim().isEmpty()){
            edtDiemTK.setError("Chưa Nhập");
            edtDiemTK.requestFocus();
            return true;
        }
        return false;
    }

    private boolean checkExistMSSV(){
        if (sinhVienEntry.exist(edtMSSV.getText().toString().trim())){
            edtMSSV.setError("Mã Số Sinh Viên Trùng");
            edtMSSV.requestFocus();
            return true;
        }
        return false;
    }

    private void success(){
        Toast.makeText(this,"Thành Công",Toast.LENGTH_SHORT).show();
    }
    private void fail(){
        Toast.makeText(this,"Thất Bại",Toast.LENGTH_SHORT).show();
    }

    private void khongtimthay(){
        Toast.makeText(this,"Không tìm Thấy",Toast.LENGTH_SHORT).show();
    }
    public void Them(View view) {
        if (checkEmptyMSSV() || checkEmptyHoTen() || checkEmptyDiem()){
            return;
        }
        sinhVienEntry = new SinhVienEntry(this,edtMSSV.getText().toString(),edtHoTen.getText().toString(),edtDiemTK.getText().toString());
        if (checkExistMSSV()){
            return;
        }
        if (sinhVienEntry.Them()){
            success();
        }else{
            fail();
        }
    }

    public void CapNhat(View view) {
        if (checkEmptyMSSV() || checkEmptyHoTen() || checkEmptyDiem()){
            return;
        }
        sinhVienEntry.setMssv(edtMSSV.getText().toString());
        sinhVienEntry.setHoTen(edtHoTen.getText().toString());
        sinhVienEntry.setDiem(edtDiemTK.getText().toString());
        if (sinhVienEntry.CapNhat()){

            success();
        }else{
            fail();
        }
    }

    public void Tim(View view) {
        if (checkEmptyMSSV()){
            return;
        }
        sinhVienEntry = new SinhVienEntry(this);
        sinhVienEntry.setMssv(edtMSSV.getText().toString());
        if (sinhVienEntry.exist(edtMSSV.getText().toString())){
            sinhVienEntry = sinhVienEntry.select(edtMSSV.getText().toString().trim());
            boolean exist = (sinhVienEntry!=null);
            btnCapNhat.setEnabled(exist);
            btnXoa.setEnabled(exist);
            if (exist){
                edtHoTen.setText(sinhVienEntry.getHoTen());
                edtDiemTK.setText(sinhVienEntry.getDiem());
                success();
            }else{
                fail();
            }
        }else{
            khongtimthay();
        }

    }

    public void DanhSach(View view) {
        startActivity(new Intent(this,DanhSachActivity.class));
    }

    public void Xoa(View view) {
        if (sinhVienEntry.Xoa()){
            Huy(view);
            success();
        }else{
            fail();
        }
    }
}
