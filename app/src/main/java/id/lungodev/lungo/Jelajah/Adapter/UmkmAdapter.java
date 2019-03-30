package id.lungodev.lungo.Jelajah.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import id.lungodev.lungo.Jelajah.Model.Jelajah;
import id.lungodev.lungo.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Jelajah.Model.Jelajah;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class UmkmAdapter extends RecyclerView.Adapter<UmkmAdapter.UmkmHolder> {
    private List<Jelajah> listUmkm;

    public UmkmAdapter(List<Jelajah> listUmkm){
        this.listUmkm = listUmkm;
    }

    @NonNull
    @Override
    public UmkmAdapter.UmkmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UmkmAdapter.UmkmHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UmkmAdapter.UmkmHolder umkmHolder, int i) {
        umkmHolder.bindItem(listUmkm.get(i));
    }

    @Override
    public int getItemCount() {
        return listUmkm.size();
    }

    class UmkmHolder extends RecyclerView.ViewHolder {

        ImageView fotoUmkm;
        TextView namaUmkm, lokasiUmkm;
        LinearLayout ratingLayout;

        UmkmHolder(@NonNull View itemView) {
            super(itemView);

            fotoUmkm = itemView.findViewById(R.id.iv_foto_lokasi);
            namaUmkm = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiUmkm = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }
        void bindItem(Jelajah item){
            namaUmkm.setText(item.getNama());
            lokasiUmkm.setText(item.getLokasi_real());
            Log.d("FOTO", "bindItem: "+item.getFoto());
            GlideApp.with(itemView.getContext())
                    .asBitmap()
                    .load(item.getFoto().get(0))
                    .fallback(R.color.teal)
                    .into(fotoUmkm);

        }
    }
}
