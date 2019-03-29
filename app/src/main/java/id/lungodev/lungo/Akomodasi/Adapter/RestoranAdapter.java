package id.lungodev.lungo.Akomodasi.Adapter;

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

import id.lungodev.lungo.Akomodasi.Model.Akomodasi;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class RestoranAdapter extends RecyclerView.Adapter<RestoranAdapter.RestoranHolder>{

    private List<Akomodasi> listRestoran;

    public RestoranAdapter(List<Akomodasi> listRestoran){
        this.listRestoran = listRestoran;
    }

    @NonNull
    @Override
    public RestoranHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RestoranHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestoranHolder restoranHolder, int i) {
        restoranHolder.bindItem(listRestoran.get(i));
    }

    @Override
    public int getItemCount() {
        return listRestoran.size();
    }

    class RestoranHolder extends RecyclerView.ViewHolder {

        ImageView fotoRestoran;
        TextView namaRestoran, lokasiRestoran;
        LinearLayout ratingLayout;


        public RestoranHolder(@NonNull View itemView) {
            super(itemView);

            fotoRestoran = itemView.findViewById(R.id.iv_foto_lokasi);
            namaRestoran = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiRestoran = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }

        void bindItem(Akomodasi item){
            namaRestoran.setText(item.getNama());
            lokasiRestoran.setText(item.getLokasiReal());
            Log.d("FOTO", "bindItem: "+item.getListFoto());
//            GlideApp.with(itemView.getContext())
//                    .asBitmap()
//                    .load(item.getListFoto().get(0))
//                    .fallback(R.color.teal)
//                    .into(fotoRestoran);

        }
    }
}
