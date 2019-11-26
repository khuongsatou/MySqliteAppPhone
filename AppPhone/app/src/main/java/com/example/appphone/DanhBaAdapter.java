package com.example.appphone;

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

public class DanhBaAdapter extends RecyclerView.Adapter<DanhBaAdapter.DanhBaHolder> {
    private Context context;
    private List<DanhBaEntry> danhBaEntrys;

    public DanhBaAdapter(Context context, List<DanhBaEntry> danhBaEntrys) {
        this.context = context;
        this.danhBaEntrys = danhBaEntrys;
    }

    @NonNull
    @Override
    public DanhBaAdapter.DanhBaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_item_danh_ba,parent,false);
        return new DanhBaHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhBaAdapter.DanhBaHolder holder, int position) {
        DanhBaEntry db = danhBaEntrys.get(position);
        holder.tvTen.setText(db.getTen());
        holder.tvSDT.setText(db.getSdt());
    }

    @Override
    public int getItemCount() {
        return danhBaEntrys.size();
    }

    public class DanhBaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTen,tvSDT;
        private CardView cvItemDanhBa;
        public DanhBaHolder(@NonNull View itemView) {
            super(itemView);

            tvTen = itemView.findViewById(R.id.tvTen);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            cvItemDanhBa = itemView.findViewById(R.id.cvItemDanhBa);
            cvItemDanhBa.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:"+danhBaEntrys.get(getLayoutPosition()).getSdt()));
            if (i.resolveActivity(context.getPackageManager()) !=null){
                context.startActivity(i);
            }
        }
    }
}
