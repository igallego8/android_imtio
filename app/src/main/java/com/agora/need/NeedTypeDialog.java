package com.agora.need;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.agora.R;
import com.agora.category.CategoryListActivity;
import com.agora.entity.NeedType;

/**
 * Created by Ivan on 23/11/15.
 */
public class NeedTypeDialog extends Dialog implements
        android.view.View.OnClickListener{


    private CallbackTypeDialog callback;

    public NeedTypeDialog(Context context, CallbackTypeDialog callback) {
        super(context);
        this.callback=callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_need_create_type);
        ImageButton btNewProductNeed= (ImageButton) findViewById(R.id.ib_new_need_product);
        btNewProductNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryListActivity.class);
                intent.putExtra(CategoryListActivity.TYPE, NeedType.PRODUCT);
                getContext().startActivity(intent);
                NeedTypeDialog.this.dismiss();
            }
        });

        ImageButton btNewServiceNeed= (ImageButton) findViewById(R.id.ib_new_need_service);
        btNewServiceNeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getContext(),CategoryListActivity.class);
                intent.putExtra(CategoryListActivity.TYPE, NeedType.SERVICE);
                getContext().startActivity(intent);
                NeedTypeDialog.this.dismiss();*/

                Toast.makeText(v.getContext(),"Not implemente yet", Toast.LENGTH_LONG).show();
            }
        });


        Button cancel= (Button) findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeedTypeDialog.this.dismiss();
                callback.cancel();
            }
        });

    }



    @Override
    public void onClick(View v) {

    }


    public interface CallbackTypeDialog {

        void cancel();

    }

}
