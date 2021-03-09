package com.infowithvijay.triviaquizapp2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infowithvijay.triviaquizapp2.R;

public class ScoreActivity extends AppCompatActivity {

    int programingHighScore,motivationHighScore,videoProductionHighScore;

    TextView txtResultForProgramingS,txtResultForMotivationS,txtResultForVideoProductionS;

    ImageView imgHomeScreen;


    public static final String SCOREPREFERENCE = "shared_preference";
    public static final String PROGRAMING = "programing_high_score_preference";
    public static final String MOTIVATION = "motivation_high_score_preference";
    public static final String VIDEOPRODUCTION = "video_production_high_score_preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtResultForMotivationS = findViewById(R.id.txtResultForMotivationS);
        txtResultForProgramingS = findViewById(R.id.txtResultForProgramingS);
        txtResultForVideoProductionS = findViewById(R.id.txtResultForVideoProductionS);

        imgHomeScreen = findViewById(R.id.img_homeS);

        loadHighScore();

        imgHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, HomeScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void loadHighScore() {

        SharedPreferences sharedPreferencesPrograming = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        programingHighScore = sharedPreferencesPrograming.getInt(PROGRAMING,0);
        Log.i("Programing SCORE", " " + programingHighScore);
        txtResultForProgramingS.setText("" + programingHighScore);


        SharedPreferences sharedPreferencesVideoProduction = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        videoProductionHighScore = sharedPreferencesVideoProduction.getInt(VIDEOPRODUCTION,0);
        Log.i("Video Production SCORE", " " + videoProductionHighScore);
        txtResultForVideoProductionS.setText("" + videoProductionHighScore);


        SharedPreferences sharedPreferencesVideoMotivation = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        motivationHighScore = sharedPreferencesVideoMotivation.getInt(MOTIVATION,0);
        Log.i("Motivation SCORE", " " + motivationHighScore);
        txtResultForMotivationS.setText("" + motivationHighScore);

    }
}