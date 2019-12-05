package com.example.appphonever4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DSSinhVien extends AppCompatActivity {
    private RecyclerView rcvSinhVien;
    private SinhVienAdapter adapter;
    private List<SinhVienEntry> sinhVienEntryList;
    private SinhVienEntry entry = new SinhVienEntry(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssinh_vien);

        innitView();
        innitData();
    }

    private void innitData() {
        sinhVienEntryList = new ArrayList<>();
        sinhVienEntryList.addAll(entry.getList());
        adapter = new SinhVienAdapter(this,sinhVienEntryList);
        rcvSinhVien.setLayoutManager(new LinearLayoutManager(this));
        rcvSinhVien.setAdapter(adapter);
    }

    private void innitView() {
        rcvSinhVien = findViewById(R.id.rcvSinhVien);

    }
}
