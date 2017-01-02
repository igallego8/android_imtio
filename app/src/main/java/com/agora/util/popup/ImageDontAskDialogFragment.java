package com.agora.util.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.web.WebActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ImageDontAskDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDontAskDialogFragment extends DialogFragment {


    private Activity activity;
    private  CheckBox dontShowAgain;
    private ArrayList content;
    private int index;
    private ImageView image;
    private AlertDialog alert;
    private TextView text;

    public static ImageDontAskDialogFragment newInstance(ArrayList<DontAskContentDialog> content) {
        ImageDontAskDialogFragment fragment = new ImageDontAskDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("content", content);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageDontAskDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        content= (ArrayList)getArguments().getSerializable("content");
        if (content.size()>0) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.activity_popup, null);
            dontShowAgain = (CheckBox) v.findViewById(R.id.skip);
            image = (ImageView) v.findViewById(R.id.iv_tutorial);
            text = (TextView)v.findViewById(R.id.tv_text);
            image.setImageResource(((DontAskContentDialog) content.get(index)).getResource());
            text.setText(((DontAskContentDialog) content.get(index)).getText());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),WebActivity.class);
                    intent.putExtra(WebActivity.URL,((DontAskContentDialog) content.get(index)).getUrl());
                    getActivity().startActivity(intent);
                }
            });
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.do_you_know);
            builder.setView(v)
                    .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String checkBoxResult = "NOT checked";
                            if (dontShowAgain.isChecked()) {
                                checkBoxResult = "checked";
                            }
                            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString(AppConfig.PREF_KEY_SKIP_NEED_TUTORIAL, checkBoxResult);
                            editor.commit();
                            dialog.dismiss();

                        }
                    })
                    .setNeutralButton(R.string.previous,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                    .setNegativeButton(R.string.next,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
            alert = builder.create();
            return alert;
        }
        return null;
    }

    @Override
    public void onStart() {
        super.onStart();
        Button previousButton = alert.getButton(DialogInterface.BUTTON_NEUTRAL);
        Button nextButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        previousButton.setOnClickListener(new PreviousListener(alert));
        nextButton.setOnClickListener(new NextListener(alert));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    class PreviousListener implements View.OnClickListener {
        private final Dialog dialog;
        public PreviousListener(Dialog dialog) {
            this.dialog = dialog;
        }
        @Override
        public void onClick(View v) {
            index--;
            if (index < 0) {
                index = content.size() - 1;
            }
            text.setText(((DontAskContentDialog) content.get(index)).getText());
            image.setImageResource(((DontAskContentDialog) content.get(index)).getResource());
        }
    }

    class NextListener implements View.OnClickListener {
        private final Dialog dialog;
        public NextListener(Dialog dialog) {
            this.dialog = dialog;

        }
        @Override
        public void onClick(View v) {
            index++;
            if (index > content.size() - 1) {
                index = 0;
            }
            text.setText(((DontAskContentDialog) content.get(index)).getText());
            image.setImageResource(((DontAskContentDialog) content.get(index)).getResource());
        }
    }


}
