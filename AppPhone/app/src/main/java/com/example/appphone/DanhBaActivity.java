package com.example.appphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DanhBaActivity extends AppCompatActivity {
    private DanhBaAdapter adapter;
    private RecyclerView rcvDanhBa;
    DanhBaEntry entry = new DanhBaEntry(this);
    private List<DanhBaEntry> danhBaEntrys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        initView();
        initAdapter();
        initData();
    }



    private void initData() {
        danhBaEntrys.clear();
        danhBaEntrys.addAll(entry.getDanhBa());
        adapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        adapter = new DanhBaAdapter(this,danhBaEntrys);
        rcvDanhBa.setLayoutManager(new LinearLayoutManager(this));
        rcvDanhBa.setAdapter(adapter);
    }

    private void initView() {
        rcvDanhBa = findViewById(R.id.rcvDanhBa);

    }


}
