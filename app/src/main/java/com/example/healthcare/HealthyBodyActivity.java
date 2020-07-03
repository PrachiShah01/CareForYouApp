package com.example.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthyBodyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_body);

        BottomNavigationView bottomNav= findViewById(R.id.bodytopnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setTitle("Body Care");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()){
                case R.id.jointpain:
                    selectedFragment = new jointpainfragment();
                    break;
                case R.id.backpain:
                    selectedFragment = new backpainfragment();
                    break;
                case R.id.coldcough:
                    selectedFragment = new coldcoughfragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.bodyfragmentcontainer,selectedFragment).commit();
            return true;
        }
    };
}
