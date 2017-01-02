package com.agora.need;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.app.AppContext;
import com.agora.entity.Need;
import com.agora.image.ImageDialogFragment;
import com.agora.need.preferences.PreferenceDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NeedDetailActivity extends ActivityToolBarProgress implements NeedDetailFragment.OnFragmentInteractionListener {

    private Long categoryKey;
    private NeedDetailFragment fragment;
    private  Integer needPosition;
    private Need need;
    public static ArrayList<PreferenceDTO> preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_detail);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        need = AppContext.getNeeds().get((Integer)getIntent().getSerializableExtra("needposition"));

        if (savedInstanceState == null) {
            // do whatever

            preferences= new ArrayList<>();
            PreferenceDTO preference= new PreferenceDTO();
            preference.setTitle("Entrega");
            preference.setTitleIcon(R.drawable.ic_local_shipping_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            Map map= new HashMap<String, Boolean>();
            map.put("Domicilio", need.getPreferences().get("Domicilio"));
            map.put("Punto de venta", need.getPreferences().get("Punto de venta"));
            preference.setParameters(map);
            preferences.add(preference);

            preference= new PreferenceDTO();
            preference.setTitle("Método de Pago");
            preference.setTitleIcon(R.drawable.ic_local_atm_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            map= new HashMap<String, Boolean>();
            map.put("Efectivo contra entrega",  need.getPreferences().get("Efectivo contra entrega"));
            map.put("Tarjeta Crédito - Débito contra entrega",  need.getPreferences().get("Tarjeta Crédito - Débito contra entrega"));
            map.put("On Line",  need.getPreferences().get("On Line"));
            preference.setParameters(map);
            preferences.add(preference);


            preference= new PreferenceDTO();
            preference.setTitle("Prioridad");
            preference.setTitleIcon(R.drawable.ic_assignment_ind_black_36dp);
            preference.setTitleColumn("");
            preference.setTitleIconColumn(R.drawable.ic_warning_black_24dp);
            map= new HashMap<String, Boolean>();
            map.put("Inmediata", need.getPreferences().get("Inmediata"));
            preference.setParameters(map);
            preferences.add(preference);

            fragment = NeedDetailFragment.newInstance(need,preferences);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment9, fragment,"fragment9");
            //transaction.replace(R.id.fragment7, fragment);
            // transaction.addToBackStack(null);
            transaction.commit();


        }else{
            fragment = (NeedDetailFragment) getFragmentManager().findFragmentByTag("fragment9");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_need_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.action_attach) {
            showEditDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        ArrayList<String> imageList= new ArrayList<>();
        if (need.getImage1()!=null){
            imageList.add(need.getImage1());
        }
        if (need.getImage2() != null) {
            imageList.add(need.getImage2());
        }
        if (need.getImage3() != null) {
            imageList.add(need.getImage3());
        }
        ImageDialogFragment imageDialog = ImageDialogFragment.newInstance(imageList);
        imageDialog.show(fm, "fragment_image");
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
