package id.lungodev.lungo.Jadwal.Jadwalku;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Akomodasi.AkomodasiActivity;
import id.lungodev.lungo.Jadwal.Model.Destinasiku;
import id.lungodev.lungo.Jadwal.Model.Jadwal;
import id.lungodev.lungo.Jelajah.JelajahActivity;
import id.lungodev.lungo.R;

public class JadwalkuActivity extends AppCompatActivity {

    private RelativeLayout backLayout;
    private RecyclerView rvDestinasi;
    private TextView kotaAsal, estimasi, waktuBerangkat, kendaraan;
    private CardView tambahTujuan;
    private MaterialButton setPulang;
    public static String key;

    private JadwalkuAdapter adapter;

    private List<Destinasiku> listDestinasi = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwalku);

        Jadwal jadwal = (Jadwal) getIntent().getSerializableExtra("jadwalku");


        backLayout = findViewById(R.id.back_layout);
        rvDestinasi = findViewById(R.id.rv_destinasi);
        kotaAsal = findViewById(R.id.kota_asal);
        estimasi = findViewById(R.id.estimasi_tiba);
        waktuBerangkat = findViewById(R.id.waktu_berangkat);
        kendaraan = findViewById(R.id.kendaraan);
        tambahTujuan = findViewById(R.id.tambah_tujuan);
        setPulang = findViewById(R.id.set_pulang);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (jadwal!=null){
            key = jadwal.getId();
            kotaAsal.setText(jadwal.getKotaAsal());
            estimasi.setText(String.format(getResources().getString(R.string.estimasi_wkt), jadwal.getEstimasi()));
            waktuBerangkat.setText(String.format(getResources().getString(R.string.wkt_berangkat), jadwal.getWaktuBerangkat()));
            kendaraan.setText(jadwal.getTransport());
        }
        rvDestinasi.setLayoutManager(new LinearLayoutManager(this));

        tambahTujuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(JadwalkuActivity.this);
                dialog.setContentView(R.layout.layout_dialog);

                MaterialButton jelajahBwi = dialog.findViewById(R.id.jelajah_banyuwangi);
                MaterialButton akomodasiBwi = dialog.findViewById(R.id.akomodasi_banyuwangi);

                jelajahBwi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        startActivity(new Intent(JadwalkuActivity.this, JelajahActivity.class).putExtra("status", "TAMBAH_DESTINASI"));
                    }
                });

                akomodasiBwi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        startActivity(new Intent(JadwalkuActivity.this, AkomodasiActivity.class).putExtra("status", "TAMBAH_DESTINASI"));
                    }
                });
                dialog.show();
            }
        });

        FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal/"+key+"/destinasi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Destinasiku destinasiku = data.getValue(Destinasiku.class);
                    listDestinasi.add(destinasiku);
                }

                adapter = new JadwalkuAdapter(listDestinasi);
                rvDestinasi.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
