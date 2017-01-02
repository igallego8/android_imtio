package com.agora.need;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.entity.AmountFilter;
import com.agora.entity.CashPaymentFilter;
import com.agora.entity.CreditCardPaymentFilter;
import com.agora.entity.DateFilter;
import com.agora.entity.Filter;
import com.agora.entity.Need;
import com.agora.entity.ShippingFilter;
import com.agora.listener.OnStartDragListener;
import com.agora.need.preferences.NeedPreferenceAdapter;
import com.agora.need.preferences.PreferenceDTO;
import com.agora.util.helper.SimpleItemTouchHelperCallback;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NeedDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NeedDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeedDetailFragment extends Fragment implements OnStartDragListener {

    private TextView expirationDate;
    private TextView noImagesAttached;
    private TextView description;


    private SimpleDraweeView thumbnail1;
    private SimpleDraweeView thumbnail2;
    private SimpleDraweeView thumbnail3;
    private Need need;
    private LinearLayout expandable;
    private ValueAnimator mAnimator;


    private RecyclerView mRecyclerView;
    private NeedPreferenceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager manager;
    public static ArrayList<PreferenceDTO> preferences= new ArrayList<>();



    private OnFragmentInteractionListener mListener;

    public NeedDetailFragment() {
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
        return inflater.inflate(R.layout.fragment_need_detail, container, false);

    }

    public static NeedDetailFragment newInstance(Need need, ArrayList<PreferenceDTO> preferences) {
        NeedDetailFragment fragment = new NeedDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("need", need);
        args.putSerializable("preferences",preferences);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thumbnail1 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail1);
        thumbnail2 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail2);
        thumbnail3 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail3);
        Bundle bundle=getArguments();
        preferences=(ArrayList<PreferenceDTO>)bundle.getSerializable("preferences");
        Need need= (Need)bundle.getSerializable("need");
        description = (TextView) view.findViewById(R.id.et_description);
        description.setText(need.getDescription());
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.filters_recycler_view);
        manager =new LinearLayoutManager(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NeedPreferenceAdapter(preferences,this,null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        //ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        //mItemTouchHelper = new ItemTouchHelper(callback);
        //mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    public void onResume(){
        super.onResume();

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

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
