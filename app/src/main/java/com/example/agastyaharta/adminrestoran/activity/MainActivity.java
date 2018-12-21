package com.example.agastyaharta.adminrestoran.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.example.agastyaharta.adminrestoran.R;


public class MainActivity extends AppCompatActivity {
    public static final String URL ="http://192.168.43.202:8000/";

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);

    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i=0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final  int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Intent intent = new Intent(MainActivity.this, AddMenuActivity.class);
                        startActivity(intent);
                    } else if (finalI == 1) {
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                    }

                }
            });
        }
    }
}
