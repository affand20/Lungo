package id.lungodev.lungo.Login;

public interface LoginView {

    void showLoading();
    void hideLoading();
    void showSuccessLogin();
    void showFailedLogin(String message);
    void hideFailedLogin();
}
