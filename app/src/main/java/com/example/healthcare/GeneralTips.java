package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GeneralTips extends AppCompatActivity {

    private ImageView SkinCare;
    private ImageView HairCare;
    private ImageView BodyCare;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_tips);

        ImageView weightLoss = findViewById(R.id.weightloss);
        SkinCare = findViewById(R.id.skincare);
        HairCare = findViewById(R.id.haircare);
        BodyCare = findViewById(R.id.bodycare);

        weightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralTips.this,WeightLossActivity.class));
            }
        });
        SkinCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralTips.this,SkinCareActivity.class));
            }
        });
        HairCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralTips.this,HairCareActivity.class));
            }
        });
        BodyCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralTips.this,HealthyBodyActivity.class));
            }
        });
    }
}
