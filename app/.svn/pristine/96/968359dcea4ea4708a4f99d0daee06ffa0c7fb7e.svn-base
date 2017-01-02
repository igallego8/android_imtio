package com.agora.bid;



import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.db.dao.MessageDAO;
import com.agora.entity.Bid;
import com.agora.entity.Chat;
import com.agora.msg.MessageActivity;
import com.agora.msg.MessageFragment;
import com.agora.util.UtilProcess;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class BidDetailActivity extends ActivityToolBarProgress {

    private BidDetailFragment fragment;
    private Integer bidPosition;
    private Bid bid;
    BitmapDrawable icon;
    private SimpleDraweeView image;
    private  ActionBar bar;
    private HandlerIcon handler;
    private MessageDAO dao= MessageDAO.getInstance();
    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_detail);
        super.onCreate();
        bar=getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        appContext=(AppContext)this.getApplicationContext();
        bidPosition = this.getIntent().getIntExtra("bidPosition", -1);
        bid=appContext.getBids().get(bidPosition);
        bar.setTitle(bid.getCompany().getCompanyName());
        bar.setDisplayUseLogoEnabled(true);
        bar.setHomeButtonEnabled(true);
        if (savedInstanceState == null) {
            fragment = BidDetailFragment.newInstance(bidPosition);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment10, fragment,"fragment10");
            transaction.commit();
        }else{
            fragment = (BidDetailFragment) getFragmentManager().findFragmentByTag("fragment10");
        }
        handler=new HandlerIcon();
        new AsynIconcTask().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bid_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       final int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.item_action_messages:
                Intent intent = new Intent(this, MessageActivity.class);
                intent.putExtra("chat",new Chat()) ;
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final class HandlerIcon extends Handler{

        public void handleMessage(Message msg) {
            bar.setIcon(icon);
        }

    }

    class AsynIconcTask extends AsyncTask<Void, Void, Void> {

        Bitmap image;
        protected void onPreExecute() {

        }

        protected Void doInBackground(Void... params) {

            Resources res = getResources();
            String logo= bid.getCompany().getLogo();
            if (logo!=null  && logo.trim().length()!=0) {
                image = AppConfig.dataProvider.getBitmap(AppConfig.IMAGES_URL + logo);
                image=UtilProcess.getCircularBitmap(image);
                icon= new BitmapDrawable(res,image);
            }
            return null;

        }

        protected void onPostExecute(Void param) {
            Message msg = handler.obtainMessage();
            msg.arg1 = AppConfig.PROGRESS_RUNNING;
            handler.sendMessage(msg);

        }
    }
}
