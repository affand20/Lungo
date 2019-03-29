package id.lungodev.lungo.Jelajah;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Jelajah.Model.Jelajah;

public class JelajahPresenter {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private List<Jelajah> listPantai = new ArrayList<>();
    private List<Jelajah> listAirTerjun = new ArrayList<>();
    private List<Jelajah> listTaman = new ArrayList<>();
    private List<Jelajah> listUmkm = new ArrayList<>();
    private JelajahView view;

    public JelajahPresenter(JelajahView view){
        this.view = view;
    }

    public void getPantaiData(){
        view.showLoadingPantai();
        mDatabase.getReference("wisata/pantai").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPantai.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Jelajah jelajah = snapshot.getValue(Jelajah.class);
                    listPantai.add(jelajah);
                }
                if (listPantai.size()>0){
                    view.hideLoadingPantai();
                    view.showJelajahPantai(listPantai);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }

    public void getAirTerjunData(){
        view.showLoadingAirTerjun();
        mDatabase.getReference("wisata/air_terjun").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAirTerjun.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Jelajah jelajah = snapshot.getValue(Jelajah.class);
                    listAirTerjun.add(jelajah);
                }
                if (listAirTerjun.size()>0){
                    view.hideLoadingAirTerjun();
                    view.showJelajahAirTerjun(listAirTerjun);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }
    public void getTamanData(){
        view.showLoadingTaman();
        mDatabase.getReference("wisata/taman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTaman.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Jelajah jelajah = snapshot.getValue(Jelajah.class);
                    listTaman.add(jelajah);
                }
                if (listTaman.size()>0){
                    view.hideLoadingTaman();
                    view.showJelajahTaman(listTaman);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }
    public void getUmkmData(){
        view.showLoadingUmkm();
        mDatabase.getReference("wisata/umkm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listUmkm.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Jelajah jelajah = snapshot.getValue(Jelajah.class);
                    listUmkm.add(jelajah);
                }
                if (listUmkm.size()>0){
                    view.hideLoadingUmkm();
                    view.showJelajahUmkm(listUmkm);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }
}
