/*
Math Trainer Practice
source: https://www.udemy.com/course/the-complete-android-oreo-developer-course/learn/lecture/8339450#overview

Description:


-Implemented Grid Layout
-
-Uses buttons:GO button, Answer Buttons: 0-3
-Created TextView for Timer and Scores, along with displaying the problem
-Utiilize tags for buttons as answers
 */

package com.example.mathtrainer;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView problemTextView;

    //Generates random equations from 0 to 30//24:20 time stamp==================================
    public void generateProblem() {
        Random rand = new Random();
        int a = rand.nextInt(31);
        int b = rand.nextInt(31);
        problemTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
    }


    //Go button to start the trainer
    public void go(View view) {
        goButton.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Generates random equation using problemTextView
        problemTextView = (TextView) findViewById(R.id.problemTextView);
        generateProblem();


        //Go button to start the trainer
        goButton = (Button) findViewById(R.id.goButton);

    }
}