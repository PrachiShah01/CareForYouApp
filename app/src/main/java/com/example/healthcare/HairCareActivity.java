package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HairCareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_care);

        BottomNavigationView bottomNav= findViewById(R.id.hairtopnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("Hair Care");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.hairloss:
                    selectedFragment = new hairlossfragment();
                    break;
                case R.id.dandruff:
                    selectedFragment = new dandrufffragment();
                    break;
                case R.id.hairitching:
                    selectedFragment = new hairitchingfragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.hairfragmentcontainer,selectedFragment).commit();
            return true;
        }
    };
}
