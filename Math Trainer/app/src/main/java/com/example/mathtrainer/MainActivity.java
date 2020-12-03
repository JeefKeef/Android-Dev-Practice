/*
Math Trainer Practice
source: https://www.udemy.com/course/the-complete-android-oreo-developer-course/learn/lecture/8339450#overview

Description:
Simple Addition Math game that generates random numbers from 0-30 and random addition problems.
The score updates everytime the user gets a right answer or wrong answer.
Answer the most amount of questions in 30 seconds.


-Implemented Second constraint Layout to hide UI instead of coding multiple visible code
-Implemented Grid Layout
-Designed reuseable methods compared to udemy course code
-Uses buttons:GO button, Answer Buttons: 0-3
-Created TextView for Timer and Scores, along with displaying the problem
-Utiilize tags for buttons as answers
-Implemented CountDownTimer
 */

package com.example.mathtrainer;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button goButton;
    Button answerButton0;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;
    Button playAgainButton;
    TextView problemTextView;
    TextView correctTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int correctAnswer;
    int wrongAnswer;
    int locationOfAnswer;//In array
    int score = 0;
    int numOfQuestions = 0;
    ConstraintLayout newConstraintLayout;


    public void hideLayout() {
        goButton.setVisibility(View.VISIBLE);
        newConstraintLayout.setVisibility(View.INVISIBLE);
    }

    //Play again button that resets the game scores and generate new questions
    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        timer();
        newQuestions();
        playAgainButton.setVisibility(View.INVISIBLE);
        correctTextView.setText("");
    }

    //Count Down Timer
    public void timer() {
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                correctTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    //Generates new questions and answers and clears answer array
    public void newQuestions() {
        answers.clear();
        generateProblem();
        generateAnswers();
        generateMultipleChoicesButtons();
    }

    //Choose answers and updates the score
    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())) {
            correctTextView.setText("CORRECT!");
            ++score;
        }
        else {
            correctTextView.setText("WRONG!");
        }
        ++numOfQuestions;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numOfQuestions));
        newQuestions();
    }

    //Generate answer buttons and set Text of each buttons to random wrongAnswers.
    public void generateMultipleChoicesButtons() {
        answerButton0.setText(Integer.toString(answers.get(0)));
        answerButton1.setText(Integer.toString(answers.get(1)));
        answerButton2.setText(Integer.toString(answers.get(2)));
        answerButton3.setText(Integer.toString(answers.get(3)));
    }

    //Generate random answers and set the correct answer
    public void generateAnswers() {
        Random rand = new Random();
        locationOfAnswer = rand.nextInt(4);//sets random location for correct answer

        //Loops through choices size(4), and adds the correct answer into random i, and fill the rest of the array with random incorrect answers
        for(int i = 0; i < 4; ++i) {
            if(i == locationOfAnswer) {//adds answer to array at i position
                answers.add(correctAnswer);
            }
            else {
                //fills array up with random numbers, if the wrong answer and correct answer are the same, randomize the wronganswer until it is different than the correct answer
                wrongAnswer = rand.nextInt(61);
                while(wrongAnswer == correctAnswer) {
                    wrongAnswer = rand.nextInt(61);
                }
                answers.add(wrongAnswer);
            }
        }
    }

    //Generates random equations from 0 to 30
    public void generateProblem() {
        Random rand = new Random();
        int a = rand.nextInt(31);
        int b = rand.nextInt(31);
        correctAnswer = a+b;
        problemTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
    }


    //Go button to start the trainer
    public void go(View view) {
        //Sets CountDownTimer starting at 30 seconds and updating in 1 seconds intervals
        timer();
        goButton.setVisibility(View.INVISIBLE);
        newConstraintLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout that sets unneccesary UI to invisible and visible
        newConstraintLayout = (ConstraintLayout) findViewById(R.id.newConstrainLayout);

        //Timer TextView
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        //Score TextView
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        //Correct TextView if correct answer is chosen.
        correctTextView = (TextView) findViewById(R.id.correctTextView);

        //Generates random equation using problemTextView
        problemTextView = (TextView) findViewById(R.id.problemTextView);
        generateProblem();

        //Generates multiple choice answers and set the correct answer
        generateAnswers();

        //Multiple Choice buttons
        answerButton0 = (Button) findViewById(R.id.answerButton0);
        answerButton1 = (Button) findViewById(R.id.answerButton1);
        answerButton2 = (Button) findViewById(R.id.answerButton2);
        answerButton3 = (Button) findViewById(R.id.answerButton3);
        generateMultipleChoicesButtons();

        //Go button to start the trainer
        goButton = (Button) findViewById(R.id.goButton);
        hideLayout();
        //Play again button after the game finishes
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
    }
}