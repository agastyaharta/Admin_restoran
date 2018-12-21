package com.example.agastyaharta.adminrestoran.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class InsertMenuActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    protected EditText namaText, hargaText, deskripsiText;
    protected TextView simpanTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);

        //deklarasi spinner
        final Spinner spinner = findViewById(R.id.kategori_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //xml declaration
        namaText = findViewById(R.id.nama_text);
        hargaText = findViewById(R.id.harga_text);
        deskripsiText = findViewById(R.id.deskripsi_text);
        simpanTextView = findViewById(R.id.simpan_tv);


        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        simpanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String inputNama = namaText.getText().toString();
                final String inputHarga = hargaText.getText().toString();
                final String inputDeskripsi = deskripsiText.getText().toString();
                final String inputSpinner = spinner.getSelectedItem().toString();

                //manggil api
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MainActivity.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<Value> call = api.menubaru(inputNama, inputHarga, inputDeskripsi, inputSpinner);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        Boolean success = response.body().getSuccess();
                        if (success){
                            Toast.makeText(InsertMenuActivity.this, "Data Menu Berhasil dimasukan", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(), AddMenuActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(InsertMenuActivity.this, "Gagal memasukan akun baru ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });
            }
        });
//

    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
