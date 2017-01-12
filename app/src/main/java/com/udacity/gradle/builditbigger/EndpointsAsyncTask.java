package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by kapil pc on 12/2/2016.
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    static public boolean testFlag = false;
    static public String loadedJoke = "" ;
    private OnTaskCompleted mListener;

    public EndpointsAsyncTask (OnTaskCompleted listener) {
        this.mListener = listener;
    }



    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });*/
            // end options for devappserver
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new
                    AndroidJsonFactory(), null)
                    .setRootUrl("https://jokesnano.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

       // context = params[0];
       // String name = params[0].second;

        try {
            //loadedJoke = myApiService.tellJoke().execute().getData();
            Log.i("Truth",myApiService.tellJoke().execute().getData());
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
       mListener.OnTaskCompleted(result);
       /* if (!testFlag) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

            Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        }*/
    }
}
