package id.lungodev.lungo.Akomodasi;

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
import id.lungodev.lungo.Akomodasi.Adapter.FotoDestinasiAdapter;
import id.lungodev.lungo.Akomodasi.Model.Akomodasi;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class DetailAkomodasiActivity extends AppCompatActivity {

    private RelativeLayout backLayout;
    private TextView namaDestinasi, lokasiDestinasi, deskripsiDestinasi;
    private RecyclerView rvFotoDestinasi;
    private MaterialButton pilihDestinasi;
    private ImageView thumbnailDestinasi;

    private FotoDestinasiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akomodasi);

        final Akomodasi akomodasi = (Akomodasi) getIntent().getSerializableExtra("akomodasi");

        thumbnailDestinasi = findViewById(R.id.thumbnail_destinasi);
        backLayout = findViewById(R.id.back_layout);
        namaDestinasi = findViewById(R.id.nama_destinasi);
        lokasiDestinasi = findViewById(R.id.lokasi_destinasi);
        rvFotoDestinasi = findViewById(R.id.rv_foto);
        pilihDestinasi = findViewById(R.id.pilih_destinasi);

        if (getIntent().getStringExtra("status")!=null && getIntent().getStringExtra("status").equals("TAMBAH_DESTINASI")){
            pilihDestinasi.setVisibility(View.VISIBLE);
        }

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
                Dialog dialog = new Dialog(DetailAkomodasiActivity.this);
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
                                akomodasi.getNama(),
                                "",
                                akomodasi.getLokasi_url(),
                                akomodasi.getLokasi_real(),
                                akomodasi.getRating(),
                                "",
                                akomodasi.getFoto(),
                                tieDurasi.getText().toString());
                        FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal/"+ JadwalkuActivity.key+"/destinasi").push().setValue(destinasiku).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(DetailAkomodasiActivity.this, JadwalLiburanSayaActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                finish();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });

        namaDestinasi.setText(akomodasi.getNama());
        lokasiDestinasi.setText(akomodasi.getLokasi_real());
        GlideApp.with(this)
                .asBitmap()
                .load(akomodasi.getFoto().get(0))
                .centerCrop()
                .into(thumbnailDestinasi);


        adapter = new FotoDestinasiAdapter(akomodasi.getFoto());
        rvFotoDestinasi.setAdapter(adapter);
    }
}
