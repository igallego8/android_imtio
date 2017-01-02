package com.agora.msg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agora.R;
import com.agora.entity.Conversation;
import com.agora.entity.Message;
import com.agora.listener.OnItemTouchListener;
import com.agora.util.UtilProcess;

import java.util.List;

/**
 * Created by Ivan on 13/09/15.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    private List<Conversation> mDataset;

    public Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MessageListAdapter(List<Conversation> myDataset, OnItemTouchListener listener, Context context) {
        mDataset = myDataset;
        this.context=context;


    }

    // Create new views (invoked by the layout manager)
    @Override
    public MessageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_msg_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //...
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
       // holder..setText(mDataset[position]);
        /*TextView t= (TextView) holder.view.findViewById(R.id.company_name);
        t.setText(mDataset.get(position).getCompany().getCompanyName());
        TextView text= (TextView) holder.view.findViewById(R.id.message_text);
        text.setText(mDataset.get(position).getText());
        TextView textIcon= (TextView) holder.view.findViewById(R.id.company_name_icon);*/
       // textIcon.setText(mDataset.get(position).getCompany().getCompanyName().substring(0, 1));
       // textIcon.setBackgroundDrawable(UtilProcess.getRoundColorDrawable());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
