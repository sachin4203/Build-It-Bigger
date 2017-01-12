package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import sachin.com.jokesandroid.JokesActivity;

/**
 * Created by kapil pc on 12/3/2016.
 */

public class MainActivityFragment extends Fragment implements OnTaskCompleted{

        private String mResult = null;
        private boolean testFlag = true;
        private Button fetchJokeButton ;
        private ProgressBar mProgressbar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        fetchJokeButton = (Button)root.findViewById(R.id.fetch_joke_Button);
        mProgressbar = (ProgressBar)root.findViewById(R.id.progressBar1);
        mProgressbar.setVisibility(View.INVISIBLE);
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
        mResult = result;
        if(mResult !=null && !testFlag) {
            mProgressbar.setVisibility(View.INVISIBLE);
            Intent next = new Intent(getActivity(), JokesActivity.class);
            next.putExtra(JokesActivity.JOKE_INTENT, mResult);
            getActivity().startActivity(next);
        }
    }

    public void loadData() {
        testFlag = false;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }
}