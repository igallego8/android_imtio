package com.agora.need.preferences;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.entity.AmountFilter;
import com.agora.entity.DateFilter;
import com.agora.entity.Filter;
import com.agora.entity.PaymentTypeFilter;
import com.agora.entity.ShippingFilter;
import com.agora.listener.OnStartDragListener;
import com.agora.util.helper.ItemTouchHelperAdapter;
import com.agora.util.helper.ItemTouchHelperViewHolder;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ivan on 13/09/15.
 */
public class NeedPreferenceAdapter extends RecyclerView.Adapter<NeedPreferenceAdapter.ViewHolder>  {


    private List<PreferenceDTO> mDataset;
    private final OnStartDragListener mDragStartListener;
    private Fragment fragment;
    private AppCompatCheckBox checkBox;



    public static class ViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    public NeedPreferenceAdapter(List<PreferenceDTO> myDataset, OnStartDragListener dragStartListener, Fragment fragment) {
        this.mDataset = myDataset;
        mDragStartListener = dragStartListener;
        this.fragment=fragment;
    }

    public void setList(List<PreferenceDTO> myDataset){
        this.mDataset=myDataset;
    }

    @Override
    public NeedPreferenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_need_preference_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PreferenceDTO preferences=mDataset.get(position);

        ImageView iconTitle = (ImageView) holder.view.findViewById(R.id.iv_pref_icon_title);
        iconTitle.setImageResource(preferences.getTitleIcon());
        TextView title = (TextView) holder.view.findViewById(R.id.tv_pref_title);

        title.setText(preferences.getTitle());

        ImageView iconColumn = (ImageView) holder.view.findViewById(R.id.iv_pref_icon_column);
        iconColumn.setImageResource(preferences.getTitleIconColumn());
        TextView textColumn = (TextView) holder.view.findViewById(R.id.tv_pref_title_column);
        textColumn.setText(preferences.getTitleColumn());

        LinearLayout layoutPreference= (LinearLayout)holder.view.findViewById(R.id.layout_pref_list);


        int index=0;
        for (final String key: preferences.getParameters().keySet()){
            RelativeLayout layout= new RelativeLayout(holder.view.getContext());
            RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


            layout.setLayoutParams(params);

            TextView prefName= new TextView(holder.view.getContext());
            prefName.setId("TextView".hashCode() + key.hashCode());
            prefName.setText(key);
            prefName.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            RelativeLayout.LayoutParams paramsCheckBox = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            paramsCheckBox.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);



            checkBox= new AppCompatCheckBox(holder.view.getContext());
            checkBox.setId("CheckBox".hashCode() + key.hashCode());
            checkBox.setLayoutParams(paramsCheckBox);
            Boolean value= preferences.getParameters().get(key);
            if (value==null){
                value= false;
            }
            checkBox.setChecked(value);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    preferences.getParameters().put(key, isChecked);
                }
            });
            index++;
            layout.addView(prefName);
            layout.addView(checkBox);
            layout.setId(index);
            layoutPreference.addView(layout);


        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();

    }


}
