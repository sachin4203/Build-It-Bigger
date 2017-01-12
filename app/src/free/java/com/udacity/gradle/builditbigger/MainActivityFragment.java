package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import sachin.com.jokesandroid.JokesActivity;

/**
 * Created by kapil pc on 12/3/2016.
 */

public class MainActivityFragment extends Fragment implements OnTaskCompleted {

    private String mResult = null;
    private boolean testFlag = true;
    private Button fetchJokeButton ;
    InterstitialAd mInterstitialAd;
    ProgressBar mProgressbar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        fetchJokeButton = (Button)root.findViewById(R.id.fetch_joke_Button);
        mProgressbar = (ProgressBar)root.findViewById(R.id.progressBar1);
        mProgressbar.setVisibility(View.INVISIBLE);
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                if(mResult !=null && !testFlag) {
                    Intent next = new Intent(getActivity(), JokesActivity.class);
                    next.putExtra(JokesActivity.JOKE_INTENT, mResult);
                    getActivity().startActivity(next);
                }

            }
        });

        requestNewInterstitial();


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        fetchJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressbar.setVisibility(View.VISIBLE);
                loadData();
            }
        });
        return root;
    }

    @Override
    public void OnTaskCompleted(String result) {
        if(!testFlag) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            mResult = result;
            mProgressbar.setVisibility(View.INVISIBLE);

        }
    }


    public void loadData() {
        testFlag = false;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}