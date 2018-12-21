package com.example.agastyaharta.adminrestoran.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.agastyaharta.adminrestoran.R;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.agastyaharta.adminrestoran.api.API;
import com.example.agastyaharta.adminrestoran.api.Value;
import com.example.agastyaharta.adminrestoran.model.Admin;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    protected TextView tvLogin;
    protected EditText etUsername, etPassword;

    SharedPreferences sharedPreferences;
    private List<Admin> adminList = new ArrayList<>();
    Admin admin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        setContentView(R.layout.activity_login);

        tvLogin=findViewById(R.id.tv_login);


        etUsername=findViewById(R.id.et_username);
        etPassword=findViewById(R.id.et_password);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String inputUsername = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();
//                Toast.makeText(LoginActivity.this, ""+inputUsername +" "+inputPassword, Toast.LENGTH_SHORT).show();
//
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MainActivity.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                API api = retrofit.create(API.class);
                Call<Value> call = api.adminlogin(inputUsername, inputPassword);
                call.enqueue(new Callback<Value>() {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {
                        Boolean success = response.body().getSuccess();
                        if (success){
                            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            adminList = response.body().getAdmin();

                            admin = adminList.get(0);
                            sharedPreferences.edit()
                                    .putString("login","true")
                                    .putString("id",admin.getId_pegawai())
                                    .putString("nama",admin.getNama_pegawai())
                                    .putString("email",admin.getEmail_pegawai())
                                    .putString("username",admin.getUsername_pegawai())
                                    .putString("password",admin.getPassword_pegawai())
                                    .apply();

                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Gagal, password salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                    }
                });

            }
        });

    }
//    @Override
//    public void onClick(View view){
//        switch (view.getId()){
//            case R.id.tv_login:
//                final String inputUsername = etUsername.getText().toString();
//                String inputPassword = etPassword.getText().toString();
//
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(MainActivity.URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                API api = retrofit.create(API.class);
//                Call<Value> call = api.login(inputUsername, inputPassword);
//                call.enqueue(new Callback<Value>() {
//                    @Override
//                    public void onResponse(Call<Value> call, Response<Value> response) {
//                        Boolean success = response.body().getSuccess();
//                        if (success){
//                            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
//                            adminList = response.body().getAdmin();
//
//                            admin = adminList.get(0);
//                            sharedPreferences.edit()
//                                    .putString("login","true")
//                                    .putString("id",admin.getId_pegawai())
//                                    .putString("nama",admin.getNama_pegawai())
//                                    .putString("email",admin.getEmail_pegawai())
//                                    .putString("username",admin.getUsername_pegawai())
//                                    .putString("password",admin.getPassword_pegawai())
//                                    .apply();
//
//                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Login Gagal, password salah", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Value> call, Throwable t) {
//
//                    }
//                });
//
//                break;

      //  }
    //}

}
