/*
Timer count down practice
source: https://www.udemy.com/course/the-complete-android-oreo-developer-course/learn/lecture/8339442#overview


 */

package com.example.countdowntimer_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    SeekBar timerSeekBar;
    Button startButton;
    CountDownTimer countDownTimer;
    boolean counterIsActive = false;

    public void resetTimer() {
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        startButton.setText("START!");
        counterIsActive = false;
    }

    public void startTimer(View view) {
        startButton = (Button) findViewById(R.id.startButton);
        if(counterIsActive) {
            resetTimer();
        }
        else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("STOP!");
            // progress is in seconds so multiply it by 1000 to convert to ms.
            //Added extra 1000 to end of progress to add extra ms padding when timer reaches 0.
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 1000, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }//converts ms to seconds

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft -  minutes * 60;

        String minuteSecond = Integer.toString(minutes);
        String zeroSecond = Integer.toString(seconds);

        //Appends zero if less than 9 to keep 00:00 format
        if(minutes <= 9) {
            minuteSecond = "0" + minuteSecond;
        }
        if(seconds <= 9) {
            zeroSecond = "0" + zeroSecond;
        }
        timerTextView.setText(minuteSecond + ":" + zeroSecond);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating the countdown seekbar
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextview);

        //1 minutes max so 1*60seconds = 60 seconds
        int timerMax = 60;
        int currentProgress = 30;
        timerSeekBar.setMax(timerMax);
        timerSeekBar.setProgress(currentProgress);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override//i = number of seconds
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}