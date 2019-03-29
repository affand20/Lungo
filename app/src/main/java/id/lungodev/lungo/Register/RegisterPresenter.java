package id.lungodev.lungo.Register;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import id.lungodev.lungo.Model.User;

import static id.lungodev.lungo.SplashActivity.prefs;

public class RegisterPresenter {

    private RegisterView view;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private FirebaseStorage mStorage = FirebaseStorage.getInstance();
    private Uri imageUri;

    RegisterPresenter(RegisterView view){
        this.view = view;
    }

    void uploadProfileImage(Bitmap data, String UID){
        final StorageReference reference = mStorage.getReference().child("users/profile-pic/"+UID);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        data.compress(Bitmap.CompressFormat.JPEG, 95, baos);
        byte[] dataByte = baos.toByteArray();

        UploadTask uploadTask = reference.putBytes(dataByte);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        Log.d("URI", "onComplete: "+task.getResult());
                        imageUri = task.getResult();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("FAILED", "onFailure: upload image failed");
            }
        });
    }

    void doRegister(final String namaLengkap, final String email, final String password, final String nomorTelepon, final Uri uriFoto){
        view.hideRegisterError();
        view.showLoading();
        // register user with firebase auth
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.d("ONSUCCESS AUTH", "onSuccess: register success, starting input to db. UID="+mAuth.getCurrentUser().getUid());

                final StorageReference reference = mStorage.getReference().child("users/profile-pic/"+authResult.getUser().getUid());
                reference.putFile(uriFoto).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                        return reference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            imageUri = task.getResult();
                            Log.d("URI", "onComplete: "+imageUri+","+task.getResult());

                            mAuth.getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder()
                                    .setDisplayName(namaLengkap)
                                    .setPhotoUri(imageUri)
                                    .build()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("INPUT NAMA LENGKAP", "onSuccess: "+namaLengkap);
                                    Log.d("DISPLAY NAME", "onSuccess: "+mAuth.getCurrentUser().getDisplayName());
                                    prefs.setNama(mAuth.getCurrentUser().getDisplayName());
                                    prefs.setUID(mAuth.getCurrentUser().getUid());
                                    prefs.setUrlFoto(String.valueOf(imageUri));
                                }
                            });
                            User user = new User(namaLengkap, email, password, nomorTelepon, String.valueOf(imageUri));
                            mDatabase.getReference("users").child(mAuth.getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("INSERT DB SUCCESS", "onSuccess: saving data success, going to menu");
                                    view.hideLoading();
                                    view.showRegisterSuccess();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    view.hideLoading();
                                    view.showRegisterError(e.getLocalizedMessage());
                                }
                            });
                        } else{
                            view.hideLoading();
                            view.showRegisterError(task.getException().getLocalizedMessage());
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.hideLoading();
                view.showRegisterError(e.getLocalizedMessage());
            }
        });
    }
}
