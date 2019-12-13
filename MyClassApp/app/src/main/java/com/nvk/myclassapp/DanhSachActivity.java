package com.nvk.myclassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DanhSachActivity extends AppCompatActivity {
    private SinhVienAdapter adapter;
    private RecyclerView rcvDanhSach;
    private List<SinhVienEntry> entryList = new ArrayList<>();
    private SinhVienEntry entry = new SinhVienEntry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);

        initView();
        initAdapter();
    }

    private void initAdapter() {

        entryList.addAll(entry.selectALL());
        adapter = new SinhVienAdapter(entryList,this);
        rcvDanhSach.setLayoutManager(new LinearLayoutManager(this));
        rcvDanhSach.setAdapter(adapter);
    }

    private void initView() {
        rcvDanhSach = findViewById(R.id.rcvDanhSach);

    }
}
