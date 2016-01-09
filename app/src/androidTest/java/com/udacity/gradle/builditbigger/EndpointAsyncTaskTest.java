package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import com.udacity.gradle.builditbigger.gcetask.AsyncResponse;
import com.udacity.gradle.builditbigger.gcetask.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nish on 10-01-2016.
 */
public class EndpointAsyncTaskTest  extends AndroidTestCase implements AsyncResponse {
    EndpointsAsyncTask jokeTask;
    CountDownLatch signal;
    String joke ="";

    protected void setUp() throws Exception {
        super.setUp();

        signal = new CountDownLatch(1);
        jokeTask = new EndpointsAsyncTask();
        jokeTask.delegate = this;
    }

    @UiThreadTest
    public void testEndpointsAsyncTask() throws InterruptedException
    {
        jokeTask.execute();
        signal.await(30, TimeUnit.SECONDS);

        assertTrue("Joke is fetched", joke != null);
    }

    @Override
    public void processFinish(String output) {
        joke = output;
        signal.countDown();
    }

}