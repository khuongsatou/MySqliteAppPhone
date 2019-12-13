package com.nvk.myclassapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SinhVienViewHolder extends RecyclerView.ViewHolder {
    public TextView tvMSSV,tvHoTen,tvDiem;
    public SinhVienViewHolder(@NonNull View itemView) {
        super(itemView);

        tvMSSV = itemView.findViewById(R.id.tvMSSV);
        tvHoTen = itemView.findViewById(R.id.tvHoTen);
        tvDiem = itemView.findViewById(R.id.tvDiem);
    }
}
