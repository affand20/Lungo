package id.lungodev.lungo.Login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static id.lungodev.lungo.SplashActivity.prefs;

public class LoginPresenter {

    private LoginView view;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    LoginPresenter(LoginView view){
        this.view = view;
    }

    void doLogin(String email, String password){
        view.showLoading();
        view.hideFailedLogin();
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                prefs.setNama(authResult.getUser().getDisplayName());
                prefs.setUID(authResult.getUser().getUid());
                view.hideLoading();
                view.showSuccessLogin();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.hideLoading();
                view.showFailedLogin(e.getLocalizedMessage());
            }
        });

    }

}
