package com.example.android.gwgquizproject;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

public class PracticeActivity extends AppCompatActivity {
    String pnames[];
    String pnumber[];
    String pyears[];
    TextView Question;
    String Answer;
    RadioButton AnswerChoice1;
    RadioButton AnswerChoice2;
    RadioButton AnswerChoice3;
    RadioButton AnswerChoice4;
    RadioButton AnswerChoice5;
    List<Integer> mListOfInt;
    int score = 0, tally = 0, hint;
    private RadioGroup radioGroup;
    final Integer[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                               17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                   31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
    int count = 1;
    private static final String TAG = "MyActivity";
    private Button mBtGoBack;
    private Button myLauncher;
    boolean showHint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intentExtras = getIntent();
        showHint =    intentExtras.getBooleanExtra("showHint", false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        mBtGoBack = findViewById(R.id.bt_go_back);
        mListOfInt = new ArrayList<Integer>(Arrays.asList(order));
        Collections.shuffle(mListOfInt);
        setQuestion( );
        mBtGoBack.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext( ), "\nScore: " + " " + score + "/" + tally, Toast.LENGTH_LONG).show( );
                finish( );
            }
        });
        myLauncher = findViewById(R.id.showHint);
        if(showHint) {

            myLauncher.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View view) {

                    String hintAnswer = pyears[hint];
                    Toast.makeText(getApplicationContext( ), "+Hint: \nserved: " + hintAnswer, Toast.LENGTH_SHORT).show( );
                }
            });
        }else{
            myLauncher.setText("Hint is Disabled!");
        }
    }

    public void setQuestion() {
        pnames = getResources( ).getStringArray(R.array.president_name);
        pnumber = getResources( ).getStringArray(R.array.president_number);
        pyears = getResources( ).getStringArray(R.array.president_years_of_service);
        Question = findViewById(R.id.question);
        AnswerChoice1 = findViewById(R.id.answerA);
        AnswerChoice2 = findViewById(R.id.answerB);
        AnswerChoice3 = findViewById(R.id.answerC);
        AnswerChoice4 = findViewById(R.id.answerD);
        AnswerChoice5 = findViewById(R.id.answerE);
        final List<Integer> mListOfInt = new ArrayList<Integer>(Arrays.asList(order));
        Collections.shuffle(mListOfInt);
        Integer[] order2 = {0, 1, 2, 3, 4};
        final List<Integer> miniListOfInt = new ArrayList<Integer>(Arrays.asList(order2));
        Collections.shuffle(miniListOfInt);
        hint = mListOfInt.get(0);
        Answer = pnames[mListOfInt.get(0)];
        Question.setText(count + ". Who was the " + pnumber[mListOfInt.get(0)] + " US President?");
        AnswerChoice1.setText("A: " + pnames[mListOfInt.get(miniListOfInt.get(0))]);
        AnswerChoice1.setChecked(false);
        AnswerChoice2.setText("B: " + pnames[mListOfInt.get(miniListOfInt.get(1))]);
        AnswerChoice2.setChecked(false);
        AnswerChoice3.setText("C: " + pnames[mListOfInt.get(miniListOfInt.get(2))]);
        AnswerChoice3.setChecked(false);
        AnswerChoice4.setText("D: " + pnames[mListOfInt.get(miniListOfInt.get(3))]);
        AnswerChoice4.setChecked(false);
        AnswerChoice5.setText("E: " + pnames[mListOfInt.get(miniListOfInt.get(4))]);
        AnswerChoice5.setChecked(false);
        count += 1;
    }

    public void checkAnswer(View view) {
        AnswerChoice1 = findViewById(R.id.answerA);
        AnswerChoice2 = findViewById(R.id.answerB);
        AnswerChoice3 = findViewById(R.id.answerC);
        AnswerChoice4 = findViewById(R.id.answerD);
        AnswerChoice5 = findViewById(R.id.answerE);
        radioGroup = (RadioGroup) findViewById(R.id.radioQuiz);
        tally += 1;
        String value = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId( )))
                .getText( ).toString( );
        if (value.contains(Answer)) {
            score += 1;
            //Toast.makeText(getApplicationContext( ), "Correct!", Toast.LENGTH_SHORT).show( );
        } else {
            Toast.makeText(getApplicationContext( ), "-Opps! The " + pnumber[hint] + " president was:\n\t\t" + Answer, Toast.LENGTH_LONG).show( );
        }
        setQuestion( );
    }
}
