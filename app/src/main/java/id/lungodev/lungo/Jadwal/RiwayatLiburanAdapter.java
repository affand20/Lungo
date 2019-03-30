package id.lungodev.lungo.Jadwal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.lungodev.lungo.Jadwal.Model.Jadwal;
import id.lungodev.lungo.R;

public class RiwayatLiburanAdapter extends RecyclerView.Adapter<RiwayatLiburanAdapter.RiwayatHolder> {

    private List<Jadwal> riwayatJadwal;

    RiwayatLiburanAdapter(List<Jadwal> riwayatJadwal){
        this.riwayatJadwal = riwayatJadwal;
    }

    @NonNull
    @Override
    public RiwayatHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RiwayatHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_riwayat_jadwalku, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatHolder riwayatHolder, int i) {
        riwayatHolder.bindItem(riwayatJadwal.get(i));
    }

    @Override
    public int getItemCount() {
        return riwayatJadwal.size();
    }

    class RiwayatHolder extends RecyclerView.ViewHolder {

        TextView tglBerangkat;

        RiwayatHolder(@NonNull View itemView) {
            super(itemView);

            tglBerangkat = itemView.findViewById(R.id.tanggal_berangkat);
        }

        void bindItem(Jadwal item){
            tglBerangkat.setText(item.getTanggalBerangkat());
        }
    }
}
