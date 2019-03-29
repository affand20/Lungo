package id.lungodev.lungo.Akomodasi;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Akomodasi.Model.Akomodasi;

public class AkomodasiPresenter {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private List<Akomodasi> listRestoran = new ArrayList<>();
    private List<Akomodasi> listPenginapan = new ArrayList<>();
    private List<Akomodasi> listRekomendasi = new ArrayList<>();
    private AkomodasiView view;

    public AkomodasiPresenter(AkomodasiView view){
        this.view = view;
    }

    public void getRestoranData(){
        view.showLoadingRestoran();
        mDatabase.getReference("akomodasi/restoran").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listRestoran.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Akomodasi akomodasi = snapshot.getValue(Akomodasi.class);
                    listRestoran.add(akomodasi);
                }
                if (listRestoran.size()>0){
                    view.hideLoadingRestoran();
                    view.showAkomodasiRestoran(listRestoran);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }

    public void getPenginapanData(){
        view.showLoadingPenginapan();
        mDatabase.getReference("akomodasi/penginapan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listRestoran.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Akomodasi akomodasi = snapshot.getValue(Akomodasi.class);
                    listRestoran.add(akomodasi);
                }
                if (listRestoran.size()>0){
                    view.hideLoadingPenginapan();
                    view.showAkomodasiPenginapan(listRestoran);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR", "onCancelled: "+databaseError.getMessage());
            }
        });
    }
}
