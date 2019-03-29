package id.lungodev.lungo.Register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import id.lungodev.lungo.Login.LoginActivity;
import id.lungodev.lungo.Menu.MenuActivity;
import id.lungodev.lungo.R;
import id.lungodev.lungo.Utils.GlideApp;

public class RegisterActivity extends AppCompatActivity implements RegisterView{

    private ImageButton backButton;
    private TextView errorText;
    private CircleImageView profile_img;
    private TextInputEditText tieNamaLengkap, tieEmail, tiePassword, tieNomorTelepon;
    private MaterialButton registerBtn;
    private ProgressBar progressBar;
    private RelativeLayout backLayout;

    private RegisterPresenter presenter;

    private static int CHOOSE_IMAGE = 101;
    private Bitmap uploadedPhotoBitmap;
    private Uri uriProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tieNamaLengkap = findViewById(R.id.tie_nama_lengkap);
        backButton = findViewById(R.id.back_btn);
        profile_img = findViewById(R.id.foto_profil);
        tieEmail = findViewById(R.id.tie_email);
        tiePassword = findViewById(R.id.tie_password);
        tieNomorTelepon = findViewById(R.id.tie_telepon);
        registerBtn = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progress_bar_register);
        errorText = findViewById(R.id.error_text);
        backLayout = findViewById(R.id.back_layout);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    presenter.doRegister(
                            tieNamaLengkap.getText().toString(),
                            tieEmail.getText().toString(),
                            tiePassword.getText().toString(),
                            tieNomorTelepon.getText().toString(),
                            uriProfileImage
                    );
                }
            }
        });

        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();
            }
        });

        presenter = new RegisterPresenter(this);
    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Foto Profil"), CHOOSE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == Activity.RESULT_OK && data!=null && data.getData()!=null){
            uriProfileImage = data.getData();
            try {
                uploadedPhotoBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
//            uploadedPhotoBitmap = (Bitmap) data.getExtras().get("data");
            GlideApp.with(this)
                    .asBitmap()
                    .load(uploadedPhotoBitmap)
                    .into(profile_img);
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(tieNamaLengkap.getText().toString())){
            tieNamaLengkap.requestFocus();
            tieNamaLengkap.setError("Wajib diisi");
            return false;
        }
        if (TextUtils.isEmpty(tieEmail.getText().toString())){
            tieEmail.requestFocus();
            tieEmail.setError("Wajib diisi");
            return false;
        }
        if (TextUtils.isEmpty(tieNomorTelepon.getText().toString())){
            tieNomorTelepon.requestFocus();
            tieNomorTelepon.setError("Wajib diisi");
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
    public void showRegisterError(String message) {
        errorText.setText(message);
        errorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRegisterSuccess() {
        startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
        finish();
    }

    @Override
    public void hideRegisterError() {
        errorText.setVisibility(View.GONE);
    }
}
