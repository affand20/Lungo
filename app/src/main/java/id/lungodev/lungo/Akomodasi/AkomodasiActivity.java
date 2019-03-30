package id.lungodev.lungo.Akomodasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import id.lungodev.lungo.Akomodasi.Adapter.PenginapanAdapter;
import id.lungodev.lungo.Akomodasi.Adapter.RestoranAdapter;
import id.lungodev.lungo.Akomodasi.Model.Akomodasi;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.ItemClickSupport;

public class AkomodasiActivity extends AppCompatActivity implements AkomodasiView {

    private RelativeLayout backLayout;
    private RecyclerView rvRestoran, rvPenginapan;
    private RestoranAdapter restoranAdapter;
    private PenginapanAdapter penginapanAdapter;
    private AkomodasiPresenter presenter;

    private ProgressBar progressRestoran, progressPenginapan;

    private List<Akomodasi> listRestoran, listPenginapan;

    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akomodasi);

        backLayout = findViewById(R.id.back_layout);
        rvRestoran = findViewById(R.id.rv_restoran);
        rvPenginapan = findViewById(R.id.rv_penginapan);
        progressRestoran = findViewById(R.id.progress_restoran);
        progressPenginapan = findViewById(R.id.progress_penginapan);

        status = getIntent().getStringExtra("status");

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listRestoran = new ArrayList<>();
        listPenginapan = new ArrayList<>();

        rvRestoran.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvPenginapan.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        presenter = new AkomodasiPresenter(this);
        presenter.getRestoranData();
        presenter.getPenginapanData();

        restoranAdapter = new RestoranAdapter(listRestoran);
        penginapanAdapter = new PenginapanAdapter(listPenginapan);

        ItemClickSupport.addTo(rvRestoran).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(AkomodasiActivity.this, DetailAkomodasiActivity.class);
                intent.putExtra("akomodasi", listRestoran.get(position));
                if (status!=null){
                    intent.putExtra("status", status);
                }
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(rvPenginapan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(AkomodasiActivity.this, DetailAkomodasiActivity.class);
                intent.putExtra("akomodasi", listPenginapan.get(position));
                if (status!=null){
                    intent.putExtra("status", status);
                }
                startActivity(intent);
            }
        });

    }


    @Override
    public void showLoadingRestoran() {
        progressRestoran.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingRestoran() {
        progressRestoran.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingPenginapan() {
        progressPenginapan.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingPenginapan() {
        progressPenginapan.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyData() {

    }

    @Override
    public void hideEmptyData() {

    }

    @Override
    public void showAkomodasiPenginapan(List<Akomodasi> listPenginapan) {
        this.listPenginapan.clear();
        this.listPenginapan.addAll(listPenginapan);
        rvPenginapan.setAdapter(penginapanAdapter);
        penginapanAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAkomodasiRestoran(List<Akomodasi> listRestoran) {
        this.listRestoran.clear();
        this.listRestoran.addAll(listRestoran);
        rvRestoran.setAdapter(restoranAdapter);
        restoranAdapter.notifyDataSetChanged();
    }
}