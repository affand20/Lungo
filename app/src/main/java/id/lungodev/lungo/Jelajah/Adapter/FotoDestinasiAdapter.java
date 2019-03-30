package id.lungodev.lungo.Jelajah.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class FotoDestinasiAdapter extends RecyclerView.Adapter<FotoDestinasiAdapter.ViewHolder> {

    private List<String> listFoto;

    public FotoDestinasiAdapter(List<String> listFoto){
        this.listFoto = listFoto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_foto_destinasi, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindItem(listFoto.get(i));
    }

    @Override
    public int getItemCount() {
        return listFoto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_foto = itemView.findViewById(R.id.foto_destinasi);
        }

        void bindItem(String foto){
            GlideApp.with(itemView.getContext())
                    .asBitmap()
                    .load(foto)
                    .into(iv_foto);
        }
    }
}
