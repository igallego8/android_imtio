package com.agora.bid;


import android.app.FragmentTransaction;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppContext;
import com.agora.entity.Bid;
import com.agora.entity.Need;
import com.agora.listener.ProgressBarStateListener;


public class BidListActivity extends ActivityToolBarProgress implements ProgressBarStateListener {

    private BidListFragment fragment;

    public static  final String TAG="BidListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_list);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try{
            Need need= AppContext.getNeeds().get(getIntent().getIntExtra("needposition",-1));
            GenericListHandler <Bid> handler =new BidNeedListHandler(need.getNeedKey());
        if (savedInstanceState==null){
            fragment = BidListFragment.newInstance(need, handler);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment5, fragment, "fragment5");
            transaction.commit();

        }else{
            fragment = (BidListFragment) getFragmentManager().findFragmentByTag("fragment5");
        }
        }catch(IndexOutOfBoundsException ex){
            Log.e(TAG,ex.getMessage());
            return;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bid_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setProgressState(int state) {
        setProgressBarStatus(state);
    }


}
