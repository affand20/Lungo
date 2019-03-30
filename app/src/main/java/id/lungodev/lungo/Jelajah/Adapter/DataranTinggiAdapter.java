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

public class DataranTinggiAdapter extends RecyclerView.Adapter<DataranTinggiAdapter.DataranTinggiHolder> {
    private List<Jelajah> listDataranTinggi;

    public DataranTinggiAdapter(List<Jelajah> listDataranTinggi){
        this.listDataranTinggi = listDataranTinggi;
    }

    @NonNull
    @Override
    public DataranTinggiAdapter.DataranTinggiHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DataranTinggiAdapter.DataranTinggiHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataranTinggiAdapter.DataranTinggiHolder dataranTinggiHolder, int i) {
        dataranTinggiHolder.bindItem(listDataranTinggi.get(i));
    }

    @Override
    public int getItemCount() {
        return listDataranTinggi.size();
    }

    class DataranTinggiHolder extends RecyclerView.ViewHolder {

        ImageView fotoDataranTinggi;
        TextView namaDataranTinggi, lokasiDataranTinggi;
        LinearLayout ratingLayout;

        DataranTinggiHolder(@NonNull View itemView) {
            super(itemView);

            fotoDataranTinggi = itemView.findViewById(R.id.iv_foto_lokasi);
            namaDataranTinggi = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiDataranTinggi = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }
        void bindItem(Jelajah item){
            namaDataranTinggi.setText(item.getNama());
            lokasiDataranTinggi.setText(item.getLokasi_real());
            Log.d("FOTO", "bindItem: "+item.getFoto());
            GlideApp.with(itemView.getContext())
                    .asBitmap()
                    .load(item.getFoto().get(0))
                    .fallback(R.color.teal)
                    .into(fotoDataranTinggi);

        }
    }
}
