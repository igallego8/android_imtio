package com.agora.bid;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.app.AppContext;
import com.agora.entity.Bid;
import com.agora.entity.Need;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class BidDetailFragment extends Fragment {

    private TextView expirationDate;
    private TextView noImagesAttached;
    private TextView description;
    private RelativeLayout shippingService;
    private RelativeLayout paymentOnSiteService;
    private RelativeLayout paymentCreditCard;
    private Activity activity;


    public static BidDetailFragment newInstance(Integer bidPosition) {
        BidDetailFragment fragment = new BidDetailFragment();
        Bundle args = new Bundle();
        args.putInt("bidPosition", bidPosition);
        fragment.setArguments(args);
        return fragment;
    }

    public BidDetailFragment() {
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
        return inflater.inflate(R.layout.fragment_bid_detail, container, false);
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        description=(TextView)view.findViewById(R.id.tv_description);
        expirationDate = (TextView) view.findViewById(R.id.et_expiration_date);
        shippingService= (RelativeLayout)view.findViewById(R.id.layout_shipping_service);
        paymentOnSiteService= (RelativeLayout)view.findViewById(R.id.layout_payment_on_site);
        paymentCreditCard= (RelativeLayout)view.findViewById(R.id.layout_payment_credit_card);
        SimpleDraweeView thumbnail1 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail1);
        SimpleDraweeView thumbnail2 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail2);
        SimpleDraweeView thumbnail3 = (SimpleDraweeView) view.findViewById(R.id.iv_thumbnail3);
        noImagesAttached=(TextView) view.findViewById(R.id.tv_no_img_attached);
        Bundle bundle=getArguments();
        if (bundle!=null) {
            Integer bidPosition =bundle.getInt("bidPosition");
            if (bidPosition != null && bidPosition!=-1) {
                Bid bid= null;
                if (bid != null) {
                    shippingService.setVisibility(View.VISIBLE);
                    paymentCreditCard.setVisibility(View.VISIBLE);
                    paymentCreditCard.setEnabled(bid.isCreditCardPayment());
                    expirationDate.setText(AppConfig.dateFormatter.format(bid.getExpirationDate()));
                    description.setText(bid.getObservation());
                    String thumbnail="thumbnail";
                    if (bid.getImage1()!=null && !"".equals(bid.getImage1().trim())){
                        noImagesAttached.setVisibility(View.GONE);
                        thumbnail1.setVisibility(View.VISIBLE);
                        Uri uri = Uri.parse(AppConfig.IMAGES_URL + thumbnail+bid.getImage1());
                        thumbnail1.setImageURI(uri);
                    }
                    if (bid.getImage2()!=null && !"".equals(bid.getImage2().trim())){
                        noImagesAttached.setVisibility(View.GONE);
                        thumbnail2.setVisibility(View.VISIBLE);
                        Uri uri = Uri.parse(AppConfig.IMAGES_URL + thumbnail+bid.getImage2());
                        thumbnail2.setImageURI(uri);
                    }
                    if (bid.getImage3()!=null && !"".equals(bid.getImage3().trim())){
                        noImagesAttached.setVisibility(View.GONE);
                        thumbnail3.setVisibility(View.VISIBLE);
                        Uri uri = Uri.parse(AppConfig.IMAGES_URL + thumbnail+bid.getImage3());
                        thumbnail3.setImageURI(uri);

                    }
                }

            }
        }

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
