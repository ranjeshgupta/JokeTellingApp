package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.gcetask.AsyncResponse;
import com.udacity.gradle.builditbigger.gcetask.EndpointsAsyncTask;
import com.udacity.gradle.jokedisplay.JokeActivity;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    EndpointsAsyncTask mJokeTask = new EndpointsAsyncTask();
    private InterstitialAd mInterstitialAd;
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

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_interstitial_joke_telling));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                mJokeTask.execute();
            }
        });
    }

    public void tellJoke(View view) {
        mBtnTellJoke.setVisibility(View.GONE);
        mTvInstruction.setVisibility(View.GONE);
        mSpinner.setVisibility(View.VISIBLE);

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            mJokeTask.execute();
        }
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    //this override the implemented method from asyncTask
    public void processFinish(String output){
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.JOKE_KEY, output);
        startActivity(intent);
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (!mInterstitialAd.isLoaded()) {
            requestNewInterstitial();
        }
    }
}
