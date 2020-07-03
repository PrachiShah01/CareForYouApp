package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WeightLossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_loss);

        BottomNavigationView bottomNav= findViewById(R.id.topnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("WeightLoss");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.tip1:
                    selectedFragment = new tip1fragment();
                    break;
                case R.id.tip2:
                    selectedFragment = new tip2fragment();
                    break;
                case R.id.tip3:
                    selectedFragment = new tip3fragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,selectedFragment).commit();
            return true;
        }
    };
}
