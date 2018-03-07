package com.example.android.gwgquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LearnActivity extends AppCompatActivity {
    String pnames[];
    String pnumber[];
    String pyears[];
    String pparty[];
    String pfacts[];
    TextView Question;
    TextView AnswerChoice1, AnswerChoice2, AnswerChoice3, AnswerChoice4;
    List<Integer> mListOfInt;
    Boolean funfacts;
    final Integer[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
            17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
    int count = 1;

    private Button buttonGoBack;
    private Button buttonLauncher;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        Intent intentExtras = getIntent();
        funfacts =    intentExtras.getBooleanExtra("funFacts", false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        buttonGoBack = findViewById(R.id.bt_go_back);
        mListOfInt = new ArrayList<Integer>(Arrays.asList(order));
        Collections.shuffle(mListOfInt);
        easyTest();
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 1;
                finish();
            }
        });

        buttonLauncher = findViewById(R.id.bt_launch_activity);

        buttonLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                easyTest();
            }
        });
    }

    public void easyTest(){
        pnames = getResources().getStringArray(R.array.president_name);
        pnumber = getResources().getStringArray(R.array.president_number);
        pyears = getResources().getStringArray(R.array.president_years_of_service);
        pparty = getResources().getStringArray(R.array.presidents_party);
        pfacts = getResources().getStringArray(R.array.presidents_facts);
        Question = findViewById(R.id.question);
        AnswerChoice1 = findViewById(R.id.answerA);
        AnswerChoice2 = findViewById(R.id.answerB);
        AnswerChoice3 = findViewById(R.id.answerC);
        AnswerChoice4 = findViewById(R.id.answerD);
        int index = count %44;
        Question.setText(count + ". Who was the " + pnumber[mListOfInt.get(index)] + " US President?\n");
        AnswerChoice1.setText("  Name: " + pnames[mListOfInt.get(index)]);
        AnswerChoice2.setText("Served: " + pyears[mListOfInt.get(index)]);
        AnswerChoice3.setText("   Party: " + pparty[mListOfInt.get(index)]);
        if (funfacts)
            AnswerChoice4.setText("\nFun Fact: \n" + pfacts[mListOfInt.get(index)]);
        count++;
    }
}
