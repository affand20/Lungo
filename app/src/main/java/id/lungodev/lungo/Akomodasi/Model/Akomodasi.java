package id.lungodev.lungo.Akomodasi.Model;
import java.io.Serializable;
import java.util.List;

public class Akomodasi implements Serializable {
    private String nama;
    private String lokasi_url;
    private String lokasi_real;
    private int rating;
    private List<String> foto;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi_url() {
        return lokasi_url;
    }

    public void setLokasi_url(String lokasi_url) {
        this.lokasi_url = lokasi_url;
    }

    public String getLokasi_real() {
        return lokasi_real;
    }

    public void setLokasi_real(String lokasi_real) {
        this.lokasi_real = lokasi_real;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getFoto() {
        return foto;
    }

    public void setFoto(List<String> foto) {
        this.foto = foto;
    }

}
