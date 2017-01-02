package com.agora.need;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.AmountFilter;
import com.agora.entity.CashPaymentFilter;
import com.agora.entity.Category;
import com.agora.entity.CreditCardPaymentFilter;
import com.agora.entity.DateFilter;
import com.agora.entity.Filter;
import com.agora.entity.Need;
import com.agora.entity.ShippingFilter;
import com.agora.image.ImageAttachActivity;
import com.agora.need.preferences.PreferenceDTO;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NeedNewActivity extends ActivityToolBarProgress implements NeedNewFragment.OnFragmentInteractionListener {


    public static final String TAG="NeedCreateActivity";
    private NeedNewFragment fragment;
    private Need need;
    private ProgressDialog progress;
    public static ArrayList<PreferenceDTO> preferences;
    private AppContext appContext;
    private CharSequence[] items = {"Set as Ringtone", "Set as Alarm"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_need_new);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appContext=(AppContext)getApplicationContext();
        need= new Need();
        need.setType("P");
        //need.setUser(appContext.getUser());
        Category category = (Category)getIntent().getSerializableExtra(AppConfig.CATEGORY);
        need.setCategory(category);
        if (savedInstanceState==null){
            appContext.setAttachedImagesConfirmed(false);
            appContext.getAttachedImages().clear();
            appContext.getThumbnailImages().clear();
            /*filters = new ArrayList<>();
            Filter f =  new ShippingFilter();
            f.setName(getString(R.string.shipping_service));
            f.setIcon(R.drawable.ic_local_shipping_black_36dp);
            filters.add(f);
            f = new CreditCardPaymentFilter();
            f.setName(getString(R.string.payment_credit_card));
            f.setIcon(R.drawable.ic_payment_black_36dp);
            filters.add(f);
            f = new CashPaymentFilter();
            f.setName(getString(R.string.payment_cash));
            f.setIcon(R.drawable.ic_local_atm_black_36dp);
            filters.add(f);
            f = new DateFilter();
            f.setName(getString(R.string.service_date));
            f.setIcon(R.drawable.ic_event_black_36dp);
            filters.add(f);*/
            preferences= new ArrayList<>();
            PreferenceDTO preference= new PreferenceDTO();
            preference.setTitle("Entrega");
            preference.setTitleIcon(R.drawable.ic_local_shipping_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            Map map= new HashMap<String, Boolean>();
            map.put("Domicilio", false);
            map.put("Punto de venta", false);
            preference.setParameters(map);
            preferences.add(preference);

            preference= new PreferenceDTO();
            preference.setTitle("Método de Pago");
            preference.setTitleIcon(R.drawable.ic_local_atm_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            map= new HashMap<String, Boolean>();
            map.put("Efectivo contra entrega", false);
            map.put("Tarjeta Crédito - Débito contra entrega", false);
            map.put("On Line", false);
            preference.setParameters(map);
            preferences.add(preference);


            preference= new PreferenceDTO();
            preference.setTitle("Prioridad");
            preference.setTitleIcon(R.drawable.ic_assignment_ind_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            map= new HashMap<String, Boolean>();
            map.put("Inmediata", false);
            preference.setParameters(map);
            preferences.add(preference);

            fragment = NeedNewFragment.newInstance(need, preferences);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, fragment,"fragment_need_new");
            transaction.commit();
        }else{
            fragment = (NeedNewFragment) getSupportFragmentManager().findFragmentByTag("fragment_need_new");
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_need_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
       switch(id){
           case R.id.action_attach:
            selectImage();
            break;
           case R.id.action_ok:
               need.setSettlementDate(new Date(System.currentTimeMillis()));
/*
               for (Filter f: filters){

                   if (f instanceof AmountFilter){
                       need.setBestOfferWeight(f.getWeight());
                       Log.i(TAG,"AmountFilter:"+f.getWeight());
                   }else if (f instanceof ShippingFilter){
                       need.setShipToClientDestinationWeight(f.getWeight());
                       Log.i(TAG, "ShippingFilter:" + f.getWeight());
                   }else if (f instanceof CreditCardPaymentFilter){
                       need.setCreditCardPaymentWeight(f.getWeight());
                       Log.i(TAG, "CreditCardPaymentFilter:" + f.getWeight());
                   }else if (f instanceof CashPaymentFilter){
                       need.setCashPaymentWeight(f.getWeight());
                       Log.i(TAG, "CashPaymentFilter:" + f.getWeight());
                   }else if (f instanceof DateFilter){
                       need.setExpirationDateWeight(f.getWeight());
                       Log.i(TAG, "DateFilter:" + f.getWeight());
                   }

               }*/

               for (PreferenceDTO p: preferences){
                   need.getPreferences().putAll(p.getParameters());
               }
               AsyncCreateNeedTask task = new AsyncCreateNeedTask();
               task.execute();
               break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void selectImage() {
        Intent intent = new Intent(NeedNewActivity.this, ImageAttachActivity.class);
        startActivity(intent);

    }



    private class AsyncCreateNeedTask extends AsyncTask<Void,Need,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(NeedNewActivity.this, null,
                    getResources().getString(R.string.please_wait), true);
            progress.show();

        }


        @Override
        protected Void doInBackground(Void... params) {
            Long needKey= AppConfig.dataProvider.createNeed(need,appContext.getUser().getUserKey());
            need.setNeedKey(needKey);
            appContext.getNeeds().add(need);
            if (appContext.isAttachedImagesConfirmed()){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                for (Bitmap bitmap: appContext.getAttachedImages()) {
                    if (bitmap != null) {
                        try {
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                            byte[] byte_arr = stream.toByteArray();
                            String encodedString = Base64.encodeToString(byte_arr, Base64.NO_WRAP);
                            sendImageHTTP(encodedString,needKey);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }



            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent =  new Intent(NeedNewActivity.this,NeedListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            NeedNewActivity.this.startActivity(intent);
            NeedNewActivity.this.finish();
            progress.dismiss();
        }
    }



    // Make Http call to upload Image to Java server
    public void sendImageHTTP(String encodedString, Long needKey) {
        HashMap<String,String> params = new HashMap<String,String> ();
        params.put("image",encodedString);
        params.put("key",needKey.toString());
        params.put("entity","need");
        AppConfig.dataProvider.uploadImage(params);

    }
    @Override
    public void afterDescriptionTextChanged(String text) {
        need.setDescription(text);
    }
}
