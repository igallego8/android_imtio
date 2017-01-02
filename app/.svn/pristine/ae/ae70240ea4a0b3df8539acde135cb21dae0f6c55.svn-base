package com.agora.need;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.agora.R;
import com.agora.app.AppContext;
import com.agora.entity.Filter;
import com.agora.entity.Need;
import com.agora.listener.OnStartDragListener;
import com.agora.need.preferences.NeedPreferenceAdapter;
import com.agora.need.preferences.PreferenceDTO;
import com.agora.util.helper.SimpleItemTouchHelperCallback;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.ArrayList;
import java.util.Date;


public class NeedNewFragment extends Fragment implements OnStartDragListener {


    private EditText description;
    private SimpleDraweeView thumbnail1;
    private SimpleDraweeView thumbnail2;
    private SimpleDraweeView thumbnail3;
    private Need need;
    private LinearLayout expandable;
    private ValueAnimator mAnimator;

    private AppContext appContext;
    private RecyclerView mRecyclerView;
    private NeedPreferenceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager manager;
    public static ArrayList<PreferenceDTO> preferences= new ArrayList<>();


    private OnFragmentInteractionListener mListener;

    public NeedNewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_need_new, container, false);

    }

    public static NeedNewFragment newInstance(Need need, ArrayList<PreferenceDTO> preferences) {
        NeedNewFragment fragment = new NeedNewFragment();
        Bundle args = new Bundle();
        args.putSerializable("need",need);
        args.putSerializable("preferences",preferences);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appContext = (AppContext) this.getActivity().getApplicationContext();
        thumbnail1 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail1);
        thumbnail2 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail2);
        thumbnail3 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail3);
        Bundle bundle=getArguments();
        preferences=(ArrayList<PreferenceDTO>)bundle.getSerializable("preferences");
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.filters_recycler_view);
        manager =new LinearLayoutManager(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NeedPreferenceAdapter(preferences,this,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        description = (EditText) view.findViewById(R.id.et_description);
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mListener.afterDescriptionTextChanged(description.getText().toString());
            }
        });

        need=(Need)getArguments().getSerializable("need");

        expandable= (LinearLayout) view.findViewById(R.id.layout_expandable);
        SharedPreferences settings=  PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        boolean flag=settings.getBoolean("showcase_need_preferences", false);

        if (!flag) {
            ViewTarget target = new ViewTarget(view.findViewById(R.id.iv_expandable_icon));
            ShowcaseView sv = new ShowcaseView.Builder(this.getActivity())

                    .setTarget(target)
                    .setContentTitle(R.string.showcase_title_need_preferences)
                    .setContentText(R.string.showcase_text_need_preferences)
                    .hideOnTouchOutside()
                    .setStyle(R.style.CustomShowcaseTheme3)
                    .build();

            sv.hideButton();
            sv.show();

            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("showcase_need_preferences", true);
            editor.commit();
        }

    }


    protected void expand(){
        expandable.setVisibility(View.VISIBLE);
        mAnimator.start();

    }

    protected void collapse(){
        final int height= expandable.getHeight();
        ValueAnimator nAnimator= slideAnimator(height,0);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                expandable.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();
    }

    public void onResume(){
        super.onResume();
        if(appContext.isAttachedImagesConfirmed()) {
            if (appContext.getThumbnailImages().get(0)!=null){
                thumbnail1.setImageBitmap(appContext.getThumbnailImages().get(0));
            }
            if (appContext.getThumbnailImages().get(1)!=null){
                thumbnail2.setImageBitmap(appContext.getThumbnailImages().get(1));
            }
            if (appContext.getThumbnailImages().get(2)!=null){
                thumbnail3.setImageBitmap(appContext.getThumbnailImages().get(2));
            }

        }
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }


    public interface OnFragmentInteractionListener {
       public void afterDescriptionTextChanged(String text);
    }

    protected ValueAnimator slideAnimator(int start, int end){
        ValueAnimator animator= ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams= expandable.getLayoutParams();
                layoutParams.height=value;
                expandable.setLayoutParams(layoutParams);

            }
        });
        return animator;
    }


}
