package id.lungodev.lungo.Akomodasi;

import java.util.List;

import id.lungodev.lungo.Akomodasi.Model.Akomodasi;

public interface AkomodasiView {

    void showLoadingRestoran();
    void hideLoadingRestoran();
    void showLoadingPenginapan();
    void hideLoadingPenginapan();
    void showEmptyData();
    void hideEmptyData();
    void showAkomodasiPenginapan(List<Akomodasi> listPenginapan);
    void showAkomodasiRestoran(List<Akomodasi> listRestoran);
}
