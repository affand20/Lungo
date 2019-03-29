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

import java.util.List;

import id.lungodev.lungo.Akomodasi.Model.Akomodasi;
import id.lungodev.lungo.R;

public class PenginapanAdapter extends RecyclerView.Adapter<PenginapanAdapter.PenginapanHolder> {

    private List<Akomodasi> listPenginapan;

    public PenginapanAdapter(List<Akomodasi> listPenginapan){
        this.listPenginapan = listPenginapan;
    }

    @NonNull
    @Override
    public PenginapanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PenginapanHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PenginapanHolder penginapanHolder, int i) {
        penginapanHolder.bindItem(listPenginapan.get(i));
    }

    @Override
    public int getItemCount() {
        return listPenginapan.size();
    }

    class PenginapanHolder extends RecyclerView.ViewHolder {

        ImageView fotoRestoran;
        TextView namaRestoran, lokasiRestoran;
        LinearLayout ratingLayout;

        PenginapanHolder(@NonNull View itemView) {
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
