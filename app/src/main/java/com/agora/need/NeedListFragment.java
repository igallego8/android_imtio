package com.agora.need;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.bid.BidListActivity;
import com.agora.bid.BidNeedListHandler;
import com.agora.db.dao.ChatDAO;
import com.agora.entity.Chat;
import com.agora.entity.Need;
import com.agora.listener.OnItemTouchListener;
import com.agora.listener.ProgressBarStateListener;
import com.agora.util.ProgressBarHandler;
import com.agora.listener.SwipeableRecyclerViewTouchListener;


import java.util.List;



public class NeedListFragment extends Fragment implements OnItemTouchListener  {

    private AppContext appContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager manager;
    private ProgressDialog progress;
    private ProgressBarStateListener mListener;
    public static final String INTENT_FILTER_NEED_CHANGED="com.agora.need.NEED_CHANGED";
    public static final int MESSAGES_READ=0x100;
    private ProgressBarHandler handler;
    private SharedPreferences settings;


    public static NeedListFragment newInstance() {
        NeedListFragment fragment = new NeedListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerView.Adapter getAdapter(){
        return mAdapter;
    }

    public NeedListFragment() {
        // Required empty public constructor
    }

    private BroadcastReceiver chatUpdateReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Long needKey=intent.getLongExtra("needKey", 0L);
            updateNeedInfo(needKey);
        }
    };


    protected void updateNeedInfo(final Long needKey){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (needKey != 0L) {
                    new NeedAsyncTask().execute(needKey);
                }
            }
        });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_need_list, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //noinspection ConstantConditions
        settings = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.needs_recycler_view);
        manager =new LinearLayoutManager(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        appContext=(AppContext)view.getContext().getApplicationContext();
        mAdapter = new NeedListAdapter(appContext.getNeeds(),this);
        mRecyclerView.setAdapter(mAdapter);
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
                                                    new DeleteNeedAsyncTask().execute(s);
                                                    break;

                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    //No button clicked
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(R.string.are_you_sure_remove).setPositiveButton(R.string.yes, dialogClickListener)
                                            .setNegativeButton(R.string.no, dialogClickListener).show();

                                }

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
                                                    new DeleteNeedAsyncTask().execute(s);
                                                    break;

                                                case DialogInterface.BUTTON_NEGATIVE:
                                                    //No button clicked
                                                    break;
                                            }
                                        }
                                    };
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setMessage(R.string.are_you_sure_remove).setPositiveButton(R.string.yes, dialogClickListener)
                                            .setNegativeButton(R.string.no, dialogClickListener).show();

                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);
        mRecyclerView.setLayoutManager(manager);

    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        if (appContext.getNeeds().size()!=0){
            mAdapter.notifyDataSetChanged();
        }else {
            AdapterAsyncTask task = new AdapterAsyncTask();
            task.execute();
        }

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ProgressBarStateListener) activity;
            handler = new ProgressBarHandler(mListener);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        handler=null;
    }

    @Override
    public void onCardViewTap(View view, int position,int action) {
       new  AsyncNeedDetailTask().execute(position);
    }

    @Override
    public void onButton1Click(View view, int position) {
        appContext.getNeeds().get(position).setBidsNumber(0);
        mAdapter.notifyDataSetChanged();
        Intent intent= new Intent(getActivity(), BidListActivity.class);
        intent.putExtra("needposition",position);
        getActivity().startActivityForResult(intent, 100);
    }

    @Override
    public void onButton2Click(View view, int position) {

    }



    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(chatUpdateReceiver);

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
        manager=null;
        super.onDestroyView();

    }




    private class AdapterAsyncTask extends AsyncTask<Void, Void,   List<Need> > {


        @Override
        protected void onPreExecute() {
            Message msg = handler.obtainMessage();
            msg.arg1 = AppConfig.PROGRESS_RUNNING;
            handler.sendMessage(msg);
        }

        @Override
        protected  List<Need>  doInBackground(Void... params) {
            List<Need> result= AppConfig.dataProvider.fetchNeedsByUserKey(settings.getLong("userKey",0L));

            for (Need need: result){
                appContext.getNeeds().add(need);
            }

            return null;
        }


        @Override
        protected void onPostExecute( List<Need> result ) {
            if (mAdapter!=null) {
                mAdapter.notifyDataSetChanged();
            }
            Message msg = handler.obtainMessage();
            msg.arg1 = AppConfig.PROGRESS_STOP;
            handler.sendMessage(msg);
        }

    }


    private class NeedAsyncTask extends AsyncTask<Long, Void, Void > {

        @Override
        protected  Void  doInBackground(Long... params) {
            Need need= AppConfig.dataProvider.fetchNeedByNeedKey(params[0]);
            for (Need n: appContext.getNeeds()){
                if(n!=null && need!=null) {
                    if (n.getNeedKey().equals(need.getNeedKey())) {
                        List<Chat> chats = ChatDAO.getInstance().fetchChatByNeedKey(n.getNeedKey());
                        for (Chat chat : chats) {
                            n.setMessagesNumber(chat.getQtyNewMessages());
                        }
                        n.setBidsNumber(need.getBidsNumber());
                        break;
                    }
                }
            }
           return  null;
        }


        @Override
        protected void onPostExecute( Void param ) {
            if (mAdapter!=null) {
                mAdapter.notifyDataSetChanged();
            }
        }

    }


    private class DeleteNeedAsyncTask extends AsyncTask<Integer , Void,  Void > {

        Integer position;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), null,
                    getResources().getString(R.string.please_wait), true);
            progress.show();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            position= params[0];
            if(position!=null) {
                if (AppConfig.dataProvider.deleteNeedByNeedKey(appContext.getNeeds().get(position).getNeedKey())) {
                    appContext.getNeeds().remove((int) position);
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute( Void param) {
            if (mAdapter!=null && position!=null) {
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyDataSetChanged();

            }
            progress.dismiss();
        }

    }


    class AsyncNeedDetailTask extends AsyncTask<Integer, Void, Void> {

        Integer position;

        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), null,
                    getResources().getString(R.string.please_wait), true);
            progress.show();
        }


        @Override
        protected Void doInBackground(Integer... params) {
            position=params[0];
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Intent intent = new Intent(getActivity(),NeedDetailActivity.class);
            intent.putExtra("needposition", position);
            getActivity().startActivity(intent);
            Message msg = handler.obtainMessage();
            msg.arg1 = AppConfig.PROGRESS_STOP;
            progress.dismiss();
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        IntentFilter filterAdd = new IntentFilter();
        filterAdd.addAction(INTENT_FILTER_NEED_CHANGED);
        getActivity().registerReceiver(chatUpdateReceiver, filterAdd);

        if (appContext.getNeeds().size()!=0) {
            mAdapter.notifyDataSetChanged();
        }
        Message msg = handler2.obtainMessage();
        msg.what=MESSAGES_READ;
        handler2.sendMessage(msg);
    }





    Handler handler2= new Handler(){
        public void handleMessage(final Message msg) {

            switch (msg.what){
                case MESSAGES_READ:
                    for (Need n: appContext.getNeeds()){
                        List<Chat> chats=ChatDAO.getInstance().fetchChatByNeedKey(n.getNeedKey());
                        for (Chat chat:chats){
                            n.setMessagesNumber(chat.getQtyNewMessages());
                        }

                    }
                    if (mAdapter!=null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };



}
