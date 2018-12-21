package com.example.agastyaharta.adminrestoran.activity;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agastyaharta.adminrestoran.R;
import com.example.agastyaharta.adminrestoran.api.API;
import com.example.agastyaharta.adminrestoran.api.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditMenuActivity extends AppCompatActivity {
    protected EditText etNama, etHarga, etDeskripsi;
    protected TextView tvSimpan;
    protected String namaMenu, hargaMenu, deskripsiMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);

        //deklarasi
        Intent intent = getIntent();
        etNama = findViewById(R.id.nama_text);
        etHarga = findViewById(R.id.harga_text);
        etDeskripsi = findViewById(R.id.deskripsi_text);
        tvSimpan = findViewById(R.id.simpan_tv);

       namaMenu = intent.getExtras().getString("nama_hidangan");
       hargaMenu = intent.getExtras().getString("harga_hidangan");
       deskripsiMenu = intent.getExtras().getString("deskripsi_hidangan");

       etNama.setText(namaMenu);
       etDeskripsi.setText(deskripsiMenu);
       etHarga.setText(hargaMenu);

        tvSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namaEdit = String.valueOf(etNama.getText());
                String hargaEdit = String.valueOf(etHarga.getText());
                String deskripsiEdit = String.valueOf(etDeskripsi.getText());

                editHidangan(namaEdit, hargaEdit, deskripsiEdit);
            }
        });
    }

    public void editHidangan(String namaEdit, String hargaEdit, String deskripsiEdit) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Value> call = api.editMenu(namaEdit, hargaEdit, deskripsiEdit);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Boolean success = response.body().getSuccess();
                if(success){
                    Toast.makeText(EditMenuActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditMenuActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(EditMenuActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
