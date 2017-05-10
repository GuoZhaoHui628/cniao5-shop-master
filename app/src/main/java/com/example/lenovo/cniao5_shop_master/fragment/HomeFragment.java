package com.example.lenovo.cniao5_shop_master.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.adapter.HomeCatgoryAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.decoration.CardViewtemDecortion;
import com.example.lenovo.cniao5_shop_master.bean.HomeCampaign;
import com.example.lenovo.cniao5_shop_master.http.BaseCallback;
import com.example.lenovo.cniao5_shop_master.http.Contants;
import com.example.lenovo.cniao5_shop_master.http.OkHttpHelper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

import static android.R.string.ok;


public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View view;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private List<HomeCampaign> homeCampaignList;
    private HomeCatgoryAdapter homeCatgoryAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container,false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        requestCatgoryDatas();

        return  view;
    }



    public void requestCatgoryDatas(){

       okHttpHelper.get(Contants.API.CAMPAIGN_HOME, new BaseCallback<List<HomeCampaign>>() {

           @Override
           public void onBeforeRequest(Request request) {

           }

           @Override
           public void onFailure(Request request, Exception e) {

           }

           @Override
           public void onResponse(Response response) {

           }

           @Override
           public void onSuccess(Response response, List<HomeCampaign> homeCampaigns) {

                     showRecyHomeCompaign(homeCampaigns);
           }

           @Override
           public void onError(Response response, int code, Exception e) {

           }
       });

    }


    public void showRecyHomeCompaign(List<HomeCampaign> homeCampaigns){
        homeCatgoryAdapter = new HomeCatgoryAdapter(homeCampaigns,getContext());
        mRecyclerView.setAdapter(homeCatgoryAdapter);
        mRecyclerView.addItemDecoration(new CardViewtemDecortion());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

}
