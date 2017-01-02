package com.agora.gcm;



import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.db.dao.ChatDAO;
import com.agora.db.dao.MessageDAO;
import com.agora.db.dao.NeedDAO;
import com.agora.entity.Chat;
import com.agora.entity.Message;
import com.agora.entity.Need;
import com.agora.msg.ChatListFragment;
import com.agora.msg.MessageFragment;
import com.agora.need.NeedListActivity;
import com.agora.need.NeedListFragment;


import java.util.Date;
import java.util.List;

public class MyGcmListenerService  extends Service{

    private static final String TAG = "MyGcmListenerService";


    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.i(TAG, message);
        if (message.startsWith(AppConfig.MSG_HEADER_M_T)) {
           //New message for chat
            String subString = message.substring(AppConfig.MSG_HEADER_M_T.length());
            Integer length= new Integer(subString.substring(0,subString.indexOf(":")));
            subString=subString.substring(subString.indexOf(":")+1);
            String text= subString.substring(0,length);
            Log.i(TAG,text);
            subString=subString.substring(subString.indexOf(":")+1);
            Long msgKey =  new Long(subString.substring(0,subString.indexOf(":")));
            Log.i(TAG,msgKey.toString());
            subString=subString.substring(subString.indexOf(":")+1);
            Long needKey=new Long(subString.substring(0,subString.indexOf(":")));
            subString=subString.substring(subString.indexOf(":")+1);
            Long userKey = new Long(subString);
            Log.i(TAG,userKey.toString());
            List<Chat> chatList= ChatDAO.getInstance().fetchChatByUserKeyNeedKey(userKey, needKey);
            Message msg= new Message();
            msg.setMessageKey(msgKey);
            msg.setText(text);
            msg.setStatus("D");
            msg.setIncoming(Boolean.TRUE);
            Chat chat=null;
            if (chatList.size()==0){
                chat= new Chat();
                chat.setNeedKey(needKey);
                chat.setQtyNewMessages(1);
                chat.setLastTextMessage(text);
                chat.setUserKey(userKey);
                chat.setSettlementDate(new Date(System.currentTimeMillis()));
                ChatDAO.getInstance().insert(chat);
                msg.setChatKey(chat.getChatKey());
            }else{
                chat= chatList.get(0);
                chat.setLastTextMessage(text);
                chat.setQtyNewMessages(chat.getQtyNewMessages() + 1);
                ChatDAO.getInstance().update(chat);
                msg.setChatKey(chat.getChatKey());
            }
            msg.setCreationDate(new Date(System.currentTimeMillis()));
            MessageDAO.getInstance().insert(msg);
//            Need need =NeedDAO.getInstance().fetchNeedByNeedKey(needKey);
            //need.setMessagesNumber(need.getMessagesNumber()+1);
            sendNotification(text);
            AppConfig.dataProvider.setMessageStatus(msgKey, AppConfig.MSG_RECEIVED);
            Intent intentMsg = new Intent(MessageFragment.INTENT_FILTER_MSG_ADDED);
            intentMsg.putExtra(MessageFragment.MESSAGE, msg);
            //sendBroadcast(intentMsg);
            Intent intentChat = new Intent(ChatListFragment.INTENT_FILTER_CHAT_CHANGED);
           // sendBroadcast(intentChat);
            Intent intentNeed= new Intent(NeedListFragment.INTENT_FILTER_NEED_CHANGED);
            intentNeed.putExtra("needKey",needKey);
           // sendBroadcast(intentNeed);
        } else if (message.startsWith(AppConfig.MSG_HEADER_M_S)) {
            String subString = message.substring(4);
            String status=subString.substring(0,subString.indexOf(":"));
            Log.i(TAG,status);
            subString=subString.substring(subString.indexOf(":")+1);
            Long messageKey=new Long(subString);
            Log.i(TAG, messageKey.toString());
            if (AppConfig.MSG_RECEIVED.equals(status) || AppConfig.MSG_READ.equals(status)) {
                MessageDAO dao=MessageDAO.getInstance();
                Message msg=dao.fetchMessagesByKey(messageKey);
                if(msg!=null) {
                    msg.setStatus(status);
                    dao.update(msg);
                    Intent intent = new Intent(MessageFragment.INTENT_FILTER_STATUS_MSG);
                    intent.putExtra(MessageFragment.MESSAGE_KEY, messageKey);
                    intent.putExtra(MessageFragment.MESSAGE_STATUS, status);
                    //sendBroadcast(intent);
                }
            }

        } else if (message.startsWith(AppConfig.MSG_HEADER_N_B)) {
            String subString = message.substring(4);
            Integer length= new Integer(subString.substring(0,subString.indexOf(":")));
            subString=subString.substring(subString.indexOf(":")+1);
            String text=subString.substring(0,subString.indexOf(":"));
            Log.i(TAG,text);
            subString=subString.substring(subString.indexOf(":")+1);
            Long needKey=new Long(subString);
            Intent intentNeed= new Intent(NeedListFragment.INTENT_FILTER_NEED_CHANGED);
            intentNeed.putExtra("needKey",needKey);
           // sendBroadcast(intentNeed);
            Log.i(TAG,needKey.toString());
            sendNotification(text);
        }

    }
    private void sendNotification(String message) {
        Intent intent = new Intent(this, NeedListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        //Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_logo)
                .setContentTitle(AppConfig.APP_NAME)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (preferences.getBoolean(AppConfig.PREF_KEY_NOTIF_VIB_NEW_BID,Boolean.FALSE)) {
            notificationBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        }

        if (preferences.getBoolean(AppConfig.PREF_KEY_NOTIF_SOUND_NEW_BID,Boolean.FALSE)) {
            notificationBuilder.setSound(Uri.parse("android.resource://"
                    + AppContext.getContext().getPackageName() + "/" + R.raw.sounds_notification));
        }

       // NotificationManager notificationManager =
         //       (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.notify(0, notificationBuilder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}