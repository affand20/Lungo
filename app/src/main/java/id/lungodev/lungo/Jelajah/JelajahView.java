package id.lungodev.lungo.Jelajah;

import java.util.List;

import id.lungodev.lungo.Jelajah.Model.Jelajah;

    public interface JelajahView {

        void showLoadingPantai();
        void hideLoadingPantai();

        void showLoadingTaman();
        void hideLoadingTaman();

        void showLoadingAirTerjun();
        void hideLoadingAirTerjun();

        void showLoadingUmkm();
        void hideLoadingUmkm();

        void showEmptyData();
        void hideEmptyData();

        void showRekomendasiJelajah(List<Jelajah> listRekomendasi);

        void showJelajahPantai(List<Jelajah> listPantai);
        void showJelajahTaman(List<Jelajah> listTaman);
        void showJelajahAirTerjun(List<Jelajah> listAirTerjun);
        void showJelajahUmkm(List<Jelajah> listUmkm);
    }

