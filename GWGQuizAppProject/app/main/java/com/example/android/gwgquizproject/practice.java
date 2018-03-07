package com.example.android.gwgquizproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class practice extends AppCompatActivity {
    String pnames[];
    String pnumber[];
    String pyears[];
    String pparty[];
    String pfacts[];
    TextView Q;
    TextView A1, A2, A3, A4;
    final Integer[] order = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                               17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                   31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
    List<Integer> mListOfInt;

    int count = 1;
    private static final String TAG = "MyActivity";
    private Button mBtGoBack;
    private Button myLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mListOfInt = new ArrayList<Integer>(Arrays.asList(order));
        Collections.shuffle(mListOfInt);
        mBtGoBack = findViewById(R.id.bt_go_back);
        practiceRun();
        mBtGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        myLauncher = findViewById(R.id.bt_launch_activity);

        myLauncher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                practiceRun();
            }
        });
    }

    public void practiceRun(){
        pnames = getResources().getStringArray(R.array.president_name);
        pnumber = getResources().getStringArray(R.array.president_number);
        pyears = getResources().getStringArray(R.array.president_years_of_service);
        pparty = getResources().getStringArray(R.array.presidents_party);
        pfacts = getResources().getStringArray(R.array.presidents_facts);
        Q = findViewById(R.id.question);
        A1 = findViewById(R.id.answerA);
        A2 = findViewById(R.id.answerB);
        A3 = findViewById(R.id.answerC);
        A4 = findViewById(R.id.answerE);
        int index = count % 44 - 1 ;
        Q.setText(count + ". Who was the " + pnumber[mListOfInt.get(index)] + " US President?");
        A1.setText( pnames[mListOfInt.get(index)]);
        A2.setText("Served:  " + pyears[mListOfInt.get(index)]);
        A3.setText("Party:      " + pparty[mListOfInt.get(index)]);
        A4.setText(pfacts[mListOfInt.get(index)] );

        count += 1;
    }
}
