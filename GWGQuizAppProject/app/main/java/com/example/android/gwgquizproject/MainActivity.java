package com.example.android.gwgquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button mBtLaunchActivity;
    private Button exitQuiz;
    RadioGroup rg;
    String value;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtLaunchActivity = (Button) findViewById(R.id.bt_launch_activity);

        mBtLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rg = (RadioGroup) findViewById(R.id.radioQuizLevel);
                value =
                        ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))
                                .getText().toString();
                Log.i(TAG, "Value is: =================================== " + value);
                launchActivity(value);
            }
        });

        exitQuiz = findViewById(R.id.exitQuiz);

        exitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }

    private void launchActivity(String radio) {
        if(radio.equals("Practice")) {
            Intent intent = new Intent(this, practice.class);
            startActivity(intent);
        } else if (radio.equals("Beginner")) {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        }else if (radio.equals("Intermediate")) {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        }else if (radio.equals("Advanced")) {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
        }else {
            finish();
        }

    }
}
