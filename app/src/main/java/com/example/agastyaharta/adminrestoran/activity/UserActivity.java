package com.example.agastyaharta.adminrestoran.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.agastyaharta.adminrestoran.R;

public class UserActivity extends AppCompatActivity {
    protected TextView tvLogin, tvUser, tvLogout, tvEmail, tvUsername;
    protected LinearLayout llNotLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

//        Activity view = new Activity();
//        tvUser = view.findViewById(R.id.tv_nama_user);
//        tvLogout = view.findViewById(R.id.tv_logout);
//        tvEmail = view.findViewById(R.id.tv_email_user);
//        tvUsername = view.findViewById(R.id.tv_username_user);
//        llNotLogin = view.findViewById(R.id.ll_not_login);
    }
}
