package com.agora.category;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.entity.Category;
import com.agora.listener.OnItemTouchListener;
import com.agora.need.NeedNewActivity;
import com.agora.util.BackgroundItemColor;
import com.agora.util.GenericAlertDialog;
import com.agora.util.UtilProcess;
import com.agora.util.listadapter.Item;
import com.agora.util.listadapter.ListItemAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Ivan on 13/09/15.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    private List<Category> mDataset;
    private  OnItemTouchListener onItemTouchListener;
    private  OnAdapterInteractionListener onAdapterInteractionListener;
    private ProgressDialog progress;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public CategoryListAdapter(List<Category> myDataset, OnItemTouchListener onItemTouchListener,OnAdapterInteractionListener onAdapterInteractionListener) {
        this.mDataset = myDataset;
        this.onItemTouchListener=onItemTouchListener;
        this.onAdapterInteractionListener=onAdapterInteractionListener;
    }

    public void setList(List<Category> myDataset){
        this.mDataset=myDataset;
    }

    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cv_category_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {

        CardView card= (CardView) holder.view.findViewById(R.id.card_view);
        card.setOnClickListener(new CardView.OnClickListener(){

            @Override
            public void onClick(final View v) {
                AdpaterAsyncTask task= new AdpaterAsyncTask();
                task.execute();

            }

             class AdpaterAsyncTask extends AsyncTask<Void, Void,   List<Category> > {

                 @Override
                 protected void onPreExecute() {
                     progress = ProgressDialog.show(holder.view.getContext(), null,
                             holder.view.getResources().getString(R.string.please_wait), true);
                     progress.show();

                 }


                @Override
                protected  List<Category>  doInBackground(Void... params) {
                    // everything in here gets executed in a separate thread
                    List<Category> list=  AppConfig.dataProvider.fetchSubCategories(mDataset.get(position).getCategoryKey());
                    return list;
                }

                 @Override
                 protected void onPostExecute(final  List<Category> result ) {
                     final AlertDialog.Builder alertDialog = new AlertDialog.Builder(holder.view.getContext());
                     LayoutInflater inflater = (LayoutInflater) holder.view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                     View convertView = (View) inflater.inflate(R.layout.alert_list, null);
                     Item[] items= new Item[result.size()];

                     for (int index=0;index<result.size();index++){
                         Category c=result.get(index);
                         items[index]=new Item(c.getDescription(),null,null);
                     }
                     ListItemAdapter adapter= new ListItemAdapter(holder.view.getContext(),items);
                     final GenericAlertDialog<ListItemAdapter> ds= new GenericAlertDialog<>(new GenericAlertDialog.AlertDialogListener(){
                         @Override
                         public void onClickItemListener(DialogInterface dialog, int item) {
                             Intent intent = new Intent(holder.view.getContext(), NeedNewActivity.class);
                             intent.putExtra(AppConfig.CATEGORY, result.get(item));
                             intent.setAction(Intent.ACTION_VIEW);
                             holder.view.getContext().startActivity(intent);

                         }
                     });
                     progress.dismiss();
                     ds.alertDialog(holder.view.getContext(), 0, adapter, null);


                 }

            }

        });
        TextView t = (TextView) holder.view.findViewById(R.id.category_description);
        t.setText(mDataset.get(position).getDescription());
        BackgroundItemColor color =UtilProcess.getRandomColor();
        RelativeLayout layout = (RelativeLayout) holder.view.findViewById(R.id.layout_icon);
        layout.setBackgroundColor(color.getColor1());
        RelativeLayout v= (RelativeLayout)holder.view.findViewById(R.id.layout_icon_2);
        v.setBackgroundColor(color.getColor2());
        //ImageView image =  (ImageView) holder.view.findViewById(R.id.category_logo);
        //image.setImageBitmap(mDataset.get(position).getImage());
        SimpleDraweeView image= (SimpleDraweeView) holder.view.findViewById(R.id.category_logo);
        Uri uri = Uri.parse(AppConfig.ICONS_URL + mDataset.get(position).getImageName());
        image.setImageURI(uri);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public interface OnAdapterInteractionListener {

        public void setProgressState(int state);
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
