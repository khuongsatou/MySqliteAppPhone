package com.example.appphonever4;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.SinhVienHolder> {
    private Context context;
    private List<SinhVienEntry> sinhVienEntries;

    public SinhVienAdapter(Context context, List<SinhVienEntry> sinhVienEntries) {
        this.context = context;
        this.sinhVienEntries = sinhVienEntries;
    }

    @NonNull
    @Override
    public SinhVienAdapter.SinhVienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_sinh_vien,parent,false);
        return new SinhVienHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienAdapter.SinhVienHolder holder, int position) {
        SinhVienEntry sv = sinhVienEntries.get(position);
        holder.tvMa.setText(sv.getMaSV()+"");
        holder.tvTen.setText(sv.getHoTen()+"");
        holder.tvDiem.setText(sv.getDiem()+"");
    }

    @Override
    public int getItemCount() {
        return sinhVienEntries.size();
    }

    public class SinhVienHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTen,tvMa,tvDiem;
        private CardView cvItem;
        public SinhVienHolder(@NonNull View itemView) {
            super(itemView);

            tvMa = itemView.findViewById(R.id.tvMaSV);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvDiem = itemView.findViewById(R.id.tvDiem);
            cvItem = itemView.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:"+sinhVienEntries.get(getLayoutPosition()).getMaSV()));
            if (i.resolveActivity(context.getPackageManager()) !=null){
                context.startActivity(i);
            }
        }
    }
}
