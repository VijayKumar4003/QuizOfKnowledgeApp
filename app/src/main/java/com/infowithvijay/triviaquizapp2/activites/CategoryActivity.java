package com.infowithvijay.triviaquizapp2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infowithvijay.triviaquizapp2.constants.Constants;
import com.infowithvijay.triviaquizapp2.R;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener{


    Button btMotivation,btVideoPrduction,btTesting,btPrograming,btDigitalMarketing,btAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

     btMotivation = findViewById(R.id.bt_Motivation);
     btVideoPrduction = findViewById(R.id.bt_VideoProduction);
     btAnimation = findViewById(R.id.bt_Animation);
     btDigitalMarketing = findViewById(R.id.bt_DigitalMarketing);
     btPrograming = findViewById(R.id.bt_Programing);
     btTesting = findViewById(R.id.bt_Testing);

        btMotivation.setOnClickListener(this);
        btVideoPrduction.setOnClickListener(this);
        btAnimation.setOnClickListener(this);
        btDigitalMarketing.setOnClickListener(this);
        btPrograming.setOnClickListener(this);
        btTesting.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bt_VideoProduction:  // bt_VideoProduction

                Intent intentVideoProduction = new Intent(CategoryActivity.this,QuizActivity2.class);
                intentVideoProduction.putExtra("Category", Constants.VIDEO_PRODUCTION);
                startActivity(intentVideoProduction);
                finish();
                break;

            case R.id.bt_Programing:  // bt_Programing

                Intent intentPrograming = new Intent(CategoryActivity.this,QuizActivity2.class);
                intentPrograming.putExtra("Category",Constants.PROGRAMING);
                startActivity(intentPrograming);
                finish();
                break;


            case R.id.bt_Motivation:  // bt_Motivation

                Intent intentMotivation = new Intent(CategoryActivity.this,QuizActivity2.class);
                intentMotivation.putExtra("Category",Constants.MOTIVATION);
                startActivity(intentMotivation);
                finish();
                break;


            case R.id.bt_Animation:  // bt_Animation

                Intent intentAnimation = new Intent(CategoryActivity.this,QuizActivity2.class);
                intentAnimation.putExtra("Category",Constants.ANIMATION);
                startActivity(intentAnimation);
                finish();
                break;

            case R.id.bt_Testing:  // bt_Testing
                Intent intentTesting = new Intent(CategoryActivity.this,QuizActivity2.class);
                intentTesting.putExtra("Category",Constants.TESTING);
                startActivity(intentTesting);
                finish();
                break;

            case R.id.bt_DigitalMarketing:  // bt_DigitalMarketing
                Intent intenDigitalMarketing = new Intent(CategoryActivity.this,QuizActivity2.class);
                intenDigitalMarketing.putExtra("Category",Constants.DIGITALMARKETING);
                startActivity(intenDigitalMarketing);
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CategoryActivity.this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

}