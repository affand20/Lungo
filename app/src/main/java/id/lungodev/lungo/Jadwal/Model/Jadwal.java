package id.lungodev.lungo.Jadwal.Model;

import java.io.Serializable;

public class Jadwal implements Serializable {

    private String id;
    private String tanggalBerangkat;
    private String waktuBerangkat;
    private String kotaAsal;
    private String transport;
    private String estimasi;

    public Jadwal(String tanggalBerangkat, String waktuBerangkat, String kotaAsal, String transport, String estimasi) {
        this.tanggalBerangkat = tanggalBerangkat;
        this.waktuBerangkat = waktuBerangkat;
        this.kotaAsal = kotaAsal;
        this.transport = transport;
        this.estimasi = estimasi;
    }

    public Jadwal(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(String tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public String getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(String waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }
}
