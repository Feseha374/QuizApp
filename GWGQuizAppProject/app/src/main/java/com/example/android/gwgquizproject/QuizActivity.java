package com.example.android.gwgquizproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.view.View.VISIBLE;

public class QuizActivity extends AppCompatActivity {
    String presidentNames[];
    String presidentNumber[];
    String presidentServiceYears[];
    TextView Question;
    String Answer;
    RadioButton AnswerChoice1;
    RadioButton AnswerChoice2;
    RadioButton AnswerChoice3;
    RadioButton AnswerChoice4;
    RadioButton AnswerChoice5;
    EditText fillInTheBlank;
    Button buttonGoBack;
    int numOfQuestions;
    int flagNumOfQuestions=0;
    int score = 0, tally = 0, hint;
    CountDownTimer countDownTimer;
    TextView myCounter;
    final Integer[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                               17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                   31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
    int count = 0, check = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intentExtras = getIntent();
        numOfQuestions =  intentExtras.getIntExtra("questionNumber", 0);
        flagNumOfQuestions = numOfQuestions;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buttonGoBack = findViewById(R.id.bt_go_back);
        setQuestion( );
        buttonGoBack.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                if(buttonGoBack.getText().equals("End Quiz")) {
                    showScore();
                    finish();
                }else{
                    checkAnswer(view);
                    buttonGoBack.setText("End Quiz");
                    setQuestion();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setQuestion() {
        count ++;
        fillInTheBlank = findViewById(R.id.fillInTheBlank);
        if(check % 6 == 0){
            if (countDownTimer != null) {
                countDownTimer.cancel( );
                countDownTimer = null;
            }
            final ArrayList<Integer> mListOfInt = new ArrayList<>(Arrays.asList(order));
            Collections.shuffle(mListOfInt);
            Integer[] order2 = {0, 1, 2, 3, 4};
            final List<Integer> miniListOfInt = new ArrayList<>(Arrays.asList(order2));
            Collections.shuffle(miniListOfInt);
            hint = mListOfInt.get(0);
            Answer = presidentNames[mListOfInt.get(0)];
            Question.setText(count + ". Who was the " + presidentNumber[mListOfInt.get(0)] + " US President?");
            AnswerChoice1.setVisibility(View.GONE);
            AnswerChoice2.setVisibility(View.GONE);
            AnswerChoice3.setVisibility(View.GONE);
            AnswerChoice4.setVisibility(View.GONE);
            AnswerChoice5.setVisibility(View.GONE);
            fillInTheBlank.setVisibility(VISIBLE);
            fillInTheBlank.setHint("Full or Last name");
            buttonGoBack.setText("Next Question");
            myCounter.setText("Take Your Time!");
            count--;
            check++;
        }else {
            startTimer( );
            fillInTheBlank.setVisibility(View.INVISIBLE);

            presidentNames = getResources().getStringArray(R.array.president_name);
            presidentNumber = getResources().getStringArray(R.array.president_number);
            presidentServiceYears = getResources().getStringArray(R.array.president_years_of_service);
            Question = findViewById(R.id.question);
            AnswerChoice1 = findViewById(R.id.answerA);
            AnswerChoice2 = findViewById(R.id.answerB);
            AnswerChoice3 = findViewById(R.id.answerC);
            AnswerChoice4 = findViewById(R.id.answerD);
            AnswerChoice5 = findViewById(R.id.answerE);
            AnswerChoice1.setVisibility(View.VISIBLE);
            AnswerChoice2.setVisibility(View.VISIBLE);
            AnswerChoice3.setVisibility(View.VISIBLE);
            AnswerChoice4.setVisibility(View.VISIBLE);
            AnswerChoice5.setVisibility(View.VISIBLE);

            final ArrayList<Integer> mListOfInt = new ArrayList<>(Arrays.asList(order));
            Collections.shuffle(mListOfInt);
            Integer[] order2 = {0, 1, 2, 3, 4};
            final List<Integer> miniListOfInt = new ArrayList<>(Arrays.asList(order2));
            Collections.shuffle(miniListOfInt);
            hint = mListOfInt.get(0);
            Answer = presidentNames[mListOfInt.get(0)];
            Question.setText(count + ". Who was the " + presidentNumber[mListOfInt.get(0)] + " US President?");
            AnswerChoice1.setText("A: " + presidentNames[mListOfInt.get(miniListOfInt.get(0))]);
            AnswerChoice1.setChecked(false);
            AnswerChoice2.setText("B: " + presidentNames[mListOfInt.get(miniListOfInt.get(1))]);
            AnswerChoice2.setChecked(false);
            AnswerChoice3.setText("C: " + presidentNames[mListOfInt.get(miniListOfInt.get(2))]);
            AnswerChoice3.setChecked(false);
            AnswerChoice4.setText("D: " + presidentNames[mListOfInt.get(miniListOfInt.get(3))]);
            AnswerChoice4.setChecked(false);
            AnswerChoice5.setText("E: " + presidentNames[mListOfInt.get(miniListOfInt.get(4))]);
            AnswerChoice5.setChecked(false);
        }

        check ++;
        if ((numOfQuestions == 0) && (flagNumOfQuestions > 0)){
            showScore();
        }
    }
    public void showScore(){
        Toast toast = Toast.makeText(this, "\nScore: " + " " + score + "/" + tally, Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    public void checkAnswer(View view) {
        RadioGroup radioGroup = findViewById(R.id.radioQuiz);
        EditText blankx = (EditText) findViewById(R.id.fillInTheBlank);
        String blank = blankx.getText().toString();
        String value="";
        if (! blank.equals("")) {
            value = blank;
        }
        else {
            value = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId( ))).getText( ).toString( );
        }

        if (value.contains(Answer) || Answer.contains(value)) {
            score ++;
            tally ++;
            //Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show( );
        } else {
           // Toast.makeText(this, "-Opps! The " + pnumber[hint] + " president was:\n\countDownTimer" + Answer, Toast.LENGTH_SHORT).show( );
            tally ++;
        }

        numOfQuestions -= 1;
        //startTimer();
        blankx.setText("");
        setQuestion( );
    }
    public void startTimer(){
        //cancel the old countDownTimer
       myCounter = findViewById(R.id.bt_timer);
        if (countDownTimer != null) {
            countDownTimer.cancel( );
        }
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onFinish() {
                tally++;
                setQuestion( );
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                myCounter.setText("Seconds Until Next Question: "
                        + String.valueOf((int) (millisUntilFinished / 1000)));
            }
        };
        countDownTimer.start( );
    }
}

