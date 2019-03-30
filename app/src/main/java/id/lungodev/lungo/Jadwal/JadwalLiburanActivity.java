package id.lungodev.lungo.Jadwal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import id.lungodev.lungo.Jadwal.Jadwalku.JadwalkuActivity;
import id.lungodev.lungo.Jadwal.Model.Jadwal;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.AnimSupport;

public class JadwalLiburanActivity extends AppCompatActivity {

    private ImageButton closeBtn;
    private MaterialButton startBtn, next1, next2;
    private RelativeLayout kelola1, kelola2, kelola3, kelola4, backLayout, backLayout2, backLayout3;
    private AnimSupport anim;
    private TextInputEditText tglBerangkat, jamBerangkat;
    private Spinner asalKota;
    private CardView bis,motor,mobil;

    private String ansTglBerangkat, ansJamBerangkat, ansAsalKota, ansKendaraan, ansEstimasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_liburan);

        anim = new AnimSupport();

        asalKota = findViewById(R.id.spinner_asal_kota);
        closeBtn = findViewById(R.id.close_btn);
        startBtn = findViewById(R.id.start_atur_jadwal);
        kelola1 = findViewById(R.id.kelola_1);
        kelola2 = findViewById(R.id.kelola_2);
        kelola3 = findViewById(R.id.kelola_3);
        kelola4 = findViewById(R.id.kelola_4);
        backLayout = findViewById(R.id.back_layout);
        backLayout2 = findViewById(R.id.back_layout_2);
        backLayout3 = findViewById(R.id.back_layout_3);
        tglBerangkat = findViewById(R.id.tie_berangkat);
        jamBerangkat = findViewById(R.id.tie_wkt_berangkat);
        next1 = findViewById(R.id.btn_next_1);
        next2 = findViewById(R.id.btn_next_2);
        bis = findViewById(R.id.bis);
        mobil = findViewById(R.id.mobil);
        motor = findViewById(R.id.motor);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.slideRightHideAnim(kelola2, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola2.setVisibility(View.GONE);
                        kelola1.setVisibility(View.VISIBLE);
                        anim.slideRightShowAnim(kelola1);
                    }
                });
            }
        });

        backLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.slideRightHideAnim(kelola3, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola3.setVisibility(View.GONE);
                        kelola2.setVisibility(View.VISIBLE);
                        anim.slideRightShowAnim(kelola2);
                    }
                });
            }
        });

        backLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.slideRightHideAnim(kelola4, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola4.setVisibility(View.GONE);
                        kelola3.setVisibility(View.VISIBLE);
                        anim.slideRightShowAnim(kelola3);
                    }
                });
            }
        });

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(tglBerangkat.getText().toString())){
                    tglBerangkat.requestFocus();
                    tglBerangkat.setError("Wajib diisi");
                    return;
                }
                if (TextUtils.isEmpty(jamBerangkat.getText().toString())){
                    jamBerangkat.requestFocus();
                    jamBerangkat.setError("Wajib diisi");
                    return;
                }
                ansTglBerangkat = tglBerangkat.getText().toString();
                ansJamBerangkat = jamBerangkat.getText().toString();

                anim.slideLeftHideAnim(kelola2, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola2.setVisibility(View.GONE);
                        kelola3.setVisibility(View.VISIBLE);
                        anim.slideLeftShowAnim(kelola3);
                    }
                });
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ansAsalKota = asalKota.getSelectedItem().toString();

                anim.slideLeftShowAnim(kelola4, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola3.setVisibility(View.GONE);
                        kelola4.setVisibility(View.VISIBLE);
                        anim.slideLeftShowAnim(kelola4);
                    }
                });
            }
        });

        bis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ansKendaraan = "bis";
                ansEstimasi = "6 Jam 30 Menit";

                Jadwal jadwal = new Jadwal(ansTglBerangkat, ansJamBerangkat, ansAsalKota, ansKendaraan, ansEstimasi);
                FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal").push().setValue(jadwal).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(JadwalLiburanActivity.this, JadwalkuActivity.class));
                        finish();
                    }
                });
            }
        });

        mobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ansKendaraan = "mobil";
                ansEstimasi = "6 Jam";
                Jadwal jadwal = new Jadwal(ansTglBerangkat, ansJamBerangkat, ansAsalKota, ansKendaraan, ansEstimasi);
                FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal").push().setValue(jadwal).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(JadwalLiburanActivity.this, JadwalkuActivity.class));
                        finish();
                    }
                });
            }
        });

        motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ansKendaraan = "motor";
                ansEstimasi = "8 Jam";
                Jadwal jadwal = new Jadwal(ansTglBerangkat, ansJamBerangkat, ansAsalKota, ansKendaraan, ansEstimasi);
                String key = FirebaseDatabase.getInstance().getReference("users/"+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal").push().getKey();
                jadwal.setId(key);
            FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/riwayatJadwal/"+key).setValue(jadwal).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(JadwalLiburanActivity.this, JadwalkuActivity.class));
                        finish();
                    }
                });
            }
        });



        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.slideLeftHideAnim(kelola1, new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        kelola1.setVisibility(View.GONE);
                        kelola2.setVisibility(View.VISIBLE);
                        anim.slideLeftShowAnim(kelola2);
                    }
                });
            }
        });
    }
}
