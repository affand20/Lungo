package id.lungodev.lungo.Jadwal.Jadwalku;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.lungodev.lungo.Jadwal.Model.Destinasiku;
import id.lungodev.lungo.R;

public class JadwalkuAdapter extends RecyclerView.Adapter<JadwalkuAdapter.ViewHolder> {

    private List<Destinasiku> listDestinasiku;

    public JadwalkuAdapter(List<Destinasiku> listDestinasiku){
        this.listDestinasiku = listDestinasiku;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_jadwal_destinasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindItems(listDestinasiku.get(i));
    }

    @Override
    public int getItemCount() {
        return listDestinasiku.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tujuanDestinasi, estimasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tujuanDestinasi = itemView.findViewById(R.id.tujuan_destinasi);
            estimasi = itemView.findViewById(R.id.estimasi_tiba);
        }

        void bindItems(Destinasiku destinasi){
            tujuanDestinasi.setText(destinasi.getNama());
            estimasi.setText(destinasi.getDurasi());
        }
    }
}
