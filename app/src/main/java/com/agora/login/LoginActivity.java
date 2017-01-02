package com.agora.login;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;

import com.agora.R;


public class LoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener , Login2Fragment.OnFragmentInteractionListener {
    private TransitionManager mTransitionManager;
    private Scene mScene1;
    private Scene mScene2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }

        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        TransitionInflater transitionInflater = TransitionInflater.from(this);
        mTransitionManager = transitionInflater.inflateTransitionManager(R.transition.transition_manager, container);
        mScene1 = Scene.getSceneForLayout(container, R.layout.fragment_login, this);
        mScene2 = Scene.getSceneForLayout(container, R.layout.fragment_login_transition2, this);
        //mScene1.enter();
        //new  AsyncLoginTask().execute();
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void signUp() {

        Intent i = new Intent (this, SignUpActivity.class);
        this.startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    class AsyncLoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int x=50000;x>=0;x--){

            }
         return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mTransitionManager.transitionTo(mScene2);
        }
    }
}
