package com.agora.deal;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppConfig;
import com.agora.bid.BidDealListHandler;
import com.agora.bid.BidListActivity;
import com.agora.entity.Company;
import com.agora.entity.Deal;
import com.agora.entity.Need;
import com.agora.entity.User;
import com.agora.need.NeedListActivity;
import com.agora.need.NeedListFragment;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;

public class DealActivity extends ActivityToolBarProgress {

    private ProgressDialog progress;
    private Fragment fragment;
    private Need need;
    private Deal deal;
    public static final String  ARG_NEED="need";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        need=(Need) getIntent().getSerializableExtra(ARG_NEED);

       /* if (savedInstanceState==null){
            fragment = DealFragment.newInstance(need);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_deal, fragment, "fragment_deal");
            transaction.commit();

        }else{
            fragment = (DealFragment) getFragmentManager().findFragmentByTag("fragment_deal");
        }*/

        new AsyncDealInfoTask().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id){
            case R.id.item_action_declain:
                break;

            case R.id.item_action_bid_info:
                Intent intent= new Intent(this, BidListActivity.class);
                intent.putExtra("need",need);
                intent.putExtra("handler", new BidDealListHandler(deal));
                startActivity(intent);
                break;

            case R.id.item_action_ok:
                intent= new Intent();
                intent.putExtra("need", need);
                this.setResult(100,intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    class AsyncDealInfoTask extends AsyncTask<Void, Void, Deal> {

        protected void onPreExecute() {
            progress = ProgressDialog.show(DealActivity.this, null,
                    getResources().getString(R.string.please_wait), true);
            progress.show();
        }

        @Override
        protected Deal doInBackground(Void... params) {
            deal= AppConfig.dataProvider.findDealByNeedKey(need.getNeedKey());
            return deal;
        }

        @Override
        protected void onPostExecute(Deal deal) {
            fragment = DealFragment.newInstance(deal);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_deal, fragment, "fragment_deal");
            transaction.commit();
            progress.dismiss();

        }
    }

}
