package com.agora.bid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.Bid;
import com.agora.entity.Need;
import com.agora.image.ImageDialogFragment;
import com.agora.listener.OnItemTouchListener;
import com.agora.listener.ProgressBarStateListener;
import com.agora.util.ProgressBarHandler;
import com.agora.listener.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;


public class BidListFragment extends Fragment implements OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Bid> mItems= new ArrayList<>();
    private ProgressBarStateListener mListener;
    private Activity activity;
    private ProgressBarHandler progressBarHandlerhandler;
    private Need need;
    private ProgressDialog progress;
    private GenericListHandler<Bid> handler;
    private final static String HANDLER="handler";
    private AppContext appContext;
    public static final int BID_DETAIL_ACTION=0;
    public static final int IMG_SHOW_ACTION=1;

    private Bid bid;

    public BidListFragment() {
        // Required empty public constructor
    }

    public static BidListFragment newInstance(Need need, GenericListHandler<Bid> handler) {
        BidListFragment fragment = new BidListFragment();
        Bundle args = new Bundle();
        args.putSerializable("need", need);
        args.putSerializable(HANDLER,handler);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bid_list, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
        try {
            mListener = (ProgressBarStateListener) activity;
            progressBarHandlerhandler = new ProgressBarHandler(mListener);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        progressBarHandlerhandler=null;
        handler=null;
    }


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle=getArguments();
        need=(Need)bundle.getSerializable("need");
        handler=(GenericListHandler<Bid>)bundle.getSerializable(HANDLER);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.bids_recycler_view);
        mLayoutManager = new LinearLayoutManager( getActivity());
        mAdapter = new BidListAdapter(mItems,this,need);
        mRecyclerView.setAdapter(mAdapter);
        appContext=(AppContext)view.getContext().getApplicationContext();
        //adapter
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {

                                    final int s=position;
                                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which){
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    //Yes button clicked
                                                    mItems.remove(s);
                                                    mAdapter.notifyItemRemoved(s);
                                                    break;

                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    //No button clicked
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();
                                    // mItems.remove(position);
                                    //mAdapter.notifyItemRemoved(position);
                                }
                                //mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    final int s=position;
                                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which){
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    //Yes button clicked
                                                    mItems.remove(s);
                                                    mAdapter.notifyItemRemoved(s);
                                                    break;

                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    //No button clicked
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                                            .setNegativeButton("No", dialogClickListener).show();


                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        //mRecyclerView.addOnItemTouchListener(swipeTouchListener);
        mRecyclerView.setLayoutManager(mLayoutManager);



    }



    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (appContext.getBids().size()!=0){
            appContext.getBids().clear();
        }
        AdapterAsyncTask task = new AdapterAsyncTask();
        task.execute();
    }



    @Override
    public void onCardViewTap(View view, int position,final int action) {
        switch (action){
            case BID_DETAIL_ACTION:
                break;

            case IMG_SHOW_ACTION:
                showEditDialog(position);
                break;

        }

    }


    private void showEditDialog(int position) {
        FragmentManager fm = getFragmentManager();
        ArrayList<String> imageList= new ArrayList<>();
        bid= appContext.getBids().get(position);
        if (bid.getImage1()!=null && !bid.getImage1().trim().equals("")) {
            imageList.add(bid.getImage1());
        }
        if (bid.getImage2()!=null && !bid.getImage2().trim().equals("")) {
            imageList.add(bid.getImage2());
        }
        if (bid.getImage3()!=null && !bid.getImage3().trim().equals("")) {
            imageList.add(bid.getImage3());
        }
        if(imageList.size()>0) {
            ImageDialogFragment imageDialog = ImageDialogFragment.newInstance(imageList);
            imageDialog.show(fm, "fragment_image");
        }
    }

    @Override
    public void onButton1Click(View view, int position) {
        Bid bid=appContext.getBids().get(position);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("bid",bid);
        returnIntent.putExtra("need", need);
        getActivity().setResult(100, returnIntent);
        getActivity().finish();


    }



    @Override
    public void onButton2Click(View view, int position) {

    }

    private class AdapterAsyncTask extends AsyncTask<Void, Void,   List<Bid> > {
        @Override
        protected void onPostExecute( List<Bid> result ) {

            mItems.clear();

                for (Bid n : appContext.getBids()) {
                    mItems.add(n);
                }
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
            if (progressBarHandlerhandler!=null) {
                Message msg = progressBarHandlerhandler.obtainMessage();
                msg.arg1 = AppConfig.PROGRESS_STOP;
                progressBarHandlerhandler.sendMessage(msg);
            }


        }

        @Override
        protected void onPreExecute(){
            Message msg = progressBarHandlerhandler.obtainMessage();
            msg.arg1 = AppConfig.PROGRESS_RUNNING;
            progressBarHandlerhandler.sendMessage(msg);
        }

        @Override
        protected  List<Bid>  doInBackground(Void... params) {
                List<Bid> result= handler.list();
                //List<Bid> result= AppConfig.dataProvider.fetchBidByNeedKey(need);
                for (Bid b: result){
                    b.setNeed(need);
                    appContext.getBids().add(b);
                }
                AppConfig.dataProvider.bidsRead(need.getNeedKey());


            return null;
        }

    }

    @Override
    public void onDestroyView() {

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }
        mAdapter = null;
        mLayoutManager = null;
        super.onDestroyView();
    }





}
