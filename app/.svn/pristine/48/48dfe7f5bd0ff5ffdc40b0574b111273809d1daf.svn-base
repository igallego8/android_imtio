package com.agora.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.Auth;
import com.agora.entity.User;
import com.agora.login.LoginActivity;
import com.agora.login.SignUpActivity;
import com.agora.main.SplashActivity;
import com.agora.need.NeedListActivity;


import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DemoActivity extends Activity implements ViewPager.OnPageChangeListener {

    private LinearLayout dotContainer;
    private LinearLayout buttonsContainer;
    private Button btJoin;
    private Button btNext;
    private TextView title;
    private int index;
    private RelativeLayout container;
    public  int empty_dot_size;
    public  int filled_dot_size;
    public  int margin;
    private CustomPagerAdapter pagerAdapter;
    private ViewPager pager;
    private List<ImageView> dotList= new ArrayList<>();
    private int dpHeight;
    private int dpWidth;
    private List<DemoDTO> data= new ArrayList<>();
    private AppContext appContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_pager);
        appContext =(AppContext) this.getApplicationContext();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        Long userKey=settings.getLong("userKey",0L);
         if(userKey!=0L) {
             String name=settings.getString("userName","");
             String lastName=settings.getString("lastName", "");
             String idGCM =settings.getString("idGCM", "");
             User user= new User();
             user.setName(name);
             user.setLastName(lastName);
             user.setUserKey(userKey);
             Auth auth= new Auth();
             auth.setIdGCM(idGCM);
             appContext.setUser(user);
             needListIntent();
         }else {

             title = (TextView) findViewById(R.id.tv_title);
             if (savedInstanceState == null) {

                 String skipMessage = settings.getString(AppConfig.PREF_KEY_SKIP_NEED_TUTORIAL, "NOT checked");
                 if (!skipMessage.equals("checked")) {

                     DemoDTO d = new DemoDTO();
                     d.setImage(R.drawable.logo_2);
                     d.setText(this.getResources().getString(R.string.welcome_info));
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_need_new);
                     d.setText("Registre las necesidades usando el boton (+) y pronto recibirá ofertas de proveedores.");
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_need_category);
                     d.setText("Encontrará gran varidad de categorías de productos y servicios.");
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_need_description);
                     d.setText("Ayúdanos a saber con claridad la necesidad. Entre más  detallado sea la descripción, más acertadas serán las ofertas que recibirá.");
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_need_attach);
                     d.setText("Anexa imágenes si necesita mostrar gráficamente lo que necesita.");
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_need_pref);
                     d.setText("Informa cuáles son sus prioridades en términos de  tipo de pago, tiempo y servicio de entrega.");
                     data.add(d);
                     d = new DemoDTO();
                     d.setImage(R.drawable.demo_bid_info);
                     d.setText("Seleccione la oferta que cumpla con sus necesidades.");
                     data.add(d);

                     pager = (ViewPager) findViewById(R.id.view_pager);
                     pagerAdapter = new CustomPagerAdapter(this, data);
                     pager.setAdapter(pagerAdapter);
                     container = (RelativeLayout) findViewById(R.id.container);
                     pager.addOnPageChangeListener(this);
                     DisplayMetrics screenDensity = this.getResources().getDisplayMetrics();
                     dpHeight = (int) (screenDensity.heightPixels / screenDensity.density);
                     dpWidth = (int) (screenDensity.widthPixels / screenDensity.density);
                     dotContainer = (LinearLayout) findViewById(R.id.dot_container);
                     buttonsContainer = (LinearLayout) findViewById(R.id.button_container);
                     btJoin = (Button) findViewById(R.id.bt_join);
                     btJoin.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             join();
                         }
                     });
                     btNext  = (Button) findViewById(R.id.bt_next);
                     btNext.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             ++index;
                             onPageSelected(index);
                             pager.setCurrentItem(index);
                         }
                     });
                     filled_dot_size = (int) getResources().getDimension(R.dimen.filled_dot_size);
                     empty_dot_size = (int) getResources().getDimension(R.dimen.empty_dot_size);
                     margin = (int) getResources().getDimension(R.dimen.margin_dot_size);
                     LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                     lp.setMargins(margin, 0, margin, 0);
                     for (int x = 0; x < data.size(); x++) {
                         ImageView dot = new ImageView(this);
                         dot.setLayoutParams(lp);
                         dotList.add(dot);
                         dotContainer.addView(dot);
                     }
                     updateDots();
                 } else {
                     join();
                 }
             }
         }
    }


    public void join(){
        Intent mainIntent = new Intent(this, SplashActivity.class);
        this.startActivity(mainIntent);
        this.finish();
    }


    public void updateDots(){
        dotList.get(index).setBackground(getResources().getDrawable(R.drawable.solid_dot));
        dotList.get(index).setMinimumHeight(filled_dot_size);
        dotList.get(index).setMinimumWidth(filled_dot_size);
        dotList.get(index).setAlpha(1f);

        if (index==0){
            title.setText(getResources().getString(R.string.welcome));

        }else{
            title.setText(getResources().getString(R.string.do_you_know));
        }
        for (int x=0;x<dotList.size();x++) {
            if(x!=index){
                dotList.get(x).setMinimumHeight(empty_dot_size);
                dotList.get(x).setMinimumWidth(empty_dot_size);
                dotList.get(x).setBackground(getResources().getDrawable(R.drawable.empty_dot));
                dotList.get(x).setAlpha(0.30f);

            }
        }

        if (index==dotList.size()-1){
            btNext.setVisibility(View.GONE);
            btJoin.setVisibility(View.VISIBLE);
        }else{
            btNext.setVisibility(View.VISIBLE);
            btJoin.setVisibility(View.GONE);
        }


    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        index=position;
        updateDots();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void needListIntent(){
        Intent mainIntent = new Intent(this, NeedListActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
