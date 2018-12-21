package com.example.agastyaharta.adminrestoran.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.agastyaharta.adminrestoran.R;

import com.example.agastyaharta.adminrestoran.model.Hidangan;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;

public class RecyclerViewHidanganHorizontal extends RecyclerView.Adapter<RecyclerViewHidanganHorizontal.ViewHolder> {
    private Context context;
    private List<Hidangan> hidangans;

    public RecyclerViewHidanganHorizontal(Context context, List<Hidangan> hidangans) {
        this.context = context;
        this.hidangans = hidangans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_hidangan_horizontal, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Hidangan hidangan = hidangans.get(position);
        holder.textViewNama.setText(hidangan.getNama_hidangan());
        holder.textViewHarga.setText(hidangan.getHarga_hidangan());


        holder.cvMenuKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditMenuActivity.class);
                intent.putExtra("nama_hidangan", hidangans.get(position).getNama_hidangan());
                intent.putExtra("deskripsi_hidangan", hidangans.get(position).getDeskripsi_hidangan());
                intent.putExtra("kateogori_hidangan", hidangans.get(position).getKategori_hidangan());
                intent.putExtra("harga_hidangan", hidangans.get(position).getHarga_hidangan());


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return hidangans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nama_hidangan) TextView textViewNama;
        @BindView(R.id.tv_harga_hidangan) TextView textViewHarga;
        @BindView(R.id.iv_fotohidangan) ImageView ivFotoHidangan;
        @BindView(R.id.cv_menu_kategori) CardView cvMenuKategori;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
