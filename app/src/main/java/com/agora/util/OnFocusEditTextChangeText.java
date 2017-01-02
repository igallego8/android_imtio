package com.agora.util;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.agora.R;

/**
 * Created by Ivan on 1/11/15.
 */
public class OnFocusEditTextChangeText implements View.OnFocusChangeListener {

    EditText editText;
    private Context context;
    int defaultTextResource;

    public OnFocusEditTextChangeText(EditText editText, int defaultTextResource, Context context){
        this.editText=editText;
        this.defaultTextResource=defaultTextResource;
        this.context=context;

    }
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (hasFocus){
            String text=context.getResources().getString(defaultTextResource);
            if (editText.getText().toString().equals(text)){
                editText.setText("");
            }
        } else {
            if (editText.getText().toString().trim().equals("")){
                editText.setText(context.getResources().getString(defaultTextResource));
            }
        }
    }

}
