package id.lungodev.lungo.Register;

public interface RegisterView {

    void showLoading();
    void hideLoading();
    void showRegisterError(String message);
    void showRegisterSuccess();
    void hideRegisterError();

}
