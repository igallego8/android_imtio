package com.agora.need;

import android.content.Context;


import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.agora.R;

/**
 * Created by Ivan on 11/10/15.
 */
public class NeedAttachActionProvider  extends ActionProvider {


    Context mContext;
    public NeedAttachActionProvider(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public View onCreateActionView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.action_provider_need_attach,null);
        return view;
    }



}
