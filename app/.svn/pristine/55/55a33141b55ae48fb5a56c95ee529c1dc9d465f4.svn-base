package com.agora.need;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.bid.BidListActivity;
import com.agora.bid.BidNeedListHandler;
import com.agora.db.dao.ChatDAO;
import com.agora.deal.DealActivity;
import com.agora.deal.DealFragment;
import com.agora.entity.Chat;
import com.agora.entity.Need;
import com.agora.listener.OnItemTouchListener;
import com.agora.msg.ChatListActivity;
import com.agora.util.BackgroundItemColor;
import com.agora.util.ExpandableTextView;
import com.agora.util.UtilProcess;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 13/09/15.
 */
public class NeedListAdapter extends RecyclerView.Adapter<NeedListAdapter.ViewHolder> {
    private List<Need> mDataset;
    private LinearLayout layoutDealIcon;
    private LinearLayout layoutBidIcons;

    private OnItemTouchListener onItemTouchListener;

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
    public NeedListAdapter(List<Need> myDataset, OnItemTouchListener onItemTouchListener) {
        this.mDataset = myDataset;
        this.onItemTouchListener=onItemTouchListener;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public NeedListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_need_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RelativeLayout layoutIcon = (RelativeLayout) holder.view.findViewById(R.id.layout_need);
        layoutIcon.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemTouchListener.onCardViewTap(holder.view, position, 0);
            }

        });
        final Need need= mDataset.get(position);
        TextView categoryDescription = (TextView) holder.view.findViewById(R.id.tv_category_description);
        categoryDescription.setText(need.getCategory().getDescription());
        ExpandableTextView needDescription = (ExpandableTextView) holder.view.findViewById(R.id.tv_need_description);
        needDescription.setText(need.getDescription());
        BackgroundItemColor color =UtilProcess.getRandomColor();
        FrameLayout layout = (FrameLayout) holder.view.findViewById(R.id.frame_icon);
        layoutDealIcon= (LinearLayout) holder.view.findViewById(R.id.layout_deal);

        layoutDealIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.view.getContext(), DealActivity.class);
                intent.putExtra(DealActivity.ARG_NEED,need);
                holder.view.getContext().startActivity(intent);
            }
        });
        layoutBidIcons= (LinearLayout) holder.view.findViewById(R.id.layout_bid);


        if (need.getDealKey()==null || need.getDealKey().longValue()==0){
            layoutDealIcon.setVisibility(View.GONE );
            layoutBidIcons.setVisibility(View.VISIBLE);
        }else{
            layoutBidIcons.setVisibility(View.GONE);
            layoutDealIcon.setVisibility(View.VISIBLE );
        }
       // layout.setBackgroundColor(color.getColor1());
        layout.setBackgroundDrawable(UtilProcess.getRoundColorDrawable());
        LinearLayout bidLayout= (LinearLayout) holder.view.findViewById(R.id.layout_bid);
        bidLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemTouchListener.onButton1Click(v,position);

            }
        });
        LinearLayout messageLayout= (LinearLayout) holder.view.findViewById(R.id.layout_message);
        messageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), ChatListActivity.class);
                ArrayList<Chat> chatList = (ArrayList)ChatDAO.getInstance().fetchChatByNeedKey(mDataset.get(position).getNeedKey());
                intent.putExtra("chats",chatList);
                v.getContext().startActivity(intent);
            }
        });

        LinearLayout frameMgs = (LinearLayout) holder.view.findViewById(R.id.frame_message_bubble);
        TextView qty= (TextView) holder.view.findViewById(R.id.tv_qty_message);
        if (mDataset.get(position).getMessagesNumber()>0) {
            frameMgs.setVisibility(View.VISIBLE);
            qty.setText("+"+mDataset.get(position).getMessagesNumber());
        }else{
            frameMgs.setVisibility(View.GONE);
            qty.setText("");
        }
        LinearLayout frameBids = (LinearLayout) holder.view.findViewById(R.id.frame_bid_bubble);
        TextView bidQty= (TextView) holder.view.findViewById(R.id.tv_qty_bids);
        if (mDataset.get(position).getBidsNumber()>0) {
            frameBids.setVisibility(View.VISIBLE);
            bidQty.setText("+"+mDataset.get(position).getBidsNumber());
        }else{
            frameBids.setVisibility(View.GONE);
            bidQty.setText("");
        }

        SimpleDraweeView image= (SimpleDraweeView) holder.view.findViewById(R.id.iv_category_logo);
        Uri uri = Uri.parse(AppConfig.ICONS_URL + (mDataset.get(position).getCategory().getImageName()));
        image.setImageURI(uri);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
