package com.agora.deal;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agora.R;
import com.agora.app.AppConfig;
import com.agora.entity.Bid;
import com.agora.entity.Company;
import com.agora.entity.Deal;
import com.agora.entity.Need;
import com.agora.entity.User;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;


public class DealFragment extends Fragment {

    private ProgressDialog progress;
    private Deal deal;
    private OnFragmentInteractionListener mListener;
    public static final String  ARG_DEAL="deal";
    private Company company;
    private Context context;
    private TextView companyName;
    private TextView companyRut;
    private TextView companyAddress;
    private TextView companyCity;
    private TextView companyCountry;
    private TextView companyPhone;
    private TextView companyWebPage;
    private TextView companyEmail;
    private TextView companyUserName;
    private TextView companyUserPhone;
    private SimpleDraweeView image;
    private FrameLayout frameLogo;


    public DealFragment() {
        // Required empty public constructor
    }


    public static DealFragment newInstance(Deal deal) {
        DealFragment fragment = new DealFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DEAL,deal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            deal = (Deal)getArguments().getSerializable(ARG_DEAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal, container, false);

    }


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        companyName=(TextView)view.findViewById(R.id.tv_company_name);
        companyRut=(TextView)view.findViewById(R.id.tv_company_rut);
        companyAddress=(TextView)view.findViewById(R.id.tv_company_address);
        companyCity=(TextView)view.findViewById(R.id.tv_company_city);
        companyCountry=(TextView)view.findViewById(R.id.tv_company_country);
        companyEmail=(TextView)view.findViewById(R.id.tv_company_email);
        companyPhone=(TextView)view.findViewById(R.id.tv_company_phone);
        companyWebPage=(TextView)view.findViewById(R.id.tv_company_webpage);
        companyUserName=(TextView)view.findViewById(R.id.tv_user_name);
        companyUserPhone=(TextView)view.findViewById(R.id.tv_user_phone);
        image= new  SimpleDraweeView(view.getContext());
        frameLogo = (FrameLayout)view.findViewById(R.id.frame_logo);
        Company company=deal.getCompany();
        if (company != null){
            companyName.setText(company.getCompanyName());
            companyRut.setText(company.getRut());
            companyAddress.setText(company.getAddress());
            companyCity.setText(company.getCity());
            companyCountry.setText(company.getCountry());
            companyEmail.setText(company.getEmail());
            companyPhone.setText(company.getPhone());
            companyWebPage.setText(company.getWebpage());

            if (company.getLogo()!=null) {
                GenericDraweeHierarchyBuilder builder =
                        new GenericDraweeHierarchyBuilder(getResources());
                GenericDraweeHierarchy hierarchy = builder
                        .setFadeDuration(300)
                        .setRoundingParams(new RoundingParams().setRoundAsCircle(true))
                        .build();
                Uri uri = Uri.parse(AppConfig.IMAGES_URL + company.getLogo());
                image.setImageURI(uri);
                image.setHierarchy(hierarchy);
                image.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                frameLogo.addView(image);
            }else{
                frameLogo.setVisibility(View.GONE);
            }
        }

        User user= deal.getUser();
        if (user!=null){
            companyUserPhone.setText(user.getEmail());
            companyUserName.setText(user.getName() + " " + user.getLastName()) ;
        }


    }

        // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(Uri uri);
    }



}
