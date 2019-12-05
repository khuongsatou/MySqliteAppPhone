package com.example.appphonever4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SinhVienEntry sv;
    private EditText edtMaSV,edtTen,edtDiem;
    private Button btnXoa,btnCapNhat,btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        edtMaSV = findViewById(R.id.edtMaSV);
        edtTen = findViewById(R.id.edtTen);
        edtDiem = findViewById(R.id.edtDiem);

        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnThem = findViewById(R.id.btnThem);
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
    }


    public void Huy(View view) {
        edtMaSV.setText("");
        edtTen.setText("");
        edtDiem.setText("");

        edtMaSV.requestFocus();
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnThem.setEnabled(true);

    }

    private boolean CheckEmptyMaSV(){
        if (edtMaSV.getText().toString().isEmpty()){
            Toast.makeText(this,"Bạn Chưa Nhập",Toast.LENGTH_SHORT).show();
            edtMaSV.requestFocus();
            return true;
        }
        return false;
    }

    private boolean CheckEmptyTenSV(){
        if (edtTen.getText().toString().isEmpty()){
            Toast.makeText(this,"Bạn Chưa Nhập",Toast.LENGTH_SHORT).show();
            edtTen.requestFocus();
            return true;
        }
        return false;
    }
    private boolean CheckEmptyDiem(){
        if (edtDiem.getText().toString().isEmpty()){
            Toast.makeText(this,"Bạn Chưa Nhập",Toast.LENGTH_SHORT).show();
            edtDiem.requestFocus();
            return true;
        }
        return false;
    }

    private void Success(String tb){
        Toast.makeText(this,tb+" Thành Công",Toast.LENGTH_SHORT).show();
    }
    private void Exist(String tb){
        Toast.makeText(this,tb+" Tồn Tại",Toast.LENGTH_SHORT).show();
    }
    private void Fail(String tb){
        Toast.makeText(this,tb+" Thất Bại",Toast.LENGTH_SHORT).show();
    }


    public void Them(View view) {
        if (CheckEmptyMaSV() || CheckEmptyTenSV() || CheckEmptyDiem()){
            return;
        }
        sv = new SinhVienEntry(this,edtMaSV.getText().toString(),edtTen.getText().toString(),edtDiem.getText().toString());
        if (!sv.tonTai(edtMaSV.getText().toString())){
            if (sv.them()){
                Success("Thêm");
            }else{
                Fail("Thêm");
            }
        }else{
            Exist("Mã SV ");
        }

    }



    public void DanhSach(View view) {
        startActivity(new Intent(this,DSSinhVien.class));
    }

    public void Tim(View view) {
        if (CheckEmptyMaSV()){
            return;
        }
        sv = new SinhVienEntry(this);
        this.sv = sv.tim(edtMaSV.getText().toString());
        boolean exits = (this.sv !=null);

        btnCapNhat.setEnabled(exits);
        btnXoa.setEnabled(exits);
        btnThem.setEnabled(!exits);
        if (exits){
           edtMaSV.setText(this.sv.getMaSV());
           edtTen.setText(this.sv.getHoTen());
           edtDiem.setText(this.sv.getDiem());
        }else{
            Exist("Mã SV Không ");
        }
    }

    public void capNhat(View view) {
        if (CheckEmptyMaSV() || CheckEmptyTenSV() || CheckEmptyDiem()){
            return;
        }
        sv = new SinhVienEntry(this,sv.getId(),edtMaSV.getText().toString(),edtTen.getText().toString(),edtDiem.getText().toString());
        if (sv.capNhat()){
            Huy(view);
            Success("Cập Nhật ");
        }else{
            Fail("Cập Nhật");
        }

    }

    public void Xoa(View view) {
        if (sv.xoa()){
            Huy(view);
            Success("Xóa ");
        }else{
            Fail("Xóa");
        }

    }
}
