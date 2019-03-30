package id.lungodev.lungo.Jelajah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Jelajah.Adapter.AirTerjunAdapter;
import id.lungodev.lungo.Jelajah.Adapter.DataranTinggiAdapter;
import id.lungodev.lungo.Jelajah.Adapter.PantaiAdapter;
import id.lungodev.lungo.Jelajah.Adapter.TamanAdapter;
import id.lungodev.lungo.Jelajah.Adapter.UmkmAdapter;
import id.lungodev.lungo.Jelajah.Model.Jelajah;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.ItemClickSupport;

public class JelajahActivity extends AppCompatActivity {

    private RelativeLayout backLayout;
    private RecyclerView rvPantai, rvDataranTinggi, rvUmkm, rvTaman, rvAirTerjun;

    private List<Jelajah> listAirTerjun = new ArrayList<>();
    private List<Jelajah> listPantai = new ArrayList<>();
    private List<Jelajah> listDataranTinggi = new ArrayList<>();
    private List<Jelajah> listUmkm = new ArrayList<>();
    private List<Jelajah> listTaman = new ArrayList<>();

    private AirTerjunAdapter adapterAirTerjun;
    private DataranTinggiAdapter adapterDataranTinggi;
    private PantaiAdapter adapterPantai;
    private TamanAdapter adapterTaman;
    private UmkmAdapter adapterUmkm;

    private ProgressBar progressAirTerjun;
    private ProgressBar progressDataranTinggi;
    private ProgressBar progressPantai;
    private ProgressBar progressTaman;
    private ProgressBar progressUmkm;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("wisata");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jelajah);

        backLayout = findViewById(R.id.back_layout);
        rvPantai = findViewById(R.id.rv_pantai);
        rvDataranTinggi = findViewById(R.id.rv_dataran_tinggi);
        rvUmkm = findViewById(R.id.rv_umkm);
        rvTaman = findViewById(R.id.rv_taman);
        rvAirTerjun = findViewById(R.id.rv_air_terjun);
        progressAirTerjun = findViewById(R.id.progress_bar_air_terjun);
        progressDataranTinggi = findViewById(R.id.progress_bar_dataran_tinggi);
        progressPantai = findViewById(R.id.progress_bar_pantai);
        progressTaman = findViewById(R.id.progress_bar_taman);
        progressUmkm = findViewById(R.id.progress_bar_umkm);

        rvPantai.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDataranTinggi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvUmkm.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvTaman.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvAirTerjun.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ItemClickSupport.addTo(rvPantai).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JelajahActivity.this, DetailJelajahActivity.class);
                intent.putExtra("jelajah", listPantai.get(position));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(rvDataranTinggi).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JelajahActivity.this, DetailJelajahActivity.class);
                intent.putExtra("jelajah", listDataranTinggi.get(position));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(rvUmkm).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JelajahActivity.this, DetailJelajahActivity.class);
                intent.putExtra("jelajah", listUmkm.get(position));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(rvTaman).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JelajahActivity.this, DetailJelajahActivity.class);
                intent.putExtra("jelajah", listTaman.get(position));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(rvAirTerjun).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(JelajahActivity.this, DetailJelajahActivity.class);
                intent.putExtra("jelajah", listAirTerjun.get(position));
                startActivity(intent);
            }
        });

        dbRef.child("air_terjun").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAirTerjun.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jelajah jelajah = data.getValue(Jelajah.class);
                    listAirTerjun.add(jelajah);
                }
                progressAirTerjun.setVisibility(View.GONE);
                adapterAirTerjun = new AirTerjunAdapter(listAirTerjun);
                rvAirTerjun.setAdapter(adapterAirTerjun);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbRef.child("datarantinggi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDataranTinggi.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jelajah jelajah = data.getValue(Jelajah.class);
                    listDataranTinggi.add(jelajah);
                }
                progressDataranTinggi.setVisibility(View.GONE);
                adapterDataranTinggi = new DataranTinggiAdapter(listDataranTinggi);
                rvDataranTinggi.setAdapter(adapterDataranTinggi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbRef.child("pantai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPantai.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jelajah jelajah = data.getValue(Jelajah.class);
                    listPantai.add(jelajah);
                }
                progressPantai.setVisibility(View.GONE);
                adapterPantai = new PantaiAdapter(listPantai);
                rvPantai.setAdapter(adapterPantai);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbRef.child("taman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTaman.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jelajah jelajah = data.getValue(Jelajah.class);
                    listTaman.add(jelajah);
                }
                progressTaman.setVisibility(View.GONE);
                adapterTaman = new TamanAdapter(listTaman);
                rvTaman.setAdapter(adapterTaman);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbRef.child("umkm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUmkm.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Jelajah jelajah = data.getValue(Jelajah.class);
                    listUmkm.add(jelajah);
                }
                progressUmkm.setVisibility(View.GONE);
                adapterUmkm = new UmkmAdapter(listUmkm);
                rvUmkm.setAdapter(adapterUmkm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
