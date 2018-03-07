package com.example.android.gwgquizproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {

    CheckBox throwFacts;
    CheckBox showHint;
    RadioGroup radioGroup;
    String checkedRadioButtonValuevalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mBtLaunchActivity = findViewById(R.id.bt_launch_activity);

        mBtLaunchActivity.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                radioGroup = findViewById(R.id.radioQuizLevel);
                checkedRadioButtonValuevalue = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId( ))).getText( ).toString( );
                launchActivity(checkedRadioButtonValuevalue);
            }
        });

        Button exitQuiz = findViewById(R.id.exitQuiz);

        exitQuiz.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                finish( );
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void launchActivity(String radio) {
        throwFacts = findViewById(R.id.chkThrowFacts);
        boolean isChecked = throwFacts.isChecked( );
        showHint = findViewById(R.id.chkShowHint);
        boolean isHintChecked = showHint.isChecked( );
        EditText numberOfQuestions = findViewById(R.id.numOfQuests);
        int numOfQuestions = 0;
        if(! numberOfQuestions.getText().toString().equals("")){
            numOfQuestions = Integer.parseInt(numberOfQuestions.getText().toString());
        }else
            numOfQuestions = 1000;
        numberOfQuestions.setText("");
        switch (radio) {
            case "Study Mode": {
                Intent intent = new Intent(this, LearnActivity.class);
                if (isChecked) {
                    intent.putExtra("funFacts", true);
                } else {
                    intent.putExtra("funFacts", false);
                }
                startActivity(intent);
                break;
            }
            case "Practice Mode": {
                Intent intent = new Intent(this, PracticeActivity.class);
                if (isHintChecked) {
                    intent.putExtra("showHint", true);
                } else {
                    intent.putExtra("showHint", false);
                }
                startActivity(intent);
                break;
            }
            case "Quiz Mode": {
                //plain_text_NumOfQuestions
                Intent intent = new Intent(this, QuizActivity.class);
                intent.putExtra("questionNumber", numOfQuestions);
                startActivity(intent);
                break;
            }
            default:
                finish( );
                break;
        }
    }
}
