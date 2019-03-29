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

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Jelajah.Model.Jelajah;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class PantaiAdapter extends RecyclerView.Adapter<PantaiAdapter.PantaiHolder>  {
    private List<Jelajah> listPantai;

    public PantaiAdapter(List<Jelajah> listpantai){
        this.listPantai = listPantai;
    }

    @NonNull
    @Override
    public PantaiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PantaiHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PantaiHolder pantaiHolder, int i) {
        pantaiHolder.bindItem(listPantai.get(i));
    }

    @Override
    public int getItemCount() {
        return listPantai.size();
    }

    class PantaiHolder extends RecyclerView.ViewHolder {

        ImageView fotoPantai;
        TextView namaPantai, lokasiPantai;
        LinearLayout ratingLayout;

        PantaiHolder(@NonNull View itemView) {
            super(itemView);

            fotoPantai = itemView.findViewById(R.id.iv_foto_lokasi);
            namaPantai = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiPantai = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }
        void bindItem(Jelajah item){
            namaPantai.setText(item.getNama());
            lokasiPantai.setText(item.getLokasi_real());
            Log.d("FOTO", "bindItem: "+item.getFoto());
//            GlideApp.with(itemView.getContext())
//                    .asBitmap()
//                    .load(item.getListFoto().get(0))
//                    .fallback(R.color.teal)
//                    .into(fotoRestoran);

        }
    }

}
