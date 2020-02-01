package com.example.androidproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean playing = false;
    int score = 0;

    String answer = "";

    CountDownTimer countdown;

    TextView tv;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        time = findViewById(R.id.time);

        countdown = new CountDownTimer(3000, 100) {

            public void onTick(long millisUntilFinished) {
                double remaining = millisUntilFinished/1000.0;
                time.setText(String.valueOf(remaining));
                if(!answer.equals("")) {
                    if(answer.equals(tv.getText().toString())) {
                        score++;
                    } else {
                        score--;
                    }
                    answer = "";
                    changeTextColors();
                    countdown.cancel();
                    countdown.start();
                }
            }

            public void onFinish() {
                if(answer.equals(tv.getText().toString())) {
                    score++;
                } else {
                    score--;
                }
                answer = "";
                changeTextColors();
                countdown.cancel();
                countdown.start();
            }

        };
    }

    int randomColor() {
        int rand = (int) (Math.random() * 3);
        switch (rand) {
            case 0: return R.color.blue;
            case 1: return R.color.orange;
            case 2: return R.color.green;
            default: return R.color.red;
        }
    }

    void changeTextColors() {
        int color = randomColor();
        tv.setTextColor(getResources().getColor(color));
        while(tv.getCurrentTextColor() == getResources().getColor(color)) {
            color = randomColor();
        }
        tv.setBackgroundColor(getResources().getColor(color));
        color = randomColor();
        switch (color) {
            case R.color.blue:
                tv.setText(R.string.blue);
                break;
            case R.color.orange:
                tv.setText(R.string.orange);
                break;
            case R.color.red:
                tv.setText(R.string.red);
                break;
            case R.color.green:
                tv.setText(R.string.green);
                break;
            default:break;
        }
    }

    public void play(View view) {
        Button btn = (Button) view;
        if(!playing) {
            btn.setText("Cancel");
            changeTextColors();
            playing = true;
            countdown.start();
        } else {
            Toast.makeText(this, "You scored "+score, Toast.LENGTH_LONG).show();
            score = 0;
            answer = "";
            btn.setText("Play");
            countdown.cancel();
            playing = false;
        }
    }

    public void selectColor(View view) {
        Button btn = (Button) view;
        answer = btn.getText().toString();
    }
}
