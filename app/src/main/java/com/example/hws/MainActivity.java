package com.example.hws;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView sentenceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sentenceTextView = findViewById(R.id.sentenceTextView);
    }

    public void generateSentence(View view) {
        String sentence = composeSentence();
        sentenceTextView.setText(sentence);
    }

    private String composeSentence() {
        ArrayList<String> parts = new ArrayList<>();
        parts.add(readFile(R.raw.kto));
        parts.add(readFile(R.raw.gde));
        parts.add(readFile(R.raw.kogda));
        parts.add(readFile(R.raw.s_kem));
        parts.add(readFile(R.raw.chto_delali));
        parts.add(readFile(R.raw.chto_sluchilos));
        parts.add(readFile(R.raw.moral));

        StringBuilder sentenceBuilder = new StringBuilder();
        for (String part : parts) {
            sentenceBuilder.append(part).append(" ");
        }
        return sentenceBuilder.toString().trim();
    }

    private String readFile(int resourceId) {
        InputStream inputStream = getResources().openRawResource(resourceId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> lines = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        return lines.get(random.nextInt(lines.size()));
    }
}
