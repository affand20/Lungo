package id.lungodev.lungo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import id.lungodev.lungo.Login.LoginActivity;
import id.lungodev.lungo.Menu.MenuActivity;
import id.lungodev.lungo.Utils.Preferences.AppPreferences;

public class SplashActivity extends AppCompatActivity {

    public static AppPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefs = new AppPreferences(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefs.getUID()==null){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                } else{
                    startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                }
                finish();
            }
        }, 2500);
    }
}
