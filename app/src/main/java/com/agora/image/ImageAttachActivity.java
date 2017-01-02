package com.agora.image;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppContext;

import java.util.List;

public class ImageAttachActivity extends ActivityToolBarProgress implements ImageAttachFragment.OnFragmentInteractionListener {

    private AppContext appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_attach);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appContext = (AppContext) this.getApplicationContext();
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if (!appContext.isAttachedImagesConfirmed()){
            appContext.getAttachedImages().clear();
            appContext.getThumbnailImages().clear();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_attach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case R.id.action_ok:
                boolean isAllNull=true;
                for(Bitmap b: appContext.getAttachedImages()){
                    if(b!=null){
                        isAllNull=false;
                    }
                }
                if(isAllNull){
                    appContext.setAttachedImagesConfirmed(false);
                }else {
                    appContext.setAttachedImagesConfirmed(true);
                }
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment=getFragmentManager().findFragmentById(R.id.fragment8);
            fragment.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
