package id.lungodev.lungo.Akomodasi.Model;

import java.util.List;

public class Akomodasi {

    private String nama;
    private String lokasi_url;
    private String lokasi_real;
    private int rating;
    private List<String> foto;

    public Akomodasi(){}

    public Akomodasi(String nama, String lokasiUrl, String lokasiReal, int rating, List<String> foto) {
        this.nama = nama;
        this.lokasi_url = lokasiUrl;
        this.lokasi_real = lokasiReal;
        this.rating = rating;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasiUrl() {
        return lokasi_url;
    }

    public String getLokasiReal() {
        return lokasi_real;
    }

    public int getRating() {
        return rating;
    }

    public List<String> getListFoto() {
        return foto;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setLokasiUrl(String lokasiUrl) {
        this.lokasi_url = lokasiUrl;
    }

    public void setLokasiReal(String lokasiReal) {
        this.lokasi_real = lokasiReal;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFoto(List<String> foto) {
        this.foto = foto;
    }
}
