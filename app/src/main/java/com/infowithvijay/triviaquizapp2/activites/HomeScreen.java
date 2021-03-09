package com.infowithvijay.triviaquizapp2.activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.infowithvijay.triviaquizapp2.utils.AppController;
import com.infowithvijay.triviaquizapp2.R;
import com.infowithvijay.triviaquizapp2.utils.SettingsPreferences;

import static com.infowithvijay.triviaquizapp2.utils.AppController.StopSound;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    Button btPlayQuiz;
    ImageView imgSettingsScreen,imgScoreScreen;

    public static Context context;

    long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);


        btPlayQuiz = findViewById(R.id.bt_PlayButton);
        imgSettingsScreen = findViewById(R.id.img_settingsH);
        imgScoreScreen = findViewById(R.id.img_scoreH);

        imgSettingsScreen.setOnClickListener(this);
        imgScoreScreen.setOnClickListener(this);
        btPlayQuiz.setOnClickListener(this);

        context = getApplicationContext();
        AppController.currentActivity = this;
        if (SettingsPreferences.getMusicEnableDisable(context)){
            try {

                AppController.playMusic();

            }catch (IllegalStateException e){
                e.printStackTrace();
            }
        }

   }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.bt_PlayButton:

                Intent playIntent = new Intent(HomeScreen.this,CategoryActivity.class);
                startActivity(playIntent);
                finish();
                break;

            case R.id.img_settingsH:
                Intent settingIntent = new Intent(HomeScreen.this,Settings.class);
                startActivity(settingIntent);
                finish();
                break;

            case R.id.img_scoreH:
                Intent scoreIntent = new Intent(HomeScreen.this,ScoreActivity.class);
                startActivity(scoreIntent);
                finish();
                break;

        }

    }

    @Override
    public void onBackPressed() {

        StopSound();

        if (backPressedTime + 2000 > System.currentTimeMillis()){

            new AlertDialog.Builder(this)
                    .setTitle("Do you want to Exit")
                    .setNegativeButton("No",null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            setResult(RESULT_OK,new Intent().putExtra("Exit",true));
                            finish();

                        }
                    }).create().show();

        }else {

            Toast.makeText(context, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();

    }
}
