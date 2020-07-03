package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SkinCareActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_care);

        BottomNavigationView bottomNav = findViewById(R.id.skintopnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("Skin Care");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.pimples:
                    selectedFragment = new pimplesfragment();
                    break;
                case R.id.itching:
                    selectedFragment = new itchingfragment();
                    break;
                case R.id.facecare:
                    selectedFragment = new facecarefragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.skinfragmentcontainer,selectedFragment).commit();
            return true;
        }
    };
}
