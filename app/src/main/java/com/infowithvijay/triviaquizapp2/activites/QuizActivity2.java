package com.infowithvijay.triviaquizapp2.activites;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.infowithvijay.triviaquizapp2.constants.Constants;
import com.infowithvijay.triviaquizapp2.utils.PlayAudio;
import com.infowithvijay.triviaquizapp2.R;
import com.infowithvijay.triviaquizapp2.dialogs.TimerDialog;
import com.infowithvijay.triviaquizapp2.data.TriviaQuestion;
import com.infowithvijay.triviaquizapp2.db.TriviaQuizHelper;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity2 extends AppCompatActivity {


    Button buttonA,buttonB,buttonC,buttonD;
    TextView questionText,txtCategoryHeading,timeText;

    TriviaQuizHelper triviaQuizHelper;

    TriviaQuestion currentQuestion;

    List<TriviaQuestion> list;

    int qid = 1;

    int sizeofQuiz = 6; // total size of Quiz

    private final Handler handler = new Handler();
    private final Handler handler2 = new Handler();

    AnimationDrawable anim;


    ImageView imgLifeline1;
    ImageView imgLifeline2;
    ImageView imgLifeline3;


    int lifeLineVal = 3;


    private static final long COUNTDOWN_IN_MILLIS = 32000;

    private CountDownTimer countDownTimer;

    private long timeLeftMillis;

    long savedTime =0;


    private TimerDialog timerDialog;

    int correct=0;
    int wrong = 0;
    int score = 0;

    Animation correctAnsAnimation;
    Animation wrongAnsAnimation;

    int FLAG = -1;
    PlayAudio playAudio;

    String categoryValue;

    long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        questionText = findViewById(R.id.txtTriviaQuestion);
        txtCategoryHeading = findViewById(R.id.txtCategoryHeading);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        timeText = findViewById(R.id.txtTimer);

        imgLifeline1 = findViewById(R.id.imgLifeLine1);
        imgLifeline2 = findViewById(R.id.imgLifeLine2);
        imgLifeline3 = findViewById(R.id.imgLifeLine3);




        Intent intentCategory = getIntent();
        categoryValue = intentCategory.getStringExtra("Category");

        txtCategoryHeading.setText(categoryValue);

        timerDialog = new TimerDialog(this);

        correctAnsAnimation = AnimationUtils.loadAnimation(this,R.anim.correct_ans_animation);
        correctAnsAnimation.setRepeatCount(3);

        wrongAnsAnimation = AnimationUtils.loadAnimation(this,R.anim.wrong_ans_animation);
        wrongAnsAnimation.setRepeatCount(3);

        playAudio = new PlayAudio(this);

        triviaQuizHelper = new TriviaQuizHelper(this);

        triviaQuizHelper.getReadableDatabase();

        list = triviaQuizHelper.getQuestionsWithCategory(categoryValue);

        Collections.shuffle(list);

        currentQuestion = list.get(qid);





        updateQueAnsOptions();


    }

    private void updateQueAnsOptions() {

        buttonA.setBackgroundResource(R.drawable.default_options_bg);
        buttonB.setBackgroundResource(R.drawable.default_options_bg);
        buttonC.setBackgroundResource(R.drawable.default_options_bg);
        buttonD.setBackgroundResource(R.drawable.default_options_bg);

        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOption1());
        buttonB.setText(currentQuestion.getOption2());
        buttonC.setText(currentQuestion.getOption3());
        buttonD.setText(currentQuestion.getOption4());


        timeLeftMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

    }


    private void SetNewQuestion(){

        qid++;

        currentQuestion = list.get(qid);

        enableOptions();

        updateQueAnsOptions();
    }


    void healthFunctionality(){


        if (wrong == 1){
            imgLifeline3.setVisibility(View.INVISIBLE);
            lifeLineVal--;
        }else if (wrong == 2){
            imgLifeline2.setVisibility(View.INVISIBLE);
            lifeLineVal--;
        }else if (wrong == 3){
            imgLifeline1.setVisibility(View.INVISIBLE);
            lifeLineVal--;
        }

        if (lifeLineVal == 0){
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();

            Handler delay = new Handler();
            delay.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent intent = new Intent(QuizActivity2.this,ScoreActivity.class);
                    startActivity(intent);

                }
            },1000);
        }


    }

    public void buttonA(View view) {

        countDownTimer.cancel();

        disableOptions();

        buttonA.setBackgroundResource(R.drawable.flash_background);
        anim = (AnimationDrawable) buttonA.getBackground();
        anim.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){

                    buttonA.setBackgroundResource(R.drawable.correct_green_options_bg);
                    buttonA.startAnimation(correctAnsAnimation);
                    correct++;


                    FLAG = 1;
                    playAudio.setAudioforEvent(FLAG);

                    score = score + 10;


                    Log.i("QuizInfo","Correct");

                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {

                                finalResult();
                            }


                        }
                    },2000);
                }else {

                    buttonA.setBackgroundResource(R.drawable.wrong_red_options_bg);
                    buttonA.startAnimation(wrongAnsAnimation);
                    wrong++;

                    healthFunctionality();

                    FLAG = 2;
                    playAudio.setAudioforEvent(FLAG);
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                                buttonB.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                                buttonC.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else {
                                buttonD.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }
                        }
                    },2000);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }
                        }
                    },3000);


                }


            }
        },5000);

    }

    public void buttonB(View view) {

      countDownTimer.cancel();

        disableOptions();
        buttonB.setBackgroundResource(R.drawable.flash_background);
        anim = (AnimationDrawable) buttonB.getBackground();
        anim.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){

                    buttonB.setBackgroundResource(R.drawable.correct_green_options_bg);
                    buttonB.startAnimation(correctAnsAnimation);
                    correct++;

                    FLAG = 1;
                    playAudio.setAudioforEvent(FLAG);
                    score = score + 10;


                    Log.i("QuizInfo","Correct");

                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }


                        }
                    },2000);
                }else {

                    buttonB.setBackgroundResource(R.drawable.wrong_red_options_bg);
                    buttonB.startAnimation(wrongAnsAnimation);
                    wrong++;

                    healthFunctionality();

                    FLAG = 2;
                    playAudio.setAudioforEvent(FLAG);
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){
                                buttonA.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                                buttonC.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else {
                                buttonD.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }
                        }
                    },2000);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }
                        }
                    },3000);


                }


            }
        },5000);


    }

    public void buttonC(View view) {

        countDownTimer.cancel();

        disableOptions();
        buttonC.setBackgroundResource(R.drawable.flash_background);
        anim = (AnimationDrawable) buttonC.getBackground();
        anim.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){

                    buttonC.setBackgroundResource(R.drawable.correct_green_options_bg);
                    buttonC.startAnimation(correctAnsAnimation);
                    correct++;

                    FLAG = 1;
                    playAudio.setAudioforEvent(FLAG);
                    score = score + 10;


                    Log.i("QuizInfo","Correct");

                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }


                        }
                    },2000);
                }else {

                    buttonC.setBackgroundResource(R.drawable.wrong_red_options_bg);
                    buttonC.startAnimation(wrongAnsAnimation);
                    wrong++;

                    healthFunctionality();

                    FLAG = 2;
                    playAudio.setAudioforEvent(FLAG);
                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                                buttonB.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else if (currentQuestion.getOption1().equals(currentQuestion.getAnswerNr())){
                                buttonA.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else {
                                buttonD.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }
                        }
                    },2000);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                               finalResult();
                            }
                        }
                    },3000);


                }


            }
        },5000);

    }

    public void buttonD(View view) {

        countDownTimer.cancel();
        disableOptions();
        buttonD.setBackgroundResource(R.drawable.flash_background);
        anim = (AnimationDrawable) buttonD.getBackground();
        anim.start();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (currentQuestion.getOption4().equals(currentQuestion.getAnswerNr())){

                    buttonD.setBackgroundResource(R.drawable.correct_green_options_bg);
                    buttonD.startAnimation(correctAnsAnimation);
                    correct++;

                    FLAG = 1;
                    playAudio.setAudioforEvent(FLAG);
                    score = score + 10;


                    Log.i("QuizInfo","Correct");

                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }


                        }
                    },2000);
                }else {

                    buttonD.setBackgroundResource(R.drawable.wrong_red_options_bg);
                    buttonD.startAnimation(wrongAnsAnimation);
                    wrong++;

                    healthFunctionality();

                    FLAG = 2;
                    playAudio.setAudioforEvent(FLAG);

                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(currentQuestion.getOption2().equals(currentQuestion.getAnswerNr())){
                                buttonB.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else if (currentQuestion.getOption3().equals(currentQuestion.getAnswerNr())){
                                buttonC.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }else {
                                buttonA.setBackgroundResource(R.drawable.correct_green_options_bg);
                            }
                        }
                    },2000);

                    Handler handler4 = new Handler();
                    handler4.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (qid != sizeofQuiz){

                                SetNewQuestion();

                            }else {
                                finalResult();
                            }
                        }
                    },3000);


                }


            }
        },5000);

    }


    @Override
    protected void onResume() {
        super.onResume();
        countDownTimer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
       countDownTimer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
        finish();
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(QuizActivity2.this,CategoryActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();


    }

   private void disableOptions(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

   }

   private void enableOptions(){
       buttonA.setEnabled(true);
       buttonB.setEnabled(true);
       buttonC.setEnabled(true);
       buttonD.setEnabled(true);

   }


   private void finalResult(){

        Intent resultIntent = new Intent(QuizActivity2.this,GameOverScreen.class);
        resultIntent.putExtra(Constants.TOTAL_QUESTIONS,sizeofQuiz);
        resultIntent.putExtra(Constants.SCORE,score);
        resultIntent.putExtra(Constants.WRONG,wrong);
        resultIntent.putExtra(Constants.CORRECT,correct);
        resultIntent.putExtra("Category",categoryValue);
        startActivity(resultIntent);
        finish();


   }


   private void startCountDown(){

        countDownTimer = new CountDownTimer(timeLeftMillis,1000) {
            @Override
            public void onTick(long millsUnilFinished) {
                timeLeftMillis = millsUnilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftMillis = 0;
                updateCountDownText();

            }
        }.start();


   }


    private void updateCountDownText(){


        int seconds = (int) (timeLeftMillis/1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d",seconds);
        savedTime = Long.parseLong(timeFormatted);
        timeText.setText(timeFormatted);

        if (timeLeftMillis < 10000){

            timeText.setTextColor(ContextCompat.getColor(this,R.color.red));

        }else {

            timeText.setTextColor(ContextCompat.getColor(this,R.color.black));

        }

        if (timeLeftMillis == 0){

            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    timerDialog.timerDialog();

                }
            },2000);

        }

    }




}
