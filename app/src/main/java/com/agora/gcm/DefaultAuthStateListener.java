package com.agora.gcm;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Ivan on 2/01/17.
 */

public class DefaultAuthStateListener implements FirebaseAuth.AuthStateListener {

    private FirebaseAuth.AuthStateListener mAuthListener;

    public static final String TAG="DefaultAuthStateListener";

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {

                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

            }
        };

    }



}
