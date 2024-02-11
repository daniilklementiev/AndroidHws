package com.example.hws;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button countButton;
    private Button timerButton;
    private LinearLayout layout;
    private int clickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        countButton = findViewById(R.id.countButton);
        timerButton = findViewById(R.id.timerButton);
        layout = findViewById(R.id.layout);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
        params.width = 500;
        params.height = 300;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
                params.width += 10; // Увеличиваем ширину кнопки на 10 пикселей
                button.setLayoutParams(params);
            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                countButton.setText("Клики: " + clickCount);

                if (clickCount >= 20) {
                    countButton.setEnabled(false); // Делаем кнопку неактивной
                }
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Установка времени до следующего занятия (12.02.2024 8:50 утра)
                Calendar nextClassTime = Calendar.getInstance();
                nextClassTime.set(2024, Calendar.FEBRUARY, 12, 8, 50, 0);

                // Получаем текущее время
                Calendar currentTime = Calendar.getInstance();

                long diffInMillis = nextClassTime.getTimeInMillis() - currentTime.getTimeInMillis();

                // Создаем таймер обратного отсчета
                new CountDownTimer(diffInMillis, 1000) {
                    public void onTick(long millisUntilFinished) {
                        long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(days);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished));
                        String timerText = days + " дней " + hours + " ч " + minutes + " м " + seconds + " сек";
                        timerButton.setText(timerText);
                        setTitle(timerText);
                    }

                    public void onFinish() {
                        timerButton.setText("Время вышло!");
                        setTitle("Время вышло!");
                    }
                }.start();
            }
        });
    }
}