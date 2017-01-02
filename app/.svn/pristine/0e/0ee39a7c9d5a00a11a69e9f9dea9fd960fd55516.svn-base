package com.agora.bid;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.Bid;
import com.agora.entity.Need;
import com.agora.image.ImageDialogFragment;
import com.agora.listener.OnItemTouchListener;
import com.agora.util.UtilProcess;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ivan on 13/09/15.
 */
public class BidListImageAdapter extends RecyclerView.Adapter<BidListImageAdapter.ViewHolder> {
    private List<String> mDataset= new ArrayList<>();
    private OnItemTouchListener onItemTouchListener;
    private int position;

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
    public BidListImageAdapter(List<String> myDataset, OnItemTouchListener onItemTouchListener, int position) {
        mDataset = myDataset;
        this.onItemTouchListener=onItemTouchListener;
        this.position=position;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BidListImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_bid_image_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int pos) {

        FrameLayout layoutIcon = (FrameLayout) holder.view.findViewById(R.id.container);
        layoutIcon.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemTouchListener.onCardViewTap(holder.view, position,BidListFragment.IMG_SHOW_ACTION);
            }

        });
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(holder.view.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
                .build();
        FrameLayout frameImage= (FrameLayout)holder.view.findViewById(R.id.frame_img);
        SimpleDraweeView image= new  SimpleDraweeView(holder.view.getContext());
        Uri uri = Uri.parse(AppConfig.IMAGES_URL + mDataset.get(pos));
        image.setImageURI(uri);
        image.setHierarchy(hierarchy);
        image.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        frameImage.addView(image);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
