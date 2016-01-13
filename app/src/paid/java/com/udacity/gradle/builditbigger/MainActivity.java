package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.udacity.gradle.builditbigger.gcetask.AsyncResponse;
import com.udacity.gradle.builditbigger.gcetask.EndpointsAsyncTask;
import com.udacity.gradle.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    EndpointsAsyncTask mJokeTask = new EndpointsAsyncTask();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view) {
        mJokeTask.delegate = this;
        mJokeTask.execute();
    }

    //this override the implemented method from asyncTask
    public void processFinish(String output){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, output);
        startActivity(intent);
    }
}
