package com.tilda.denemeandroidapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    TextView textView;
    TextView textView2;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef, mRefUrunler;
    private ValueEventListener mesajListener;
    private ChildEventListener urunListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView.setText(user.getEmail());

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("mesaj");
        mRef.setValue("Merhaba Firebase.");

        mesajListener = mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mesaj = dataSnapshot.getValue(String.class);
                textView2.setText(mesaj);
                System.out.println(mesaj);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Okuma hatası");
            }
        });

        mRefUrunler = mDatabase.getReference("urunler");
        urunListener = mRefUrunler.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Urun yeniUrun = dataSnapshot.getValue(Urun.class);
                System.out.println("Kayıt eklendi : " + yeniUrun.toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Urun yeniUrun = dataSnapshot.getValue(Urun.class);
                System.out.println("Kayıt silindi : " + yeniUrun.toString());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRef.removeEventListener(mesajListener);
        mRefUrunler.removeEventListener(urunListener);
    }

    public void btnCikis(View v){
        mAuth.signOut();
        Intent i = new Intent(Main2Activity.this, GirisActivity.class);
        startActivity(i);
        finish();
    }


    public void btnPush(View v){
        mRef = mDatabase.getReference("PushKayitlari");
        mRef.push().setValue("Push ile eklenen kayıt.");
    }

    int sayi=0;
    public void btnChild(View v){
        sayi++;
        mRef = mDatabase.getReference("childKayitlari");
        mRef.child("urun" + sayi).setValue("Chiled ile eklenen kayıt.");
    }


    public void btnModelPush(View v){
        Urun yeniUrun = new Urun("1000", "Iphone X", 5999);

        mRef = mDatabase.getReference("urunler");
        mRef.push().setValue(yeniUrun);
    }
    public void btnModelChild(View v){
        Urun yeniUrun = new Urun("1001", "Iphone 11", 8999);

        mRef = mDatabase.getReference("urunler");
        mRef.child(yeniUrun.getStokKodu()).setValue(yeniUrun);
    }


    public void btnDegistir(View v){
        //Urun yeniUrun = new Urun("1001", "Iphone 11", 9999);

        mRef = mDatabase.getReference("urunler");
        mRef.child("1001").child("fiyat").setValue(9999);
    }

    public void btnSil(View v){
        //Urun yeniUrun = new Urun("1001", "Iphone 11", 9999);

        mRef = mDatabase.getReference("urunler");
        mRef.child("1001").removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Toast.makeText(Main2Activity.this, "Kayıt silindi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
