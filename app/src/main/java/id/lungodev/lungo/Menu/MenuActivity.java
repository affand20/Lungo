package id.lungodev.lungo.Menu;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import id.lungodev.lungo.R;

import static id.lungodev.lungo.SplashActivity.prefs;

public class MenuActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private CircleImageView profilePic;
    private RelativeLayout jadwalLiburan, jelajaBanyuwangi, akomodasi, transaksiBRI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tvGreeting = findViewById(R.id.greetings);
        profilePic = findViewById(R.id.profile_pic);
        jadwalLiburan = findViewById(R.id.kelola_menu);
        jelajaBanyuwangi = findViewById(R.id.jelajah_menu);
        akomodasi = findViewById(R.id.akomodasi_menu);
        transaksiBRI = findViewById(R.id.bri_menu);

        tvGreeting.setText(String.format(getResources().getString(R.string.greetings), prefs.getNama()));
    }
}
