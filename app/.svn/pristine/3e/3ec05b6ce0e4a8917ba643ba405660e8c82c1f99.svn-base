package com.agora.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.agora.R;
import com.agora.login.Login2Fragment;
import com.agora.need.NeedListActivity;
import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SplashFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {


    private View view;
    final Handler handler = new Handler();
    private Activity activity;
    private OnFragmentInteractionListener mListener;
    private SharedPreferences settings;


    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                AsyncLoginTask task = new AsyncLoginTask();
                task.execute();
            }
        }, 500);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private Handler handle = new Handler();


    class AsyncLoginTask extends AsyncTask<Void, Void, Void> {



        protected void onPreExecute() {


        }



        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            handle.post(new Runnable() {
                @Override
                public void run() {
                    settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());

                    Long userKey = settings.getLong("userKey", 0L);
                    if (userKey != 0L) {
                        needListIntent();
                    } else {
                        final Login2Fragment toFragment = new Login2Fragment();
                        FragmentTransitionLauncher
                                .with(view.getContext())
                                .from(view.findViewById(R.id.iv_logo)).prepare(toFragment);
                        FragmentManager manager = getActivity().getFragmentManager();
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.replace(R.id.container, toFragment).addToBackStack(null).commitAllowingStateLoss();
                    }
                }
            });
        }
    }


    public void needListIntent(){
        Intent mainIntent = new Intent(activity, NeedListActivity.class);
        activity.startActivity(mainIntent);
        activity.finish();
    }

}
