package com.infowithvijay.triviaquizapp2.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.infowithvijay.triviaquizapp2.constants.Constants;
import com.infowithvijay.triviaquizapp2.R;

public class GameOverScreen extends AppCompatActivity {


    ImageView imgPlayAgain,imgPlayScreen,imgScoreScreen;
    TextView txtTotalScore,txtWrongQues,txtCorrectQues;

    int programingHighScore,motivationHighScore,videoProductionHighScore;

    public static final String SCOREPREFERENCE = "shared_preference";
    public static final String PROGRAMING = "programing_high_score_preference";
    public static final String MOTIVATION = "motivation_high_score_preference";
    public static final String VIDEOPRODUCTION = "video_production_high_score_preference";

    String quizCategory;
    int totalQuestion,wrong,correct,score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover_screen);

        imgPlayAgain = findViewById(R.id.img_Replay);
        imgPlayScreen = findViewById(R.id.img_home);
        imgScoreScreen = findViewById(R.id.img_Score);

        txtCorrectQues = findViewById(R.id.txtRightAnsContent);
        txtWrongQues = findViewById(R.id.txtWrongAnsContent);
        txtTotalScore = findViewById(R.id.txtTotalQues);


        Intent intentContent = getIntent();
        totalQuestion = intentContent.getIntExtra(Constants.TOTAL_QUESTIONS,0);
        score = intentContent.getIntExtra(Constants.SCORE,0);
        correct = intentContent.getIntExtra(Constants.CORRECT,0);
        wrong = intentContent.getIntExtra(Constants.WRONG,0);

        txtTotalScore.setText(Constants.TOTAL_QUESTIONS + totalQuestion);
        txtCorrectQues.setText(String.valueOf(correct));
        txtWrongQues.setText(String.valueOf(wrong));

        quizCategory = intentContent.getStringExtra("Category");


        loadHighScore();


        if (quizCategory.equals("Programing")){

            updateProgramingHighScores(score);

        }else if (quizCategory.equals("VideoProduction")){

            updateVideoProductionHighScores(score);

        }else if (quizCategory.equals("Motivation")){

            updateMotivationHighScores(score);
        }


        imgScoreScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameOverScreen.this,ScoreActivity.class);
                startActivity(intent);
                finish();

            }
        });


        imgPlayScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameOverScreen.this, HomeScreen.class);
                startActivity(intent);
                finish();

            }
        });

        imgPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GameOverScreen.this,QuizActivity2.class);
                intent.putExtra("Category",quizCategory);
                startActivity(intent);
                finish();


            }
        });

    }

    private void updateProgramingHighScores(int scoreToUpdate){

        if(scoreToUpdate > programingHighScore){

         programingHighScore = scoreToUpdate;
         SharedPreferences sharedPreferences = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putInt(PROGRAMING,programingHighScore);
         editor.apply();

        }
    }

    private void updateVideoProductionHighScores(int scoreToUpdate){

        if(scoreToUpdate > videoProductionHighScore){

            videoProductionHighScore = scoreToUpdate;
            SharedPreferences sharedPreferences = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(VIDEOPRODUCTION,videoProductionHighScore);
            editor.apply();

        }

    }

    private void updateMotivationHighScores(int scoreToUpdate){


        if(scoreToUpdate > motivationHighScore){

            motivationHighScore = scoreToUpdate;
            SharedPreferences sharedPreferences = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MOTIVATION,motivationHighScore);
            editor.apply();

        }

    }




    private void loadHighScore() {

        SharedPreferences sharedPreferencesPrograming = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        programingHighScore = sharedPreferencesPrograming.getInt(PROGRAMING,0);
        Log.i("Programing SCORE", " " + programingHighScore);


        SharedPreferences sharedPreferencesVideoProduction = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        videoProductionHighScore = sharedPreferencesVideoProduction.getInt(VIDEOPRODUCTION,0);
        Log.i("Video Production SCORE", " " + videoProductionHighScore);


        SharedPreferences sharedPreferencesVideoMotivation = getSharedPreferences(SCOREPREFERENCE,MODE_PRIVATE);
        motivationHighScore = sharedPreferencesVideoMotivation.getInt(MOTIVATION,0);
        Log.i("Motivation SCORE", " " + motivationHighScore);

    }
}