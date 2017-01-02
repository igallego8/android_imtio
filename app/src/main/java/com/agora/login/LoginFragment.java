package com.agora.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.db.dao.ChatDAO;
import com.agora.entity.Auth;
import com.agora.entity.Chat;
import com.agora.entity.Client;
import com.agora.entity.Need;
import com.agora.entity.User;
import com.agora.need.NeedListActivity;
import com.agora.util.OnFocusEditTextChangeText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    protected TextWatcher tw;
    private Activity activity;
    private OnFragmentInteractionListener mListener;
    private Button button;
    private EditText password;
    protected EditText id;
    private TextView lost_password;
    protected LinearLayout layout;
    final Handler handler = new Handler();
    private String idGCM;
   // private GoogleCloudMessaging gcm;
   private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private SharedPreferences settings;
    private ProgressDialog progress;
    private Context context;
    public static final int AUTH_ERROR=100;
    private AppContext appContext;
    public static final String TAG="LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login_transition, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(final View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        settings=  PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        context=view.getContext();
        layout= (LinearLayout) view.findViewById(R.id.layout_sign_up);
        button= (Button) view.findViewById(R.id.bt_sing_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().equals("") || password.getText().toString().equals("PASSWORD")
                        || id.getText().toString().equals("") || id.getText().toString().equals("EMAIL")){
                    Toast.makeText(context, R.string.sign_in_error, Toast.LENGTH_LONG).show();
                }else {
                    getRegId();
                }

            }
        });
        appContext = (AppContext) view.getContext().getApplicationContext();
        password= (EditText)view.findViewById(R.id.tv_password);
        password.setOnFocusChangeListener(new OnFocusEditTextChangeText(password,R.string.password,activity));
        id= (EditText)view.findViewById(R.id.tv_id);
        id.setOnFocusChangeListener(new OnFocusEditTextChangeText(id,R.string.email,activity));
        lost_password= (TextView)view.findViewById(R.id.tv_lost_password);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncLoginTask task = new AsyncLoginTask();
                task.execute();
            }
        }, 1000);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.signUp();
            }
        });

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

        public void needListIntent(){
            Intent mainIntent = new Intent(activity, NeedListActivity.class);
            activity.startActivity(mainIntent);
            activity.finish();
        }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity= activity;
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


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);

        public void signUp();
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
            button.setVisibility(View.VISIBLE);
            password.setVisibility(View.VISIBLE);
            id.setVisibility(View.VISIBLE);
            lost_password.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
        }
    }



    public void getRegId(){
        new AsyncTask<Void, Void, String>() {

            String email;
            String passwordValue;
            User client;

            @Override
            public  void onPreExecute(){
                progress = ProgressDialog.show(activity, null,
                        getResources().getString(R.string.please_wait), true);
                progress.show();
                email= id.getText().toString();
                passwordValue=password.getText().toString();
            }
            @Override
            protected String doInBackground(Void... params) {
                mAuth = FirebaseAuth.getInstance();
               //try {
                 /*   if (gcm == null) {
                            gcm = GoogleCloudMessaging.getInstance(activity.getApplicationContext());
                    }*/
                    idGCM = "1231425534534";// gcm.register(AppConfig.PROJECT_NUMBER);
                   if (idGCM != null && !idGCM.trim().equals("")) {
                        SharedPreferences.Editor editor = settings.edit();
                        Auth auth = new Auth(email, passwordValue, idGCM);
                        client=(User) AppConfig.dataProvider.signIn(auth);
                        if (client!=null){
                            editor.putLong("userKey", client.getUserKey());
                            editor.putString("userName", client.getName());
                            editor.putString("lastName", client.getLastName());
                            editor.putString("idGCM", idGCM);
                            editor.putString(AppConfig.PREF_KEY_SKIP_NEED_TUTORIAL, "checked");
                            editor.commit();
                            appContext.setUser(client);
                            needListIntent();
                        }
                    }
               // } catch (IOException ex) {
                  // Log.e("GCM",ex.getMessage());

                //}
                return null;
            }

            @Override
            protected void onPostExecute(String msg) {
                progress.dismiss();
                //Log.i("GCM", idGCM);
                if (client==null){
                    Toast.makeText(context, R.string.auth_error, Toast.LENGTH_LONG).show();
                }

            }
        }.execute(null, null, null);
    }


}
