package id.lungodev.lungo.Model;

import java.util.List;

public class User {

    private String namaLengkap;
    private String email;
    private String password;
    private String nomorTelepon;
    private String fotoUrl;
    private List<RiwayatJadwal> riwayatJadwal;

    User(){}

    public User(String namaLengkap, String email, String password, String nomorTelepon, String fotoUrl){
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.password = password;
        this.nomorTelepon = nomorTelepon;
        this.fotoUrl = fotoUrl;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public List<RiwayatJadwal> getRiwayatJadwal() {
        return riwayatJadwal;
    }
}
