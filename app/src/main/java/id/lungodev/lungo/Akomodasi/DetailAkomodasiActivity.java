package id.lungodev.lungo.Akomodasi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.lungodev.lungo.Akomodasi.Model.Akomodasi;
import id.lungodev.lungo.R;

public class DetailAkomodasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akomodasi);

        Akomodasi akomodasi = (Akomodasi) getIntent().getSerializableExtra("akomodasi");
    }
}
