package com.agora.image;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.entity.Category;
import com.agora.listener.OnItemTouchListener;
import com.agora.util.BackgroundItemColor;
import com.agora.util.UtilProcess;

import java.util.List;

/**
 * Created by Ivan on 13/09/15.
 */
public class ImageAttachListAdapter extends RecyclerView.Adapter<ImageAttachListAdapter.ViewHolder> {
    private List<Bitmap> mDataset;
    private  OnAdapterInteractionListener onAdapterInteractionListener;



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

    public ImageAttachListAdapter(List<Bitmap> myDataset, OnItemTouchListener onItemTouchListener, OnAdapterInteractionListener onAdapterInteractionListener) {
        this.mDataset = myDataset;
        this.onAdapterInteractionListener=onAdapterInteractionListener;
    }

    public void setList(List<Bitmap> myDataset){
        this.mDataset=myDataset;
    }

    @Override
    public ImageAttachListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_image_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        CardView card= (CardView) holder.view.findViewById(R.id.card_view);
        ImageView icon= (ImageView)  holder.view.findViewById(R.id.i_attach_icon);
        ImageView image= (ImageView) holder.view.findViewById(R.id.i_attach_image);
        card.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataset.get(position)!=null) {
                    onAdapterInteractionListener.updateImage(position);
                }else{
                    onAdapterInteractionListener.openAttachDialog(position);
                }
            }

        });

        card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.view.getContext());

                builder.setMessage(R.string.delete_image_question)
                        .setTitle(R.string.title_delete_image);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onAdapterInteractionListener.deleteItem(position);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        if (mDataset.get(position)!=null){
            image.setImageBitmap(mDataset.get(position));
            image.setVisibility(View.VISIBLE);
            icon.setVisibility(View.INVISIBLE);
        }else{
            image.setVisibility(View.INVISIBLE);
            icon.setVisibility(View.VISIBLE);
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAdapterInteractionListener {

        public void setProgressState(int state);

        public void updateImage(int position);

        public void openAttachDialog(int position);

        public void deleteItem(int position);
    }

    // Define the Handler that receives messages from the thread and update the progress
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int current = msg.arg1;
            if(onAdapterInteractionListener!=null) {
                if (current == AppConfig.PROGRESS_STOP) {
                    onAdapterInteractionListener.setProgressState(AppConfig.PROGRESS_STOP);
                } else {
                    onAdapterInteractionListener.setProgressState(AppConfig.PROGRESS_RUNNING);
                }
            }
        }
    };

}
