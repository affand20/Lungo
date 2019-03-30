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
public class TamanAdapter extends RecyclerView.Adapter<TamanAdapter.TamanHolder> {
    private List<Jelajah> listTaman;

    public TamanAdapter(List<Jelajah> listTaman){
        this.listTaman = listTaman;
    }

    @NonNull
    @Override
    public TamanAdapter.TamanHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TamanAdapter.TamanHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout_jelajah_dan_akomodasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TamanAdapter.TamanHolder tamanHolder, int i) {
        tamanHolder.bindItem(listTaman.get(i));
    }

    @Override
    public int getItemCount() {
        return listTaman.size();
    }

    class TamanHolder extends RecyclerView.ViewHolder {

        ImageView fotoTaman;
        TextView namaTaman, lokasiTaman;
        LinearLayout ratingLayout;

        TamanHolder(@NonNull View itemView) {
            super(itemView);

            fotoTaman = itemView.findViewById(R.id.iv_foto_lokasi);
            namaTaman = itemView.findViewById(R.id.tv_nama_tempat);
            lokasiTaman = itemView.findViewById(R.id.tv_lokasi);
            ratingLayout = itemView.findViewById(R.id.layout_rating);
        }
        void bindItem(Jelajah item){
            namaTaman.setText(item.getNama());
            lokasiTaman.setText(item.getLokasi_real());
            Log.d("FOTO", "bindItem: "+item.getFoto());
            GlideApp.with(itemView.getContext())
                    .asBitmap()
                    .load(item.getFoto().get(0))
                    .fallback(R.color.teal)
                    .into(fotoTaman);

        }
    }
}
