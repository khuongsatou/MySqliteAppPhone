package com.example.danhbaver3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DanhBaAdapter extends RecyclerView.Adapter<DanhBaAdapter.DanhBaHolder> {
    private Context context;
    private List<DanhBa> danhBas;

    public DanhBaAdapter(Context context, List<DanhBa> danhBas) {
        this.context = context;
        this.danhBas = danhBas;
    }

    @NonNull
    @Override
    public DanhBaAdapter.DanhBaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_item_danh_ba,parent,false);
        return new DanhBaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhBaAdapter.DanhBaHolder holder, int position) {
        DanhBa danhBa = danhBas.get(position);
        holder.tvTen.setText(danhBa.getTen());
        holder.tvSDT.setText(danhBa.getSdt());
    }

    @Override
    public int getItemCount() {
        return danhBas.size();
    }

    public class DanhBaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTen,tvSDT;
        private CardView cvItem;
        public DanhBaHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvSDT = itemView.findViewById(R.id.tvSDT);
            cvItem = itemView.findViewById(R.id.cvItem);
            cvItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_DIAL);
            i.setData(Uri.parse("tel:"+danhBas.get(getLayoutPosition()).getSdt()));
            if (i.resolveActivity(context.getPackageManager())!=null){
                context.startActivity(i);
            }
        }
    }
}
