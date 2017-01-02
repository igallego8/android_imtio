package com.agora.image;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.category.CategoryListAdapter;
import com.agora.entity.Category;
import com.agora.util.UtilProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageAttachFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageAttachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageAttachFragment extends Fragment implements ImageAttachListAdapter.OnAdapterInteractionListener {

    private AppContext appContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager manager;
    private Activity activity;
    private OnFragmentInteractionListener mListener;
    private ImageView image;
    private Uri imageURI;
    private boolean callDialog;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageAttachFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageAttachFragment newInstance(String param1, String param2) {
        ImageAttachFragment fragment = new ImageAttachFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public ImageAttachFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_attach, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appContext = (AppContext) view.getContext().getApplicationContext();
        if (appContext.getAttachedImages().size()==0){
            callDialog=true;
            for (int x=0; x<3;x++){
                appContext.getAttachedImages().add(null);
                appContext.getThumbnailImages().add(null);
            }

        }

        mRecyclerView = (RecyclerView) getView().findViewById(R.id.images_recycler_view);
        manager =new GridLayoutManager(getActivity(),3);
        image= (ImageView)  getView().findViewById(R.id.iv_image);
        for (int x=0; x<3;x++){
            if (appContext.getAttachedImages().get(x)!=null) {
                image.setImageBitmap(appContext.getAttachedImages().get(x));
                break;
            }
        }
        mAdapter = new ImageAttachListAdapter(appContext.getAttachedImages(),null,this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
        if (callDialog){
            openAttachDialog(0);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setProgressState(int state) {

    }

    @Override
    public void updateImage(int position) {
        image.setImageBitmap(appContext.getAttachedImages().get(position));
    }

    @Override
    public void openAttachDialog(int position) {
        final String[] items = { getResources().getString(R.string.camera),
                getResources().getString(R.string.gallery),getResources().getString(R.string.cancel) };
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.title_dialog_image_attach);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getResources().getString(R.string.camera))) {
                    getImageIntent(AppConfig.REQUEST_CAMERA);
                } else if (items[item].equals(getResources().getString(R.string.gallery))) {
                    getImageIntent(AppConfig.SELECT_FILE);
                } else {
                    dialog.dismiss();

                }
            }
        });
        builder.show();
    }

    @Override
    public void deleteItem(int position) {
        appContext.getAttachedImages().set(position, null);
        appContext.getThumbnailImages().set(position, null);
        mAdapter.notifyDataSetChanged();
        image.setImageBitmap(null);
        boolean isAllNull=true;
        for(Bitmap b: appContext.getAttachedImages()){
            if(b!=null){
                isAllNull=false;
            }
        }
        if(isAllNull){
            appContext.setAttachedImagesConfirmed(false);
        }
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onDestroyView() {

        if (mRecyclerView != null) {
            mRecyclerView.setItemAnimator(null);
            mRecyclerView.setAdapter(null);
            mRecyclerView = null;
        }
        mAdapter = null;
        manager=null;
        callDialog=false;
        super.onDestroyView();

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if ((requestCode == AppConfig.REQUEST_CAMERA || requestCode == AppConfig.SELECT_FILE) && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras!=null && extras.keySet().contains("data") ){
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                Bitmap thumbnail= UtilProcess.createThumbnail(imageBitmap);
                image.setImageBitmap(imageBitmap);
                for(int x= 0; x<appContext.getAttachedImages().size();x++){
                    if ( appContext.getAttachedImages().get(x)==null){
                        appContext.getAttachedImages().set(x, imageBitmap);
                        appContext.getThumbnailImages().set(x, thumbnail);
                        break;
                    }

                }
                mAdapter.notifyDataSetChanged();
            }else{
                imageURI = data.getData();
                String selectedImagePath= getPath(imageURI);
                appContext.getImageURIs().add(selectedImagePath);
                image.setImageURI(null);
                image.setImageDrawable(null);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap bitmap =BitmapFactory.decodeFile(selectedImagePath, options);
                Bitmap thumbnail= UtilProcess.createThumbnail(bitmap);
                Display display = activity.getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                float scaleHt =(float) width/bitmap.getWidth();
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap,width, (int) (bitmap.getWidth()*scaleHt), true);
                image.setImageBitmap(scaled);
                image.invalidate();
                for(int x= 0; x<appContext.getAttachedImages().size();x++){
                    if (appContext.getAttachedImages().get(x)==null){
                        appContext.getAttachedImages().set(x, scaled);
                        appContext.getThumbnailImages().set(x, thumbnail);
                        break;
                    }

                }
                mAdapter.notifyDataSetChanged();
            }

        }
    }


    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {

        Cursor  cursor=null;
        try {
            // just some safety built in
            if (uri == null) {
                // TODO perform some logging or show user feedback
                return null;
            }
            // try to retrieve the image from the media store first
            // this will only work for images selected from gallery
            String[] projection = {MediaStore.Images.Media.DATA};
              cursor=activity.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int column_index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
            // this is our fallback here
        }finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return uri.getPath();
    }

    public void onDestroy() {
        super.onDestroy();
        callDialog=false;
        /*if (cursor != null) {
            cursor.close();
        }*/

    }


    public void getImageIntent(int item){

        if (AppConfig.REQUEST_CAMERA==item) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, AppConfig.REQUEST_CAMERA);
        } else if (AppConfig.SELECT_FILE==item) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(
                    Intent.createChooser(intent, "Select File"),
                    AppConfig.SELECT_FILE);
        }
    }


}
