package com.agora.util;

import android.os.Handler;
import android.os.Message;

import com.agora.app.AppConfig;
import com.agora.listener.ProgressBarStateListener;

/**
 * Created by Ivan on 5/11/15.
 */
public class ProgressBarHandler extends Handler {

    ProgressBarStateListener mListener;

    public ProgressBarHandler(ProgressBarStateListener mListener){
        this.mListener=mListener;
    }

    public void handleMessage(Message msg) {
        int current = msg.arg1;
        if(mListener!=null) {
            if (current == AppConfig.PROGRESS_STOP) {
                mListener.setProgressState(AppConfig.PROGRESS_STOP);
            } else {
                mListener.setProgressState(AppConfig.PROGRESS_RUNNING);
            }
        }
    }
}
