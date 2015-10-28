package com.example.guest.twitter_clone.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.input.InputManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guest.twitter_clone.R;
import com.example.guest.twitter_clone.models.Tweet;
import com.example.guest.twitter_clone.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private User mUser;
    private EditText mTweetText;
    private Button mSubmitButton;
    private ArrayList<Tweet> mTweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("twitter", Context.MODE_PRIVATE);

        mTweetText = (EditText) findViewById(R.id.newTweetEdit);
        mSubmitButton = (Button) findViewById(R.id.tweetSubmitButton);
        mTweets = (ArrayList) Tweet.all();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweetContent = mTweetText.getText().toString();
                Tweet tweet = new Tweet(tweetContent, mUser);
                tweet.save();
                mTweets.add(tweet);

                mTweetText.setText("");
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        if (!isRegistered()) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    private boolean isRegistered() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
        } else {
            mUser = new User(username);
            mUser.save();
        }
        Toast.makeText(this, "Welcome " + mUser.getName(), Toast.LENGTH_LONG).show();
    }
}
