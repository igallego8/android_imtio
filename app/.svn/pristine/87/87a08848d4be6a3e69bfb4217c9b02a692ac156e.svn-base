package com.agora.util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.agora.util.listadapter.ListItemAdapter;

/**
 * Created by Ivan on 11/11/15.
 */
public class GenericAlertDialog <T extends ListAdapter> {

    private AlertDialogListener listener;

    public GenericAlertDialog(AlertDialogListener listener){
       this.listener=listener;
    }

    public void alertDialog(final Context context,final int position, T adapter ,String title){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        if (title!=null) {
            builder.setTitle("Bid options");
        }
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                listener.onClickItemListener(dialog,  item);
            }
        });
        android.app.AlertDialog alert = builder.create();
        if (title == null) {
            alert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        alert.show();
    }



    public interface AlertDialogListener{
        void onClickItemListener(DialogInterface dialog, int item);
    }
}
