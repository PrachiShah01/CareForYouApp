package com.example.healthcare;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private FirebaseAuth firebaseAuth;
    private ImageView Generaltip, BMI, Nearby;
    private ImageView Reminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState);

        Generaltip = findViewById(R.id.generalTips);
        Reminder = findViewById(R.id.reminder);
        BMI = findViewById(R.id.bmi);
        Nearby = findViewById(R.id.nearby);

        BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,BmiCalculator.class));
            }
        });

        Nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,MapsActivity.class));
            }
        });

        Generaltip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,GeneralTips.class));
            }
        });

        Reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,Reminder.class));
            }
        });


        ImageSlider imageSlider = findViewById(R.id.imageslider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://www.lifestylebyte.com/wp-content/uploads/2017/11/Three-Ways-to-Have-a-Good-General-Healthy-body.jpg"));
        slideModels.add(new SlideModel("https://qph.fs.quoracdn.net/main-qimg-987ad86dcca849c9a184ac2c57061aa3"));
        slideModels.add(new SlideModel("https://eventsyard.com/wp-content/uploads/2019/03/banner-Health.jpg"));
        imageSlider.setImageList(slideModels,true);

        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView)findViewById(R.id.navidrawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.profile){
                    startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
                }else if(id == R.id.generalTips){
                    startActivity(new Intent(HomeActivity.this,GeneralTips.class));
                }else if(id == R.id.reminder){
                    startActivity(new Intent(HomeActivity.this,Reminder.class));
                }else if(id == R.id.home){
                    startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                }else if(id==R.id.logout){
                    Logout();
                    Toast.makeText(HomeActivity.this,"Logout",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(HomeActivity.this,loginActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
