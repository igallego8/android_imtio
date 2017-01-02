package com.agora.login;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agora.R;
import com.kogitune.activity_transition.fragment.ExitFragmentTransition;
import com.kogitune.activity_transition.fragment.FragmentTransition;

public class Login2Fragment extends Fragment {

    final Handler handler = new Handler();
    private OnFragmentInteractionListener mListener;


    public static Login2Fragment newInstance(String param1, String param2) {
        Login2Fragment fragment = new Login2Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public Login2Fragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_login_transition2, container, false);
        final ExitFragmentTransition exitFragmentTransition = FragmentTransition.with(this).to(rootView.findViewById(R.id.iv_logo)).start(savedInstanceState);
        exitFragmentTransition.startExitListening();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                AsyncLoginTask task = new AsyncLoginTask();
                task.execute();
            }
        }, 1000);

        return rootView;
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

    class AsyncLoginTask extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {


        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            LoginFragment f= new LoginFragment();
            FragmentManager manager = getActivity().getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.replace(R.id.container, f).disallowAddToBackStack().commit();

        }
    }

}
