package com.agora.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.agora.R;
import com.agora.bid.BidListActivity;
import com.agora.login.Login2Fragment;
import com.agora.login.LoginActivity;
import com.agora.login.LoginFragment;
import com.agora.login.SignUpActivity;
import com.agora.login.SignUpFragment;
import com.agora.need.NeedListActivity;

public class SplashActivity extends AppCompatActivity implements
        SplashFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener ,Login2Fragment.OnFragmentInteractionListener {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SplashFragment splashFragment = new SplashFragment();
        getFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.container, splashFragment)
                .commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void signUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }


    public void onBackPressed(){
        super.onBackPressed();
        finish();

    }

}
