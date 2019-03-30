package id.lungodev.lungo.Jadwal.Model;

import java.io.Serializable;
import java.util.List;

public class Destinasiku implements Serializable {
    private String nama;
    private String deskripsi;
    private String lokasi_url;
    private String lokasi_real;
    private int rating;
    private String video_url;
    private List<String> foto;
    private String durasi;

    public Destinasiku(String nama, String deskripsi, String lokasi_url, String lokasi_real, int rating, String video_url, List<String> foto, String durasi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi_url = lokasi_url;
        this.lokasi_real = lokasi_real;
        this.rating = rating;
        this.video_url = video_url;
        this.foto = foto;
        this.durasi = durasi;
    }

    public Destinasiku(){}

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public List<String> getFoto() {
        return foto;
    }

    public void setFoto(List<String> foto) {
        this.foto = foto;
    }

}
