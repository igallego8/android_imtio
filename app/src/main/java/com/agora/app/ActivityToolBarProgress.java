package com.agora.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.agora.R;

/**
 * Created by Ivan on 29/09/15.
 */
public abstract class ActivityToolBarProgress extends AppCompatActivity {

    protected ProgressBar mProgressBar;
    protected Toolbar toolbar;


    protected void onCreate() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        mProgressBar= (ProgressBar)findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.INVISIBLE);
    }


    public void setProgressBarStatus(final int state){
        if (mProgressBar!=null){
            switch(state) {
                case AppConfig.PROGRESS_RUNNING:
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                default:

                    mProgressBar.setVisibility(View.INVISIBLE);
            }
        }
    }
}
