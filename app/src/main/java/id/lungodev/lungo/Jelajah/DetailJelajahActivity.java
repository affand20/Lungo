package id.lungodev.lungo.Jelajah;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import id.lungodev.lungo.Jadwal.JadwalLiburanSayaActivity;
import id.lungodev.lungo.Jadwal.Jadwalku.JadwalkuActivity;
import id.lungodev.lungo.Jadwal.Model.Destinasiku;
import id.lungodev.lungo.Jelajah.Adapter.FotoDestinasiAdapter;
import id.lungodev.lungo.Jelajah.Model.Jelajah;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class DetailJelajahActivity extends AppCompatActivity {

    private RelativeLayout backLayout;
    private TextView namaDestinasi, lokasiDestinasi, deskripsiDestinasi;
    private RecyclerView rvFotoDestinasi;
    private MaterialButton pilihDestinasi;
    private ImageView thumbnailDestinasi;

    private FotoDestinasiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jelajah);

        final Jelajah jelajah = (Jelajah) getIntent().getSerializableExtra("jelajah");

        thumbnailDestinasi = findViewById(R.id.thumbnail_destinasi);
        backLayout = findViewById(R.id.back_layout);
        namaDestinasi = findViewById(R.id.nama_destinasi);
        lokasiDestinasi = findViewById(R.id.lokasi_destinasi);
        deskripsiDestinasi = findViewById(R.id.deskripsi_destinasi);
        rvFotoDestinasi = findViewById(R.id.rv_foto);
        pilihDestinasi = findViewById(R.id.pilih_destinasi);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rvFotoDestinasi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        pilihDestinasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(DetailJelajahActivity.this);
                dialog.setContentView(R.layout.layout_dialog_pilih_destinasi);

                final TextInputEditText tieDurasi = dialog.findViewById(R.id.tie_durasi);
                MaterialButton submitBtn = dialog.findViewById(R.id.submit_pilihan);

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(tieDurasi.getText().toString())){
                            tieDurasi.requestFocus();
                            tieDurasi.setError("Wajib diisi");
                            return;
                        }
                        Destinasiku destinasiku = new Destinasiku(
                                jelajah.getNama(),
                                jelajah.getDeskripsi(),
                                jelajah.getLokasi_url(),
                                jelajah.getLokasi_real(),
                                jelajah.getRating(),
                                jelajah.getVideo_url(),
                                jelajah.getFoto(),
                                tieDurasi.getText().toString());
                        FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal/"+ JadwalkuActivity.key+"/destinasi").push().setValue(destinasiku).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(DetailJelajahActivity.this, JadwalLiburanSayaActivity.class));
                                finish();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });

        namaDestinasi.setText(jelajah.getNama());
        lokasiDestinasi.setText(jelajah.getLokasi_real());
        deskripsiDestinasi.setText(jelajah.getDeskripsi());
        GlideApp.with(this)
                .asBitmap()
                .load(jelajah.getFoto().get(0))
                .into(thumbnailDestinasi);


        adapter = new FotoDestinasiAdapter(jelajah.getFoto());
        rvFotoDestinasi.setAdapter(adapter);
    }
}
