package com.example.agastyaharta.adminrestoran.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.agastyaharta.adminrestoran.R;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class AddMenuActivity extends AppCompatActivity {
    FloatingTextButton fabkiri, fabkanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        fabkiri = (FloatingTextButton) findViewById(R.id.fab2);
        fabkanan = (FloatingTextButton) findViewById(R.id.fab1);

        fabkiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMenuActivity.this, InsertMenuActivity.class));
            }
        });

        fabkanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddMenuActivity.this, EditMenuActivity.class));
            }
        });



    }
}
