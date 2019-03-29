package id.lungodev.lungo.Login;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import id.lungodev.lungo.Menu.MenuActivity;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText tieEmail;
    private TextInputEditText tiePassword;
    private MaterialButton loginBtn;
    private MaterialButton loginWithGoogle;
    private TextView register;
    private ProgressBar progressBar;
    private TextView failedLoginText;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tieEmail = findViewById(R.id.tie_email);
        tiePassword = findViewById(R.id.tie_password);
        loginBtn = findViewById(R.id.login_btn);
        loginWithGoogle = findViewById(R.id.login_dengan_google);
        register = findViewById(R.id.register_text);
        progressBar = findViewById(R.id.progress_bar_login);
        failedLoginText = findViewById(R.id.error_text);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    presenter.doLogin(tieEmail.getText().toString(), tiePassword.getText().toString());
                }
            }
        });

        presenter = new LoginPresenter(this);
    }

    private boolean validate() {
        if (TextUtils.isEmpty(tieEmail.getText().toString())){
            tieEmail.requestFocus();
            tieEmail.setError("Wajib diisi");
            return false;
        }
        if (TextUtils.isEmpty(tiePassword.getText().toString())){
            tiePassword.requestFocus();
            tiePassword.setError("Wajib diisi");
            return false;
        }

        return true;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccessLogin() {
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
        finish();
    }

    @Override
    public void showFailedLogin(String message) {
        failedLoginText.setText(message);
        failedLoginText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFailedLogin() {
        failedLoginText.setVisibility(View.GONE);
    }
}
