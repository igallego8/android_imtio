package com.agora.util.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.agora.R;

import java.util.List;

/**
 * Created by Ivan on 11/11/15.
 */
public class ListItemAdapter extends ArrayAdapter<Item> {

    private Context context;
    private ImageView icon;
    private TextView title;
    private TextView description;
    private Item [] items;


    public ListItemAdapter(Context context,Item []  items) {
        super(context,R.layout.adapter_item,items);
        this.context=context;
        this.items=items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Use super class to create the View
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adapter_item, parent, false);
        icon= (ImageView)view.findViewById(R.id.iv_item_img);
        title= (TextView)view.findViewById(R.id.tv_item_title);
        description= (TextView)view.findViewById(R.id.tv_item_description);
        Item item= items[position];
        if (item.getIcon()==null){
            icon.setVisibility(View.GONE);
        }else {
            icon.setImageResource(item.getIcon());
        }
        if (item.getTitle()==null){
            title.setVisibility(View.GONE );
        }else {
            title.setText(item.getTitle());
        }
        if (item.getDescription()==null){
            description.setVisibility(View.GONE);
        }else {
            description.setText(item.getDescription());
        }
        return view;
    }
}
