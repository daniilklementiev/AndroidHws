package com.example.hws;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private Animation anim;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.img);
        anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        iv.startAnimation(anim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        iv.clearAnimation();
    }
}