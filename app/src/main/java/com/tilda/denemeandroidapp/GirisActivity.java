package com.tilda.denemeandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //private FirebaseUser user;
    EditText eEmail, ePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser()!=null){
            Intent i = new Intent(GirisActivity.this, Main2Activity.class);
            startActivity(i);
            finish();
        }



        eEmail = findViewById(R.id.editText);
        ePassword = findViewById(R.id.editText2);
    }

    public void btnKayitOl(View v){
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(GirisActivity.this, "Kullanıcı kaydedildi.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GirisActivity.this, "Hata.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void btnGiris(View v){
        String email = eEmail.getText().toString();
        String password = ePassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(GirisActivity.this, "Giriş başarılı.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(GirisActivity.this, Main2Activity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(GirisActivity.this, "Giriş hatalı. " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
