package com.nvk.myclassapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienViewHolder> {
    private List<SinhVienEntry> entrys;
    private Context context;

    public SinhVienAdapter(List<SinhVienEntry> entrys, Context context) {
        this.entrys = entrys;
        this.context = context;
    }

    @NonNull
    @Override
    public SinhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_sinh_vien,parent,false);
        return new SinhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienViewHolder holder, int position) {
        SinhVienEntry entry = entrys.get(position);
        holder.tvMSSV.setText(entry.getMssv());
        holder.tvHoTen.setText(entry.getHoTen());
        holder.tvDiem.setText(entry.getDiem());
    }

    @Override
    public int getItemCount() {
        return entrys.size();
    }
}
