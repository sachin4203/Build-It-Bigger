package com.udacity.gradle.builditbigger;

import android.os.Handler;
import android.os.Looper;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;

/**
 * Created by kapil pc on 12/2/2016.
 */

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {


    @Test
    public void testDoInBackground() throws Exception {


        Handler h = new Handler(Looper.getMainLooper());
        h.post(new Runnable() {
            public void run() {
                final com.udacity.gradle.builditbigger.MainActivityFragment fragment = new com.udacity.gradle.builditbigger.MainActivityFragment();
                EndpointsAsyncTask endPointAsync = new EndpointsAsyncTask(fragment);
                endPointAsync.execute();
                String result = "";
                try{
                    result  = endPointAsync.get(30, TimeUnit.SECONDS);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                assertFalse("Error: Fetched Joke =  "+ result, result.isEmpty());
            }
        });



    }
}
