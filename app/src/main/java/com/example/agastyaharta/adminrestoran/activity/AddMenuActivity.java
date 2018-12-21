package com.example.agastyaharta.adminrestoran.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.agastyaharta.adminrestoran.R;
import com.example.agastyaharta.adminrestoran.api.API;
import com.example.agastyaharta.adminrestoran.api.Value;
import com.example.agastyaharta.adminrestoran.model.Hidangan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class AddMenuActivity extends AppCompatActivity {
    private List<Hidangan> allHidanganList = new ArrayList<>();

    FloatingTextButton fabkiri;
    RecyclerView rvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        fabkiri = (FloatingTextButton) findViewById(R.id.fab2);
        rvMenu = findViewById(R.id.rv_all_menu);

        callAPI();





        fabkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMenuActivity.this, InsertMenuActivity.class));
            }
        });



//        final ArrayList<RecyclerItem> recyclerList = new ArrayList<>();
//        recyclerList.add(new RecyclerItem(R.drawable.ic_add_menu,"Nama Menu","Harga Menu","Deskripsi Menu","Kategori Menu"));
//        recyclerList.add(new RecyclerItem(R.drawable.ic_add_menu,"Nama Menu","Harga Menu","Deskripsi Menu","Kategori Menu"));
//
//
//        mRecyclerView=findViewById(R.id.recyclerView);
//        mAdapter = new RecyclerAdapter(recyclerList);
//        //opsional
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager= new LinearLayoutManager(this);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(MainActivity.URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        API api = retrofit.create(API.class);
//        Call<Value> call = api.hidangan();
//        call.enqueue(new Callback<Value>() {
//                         @Override
//                         public void onResponse(Call<Value> call, Response<Value> response) {
//                             allHidanganList = response.body().getHidangan();
//                             mAdapter = new RecyclerAdapter(AddMenuActivity.this, allHidanganList);
//                         }
//
//                         @Override
//                         public void onFailure(Call<Value> call, Throwable t) {
//
//                         }
//                     }
//
//
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mAdapter);
    }

    public void callAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<Value> call = api.hidangan();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                allHidanganList = response.body().getHidangan();
                setRecycler(allHidanganList);
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(AddMenuActivity.this, "Tidak ada jaringan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRecycler(List<Hidangan> allHidanganList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(layoutManager);
        RecyclerViewHidanganHorizontal adapter = new RecyclerViewHidanganHorizontal(this, allHidanganList);
        rvMenu.setAdapter(adapter);

    }
}
