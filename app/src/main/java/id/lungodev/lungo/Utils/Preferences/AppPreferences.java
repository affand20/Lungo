package id.lungodev.lungo.Utils.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.net.URL;

public class AppPreferences {
    public static final String PREFS_NAME = "app_pref";

    private static final String USER_ID = "user_id";
    private static final String NAMA = "nama";
    private static final String URL_FOTO = "url_foto";

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

    public void setUrlFoto(String urlFoto){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(URL_FOTO, urlFoto);
        editor.apply();
    }

    public String getUrlFoto(){
        return prefs.getString(URL_FOTO, null);
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