package com.example.hws;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText tweetEditText;
    private Button tweetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tweetEditText = findViewById(R.id.tweet_edit_text);
        tweetButton = findViewById(R.id.tweet_button);

        tweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet = tweetEditText.getText().toString();
                if (!tweet.isEmpty()) {
                    new UpdateTwitterStatus().execute(tweet);
                } else {
                    Toast.makeText(MainActivity.this, "Введите текст твита", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class UpdateTwitterStatus extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                ConfigurationBuilder builder = new ConfigurationBuilder();
                builder.setDebugEnabled(true)
                        .setOAuthConsumerKey("------")
                        .setOAuthConsumerSecret("------")
                        .setOAuthAccessToken("------")
                        .setOAuthAccessTokenSecret("------");

                Twitter twitter = new TwitterFactory(builder.build()).getInstance();
                twitter4j.Status status = twitter.updateStatus(params[0]);
                return status.getText();
            } catch (TwitterException e) {
                e.printStackTrace();
                return "Failed to update status: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }
}
