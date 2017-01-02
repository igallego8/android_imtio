package com.agora.image;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.Need;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ImageDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDialogFragment extends DialogFragment {


    private Need need;
    private ArrayList<String> imageList;
    private Activity activity;
    private int index;
    private SimpleDraweeView image;
    private FrameLayout frame_left;
    private FrameLayout frame_right;

    public static ImageDialogFragment newInstance(ArrayList<String> imageList) {
        ImageDialogFragment fragment = new ImageDialogFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("imagelist", imageList);
        fragment.setArguments(args);
        return fragment;
    }

    public ImageDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageList = getArguments().getStringArrayList("imagelist");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_dialog, container,
                false);
        //getDialog().setTitle("Image");
        frame_left= (FrameLayout) view.findViewById(R.id.frame_left);
        frame_right= (FrameLayout) view.findViewById(R.id.frame_right);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        image = (SimpleDraweeView) view.findViewById(R.id.iv_image);
        if (imageList.size()>0) {
            Uri uri = Uri.parse(AppConfig.IMAGES_URL + imageList.get(index));
            image.setImageURI(uri);
            updateFrames();
        }

        ImageButton bt_left= (ImageButton) view.findViewById(R.id.ib_left);
        frame_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               previousImage();
            }
        });
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousImage();
            }
        });
        ImageButton bt_right= (ImageButton) view.findViewById(R.id.ib_right);
        frame_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextImage();
            }
        });
        return view;
    }

    public void nextImage(){
        if (index < imageList.size()-1) {
            index++;
            setImage();
            updateFrames();
        }
    }

    public void previousImage(){
        if (index > 0){
            index--;
            setImage();
            updateFrames();
        }
    }

    public void updateFrames(){
        if (imageList.size()>1 && index<imageList.size()-1){
            frame_right.setVisibility(View.VISIBLE);
        }else{
            frame_right.setVisibility(View.INVISIBLE);
        }

        if (index==0){
            frame_left.setVisibility(View.INVISIBLE);
        }else{
            frame_left.setVisibility(View.VISIBLE);
        }
    }

    public void setImage(){
        Uri uri = Uri.parse(AppConfig.IMAGES_URL + imageList.get(index));
        image.setImageURI(uri);
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


}
