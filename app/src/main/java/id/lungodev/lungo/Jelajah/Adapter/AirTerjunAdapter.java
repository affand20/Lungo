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

class AirTerjunAdapter extends RecyclerView.Adapter<AirTerjunAdapter.AirTerjunHolder> {
    private List<Jelajah> listAirTerjun;

    public AirTerjunAdapter(List<Jelajah> listAirTerjun){
        this.listAirTerjun = listAirTerjun;
    }

    @NonNull
    @Override
    public AirTerjunAdapter.AirTerjunHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AirTerjunAdapter.AirTerjunHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AirTerjunAdapter.AirTerjunHolder airTerjunHolder, int i) {
        airTerjunHolder.bindItem(listAirTerjun.get(i));
    }

    @Override
    public int getItemCount() {
        return listAirTerjun.size();
    }

    class AirTerjunHolder extends RecyclerView.ViewHolder {

        ImageView fotoAirTerjun;
        TextView namaAirTerjun, lokasiAirTerjun;
        LinearLayout ratingLayout;

        AirTerjunHolder(@NonNull View itemView) {
            super(itemView);

            fotoAirTerjun = itemView.findViewById(R.id.iv_foto_lokasi);
            namaAirTerjun = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiAirTerjun = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }
        void bindItem(Jelajah item){
            namaAirTerjun.setText(item.getNama());
            lokasiAirTerjun.setText(item.getLokasi_real());
            Log.d("FOTO", "bindItem: "+item.getFoto());
//            GlideApp.with(itemView.getContext())
//                    .asBitmap()
//                    .load(item.getListFoto().get(0))
//                    .fallback(R.color.teal)
//                    .into(fotoRestoran);

        }
    }
}
