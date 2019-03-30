package id.lungodev.lungo.Jadwal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Jadwal.Jadwalku.JadwalkuActivity;
import id.lungodev.lungo.Jadwal.Model.Jadwal;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.ItemClickSupport;

import static id.lungodev.lungo.SplashActivity.prefs;

public class JadwalLiburanSayaActivity extends AppCompatActivity {

    private RelativeLayout backLayout;
    private RecyclerView rvJadwalku;
    private FloatingActionButton fabAddJadwal;
    private RiwayatLiburanAdapter adapter;
    private ProgressBar progressBar;

    private List<Jadwal> jadwalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_liburan_saya);

        backLayout = findViewById(R.id.back_layout);
        rvJadwalku = findViewById(R.id.rv_jadwal_saya);
        fabAddJadwal = findViewById(R.id.btn_add_jadwal);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        rvJadwalku.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new RiwayatLiburanAdapter(jadwalList);
//        rvJadwalku.setAdapter(adapter);

        fabAddJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JadwalLiburanSayaActivity.this, JadwalLiburanActivity.class));
            }
        });

        ItemClickSupport.addTo(rvJadwalku).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JadwalLiburanSayaActivity.this, JadwalkuActivity.class);
                intent.putExtra("jadwalku", jadwalList.get(position));
                startActivity(intent);
            }
        });

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FirebaseDatabase.getInstance().getReference("users/"+prefs.getUID()+"/riwayatJadwal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jadwalList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jadwal jadwal = data.getValue(Jadwal.class);
                    jadwal.setId(data.getKey());
                    jadwalList.add(jadwal);
                }
                progressBar.setVisibility(View.GONE);
                adapter = new RiwayatLiburanAdapter(jadwalList);
                rvJadwalku.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
