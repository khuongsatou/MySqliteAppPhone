package com.example.danhbaver3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DanhBaActivity extends AppCompatActivity {
    private RecyclerView rcvDanhBa;
    private DanhBaAdapter adapter;
    private List<DanhBa> danhBas= new ArrayList<>();
    private DanhBa danhBa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        innitView();
        innitData();
    }

    private void innitData() {
        danhBa = new DanhBa(this);
        danhBas = danhBa.getList();
        rcvDanhBa.setLayoutManager(new LinearLayoutManager(this));
        adapter =new DanhBaAdapter(this,danhBas);
        rcvDanhBa.setAdapter(adapter);
    }

    private void innitView() {
        rcvDanhBa= findViewById(R.id.rcvDanhBa);
    }
}
