package id.lungodev.lungo.Utils.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    public static final String PREFS_NAME = "app_pref";

    private static final String USER_ID = "user_id";
    private static final String NAMA = "nama";

    private final SharedPreferences prefs;

    public AppPreferences(Context context){
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setUID(String token){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USER_ID, token);
        editor.apply();
    }

    public String getUID(){
        return prefs.getString(USER_ID, null);
    }

    public void setNama(String nama){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(NAMA, nama);
        editor.apply();
    }

    public String getNama(){
        return prefs.getString(NAMA, null);
    }


}