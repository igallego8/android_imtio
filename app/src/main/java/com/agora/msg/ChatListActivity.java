package com.agora.msg;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.view.Menu;
import android.view.MenuItem;

import com.agora.R;
import com.agora.app.ActivityToolBarProgress;
import com.agora.db.dao.MessageDAO;
import com.agora.entity.Chat;
import com.agora.entity.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity  extends ActivityToolBarProgress implements ChatListFragment.OnFragmentInteractionListener {

    private ChatListFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        super.onCreate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Chat> chats= (ArrayList<Chat>)getIntent().getSerializableExtra("chats");
        if (savedInstanceState == null) {
            fragment = ChatListFragment.newInstance(chats);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment5, fragment, "fragment5");
            transaction.commit();
        }else{
            fragment = (ChatListFragment) getFragmentManager().findFragmentByTag("fragment5");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
