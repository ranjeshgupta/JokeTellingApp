package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.gcetask.AsyncResponse;
import com.udacity.gradle.builditbigger.gcetask.EndpointsAsyncTask;
import com.udacity.gradle.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    EndpointsAsyncTask mJokeTask = new EndpointsAsyncTask();
    private ProgressBar mSpinner;
    private Button mBtnTellJoke;
    private TextView mTvInstruction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner = (ProgressBar) findViewById(R.id.progressBar);
        mSpinner.setVisibility(View.GONE);

        mBtnTellJoke = (Button) findViewById(R.id.btn_tell_joke);
        mTvInstruction = (TextView) findViewById(R.id.instructions_text_view);

        mJokeTask.delegate = this;
    }

    public void tellJoke(View view) {
        mBtnTellJoke.setVisibility(View.GONE);
        mTvInstruction.setVisibility(View.GONE);
        mSpinner.setVisibility(View.VISIBLE);

        mJokeTask.execute();
    }

    //this override the implemented method from asyncTask
    public void processFinish(String output){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, output);
        startActivity(intent);
    }
}
