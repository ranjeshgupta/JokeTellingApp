package com.udacity.gradle.builditbigger;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.udacity.gradle.jokerlib.Joker;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tellJoke(View view){
        Joker myJoker = new Joker();
        Toast.makeText(this, myJoker.getJoke(), Toast.LENGTH_SHORT).show();
    }


}
