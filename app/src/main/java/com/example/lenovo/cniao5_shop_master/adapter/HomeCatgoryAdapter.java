package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.HomeCampaign;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ${GuoZhaoHui} on 2016/12/9.
 * Abstract:
 */

public class HomeCatgoryAdapter extends RecyclerView.Adapter<HomeCatgoryAdapter.ViewHolder>{


    private  static int VIEW_TYPE_L=0;
    private  static int VIEW_TYPE_R=1;

    private LayoutInflater mInflater;

    private Context mContext;


    private List<HomeCampaign> mDatas;

    public HomeCatgoryAdapter(List<HomeCampaign> datas,Context context){
        mDatas = datas;
        mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        mInflater = LayoutInflater.from(parent.getContext());
        if(viewType==VIEW_TYPE_R){
            return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview2,parent,false));
        }else{
            return  new ViewHolder(mInflater.inflate(R.layout.template_home_cardview,parent,false));
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HomeCampaign homeCampaign = mDatas.get(position);
        holder.textTitle.setText(homeCampaign.getTitle());

        Picasso.with(mContext).load(homeCampaign.getCpOne().getImgUrl()).into(holder.imageViewBig);
        Picasso.with(mContext).load(homeCampaign.getCpTwo().getImgUrl()).into(holder.imageViewSmallTop);
        Picasso.with(mContext).load(homeCampaign.getCpThree().getImgUrl()).into(holder.imageViewSmallBottom);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {

        if(position % 2==0){
            return  VIEW_TYPE_R;
        }
        else return VIEW_TYPE_L;

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

       TextView  textTitle;
       ImageView imageViewBig;
       ImageView imageViewSmallTop;
       ImageView imageViewSmallBottom;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.text_title);
            imageViewBig = (ImageView) itemView.findViewById(R.id.imgview_big);
            imageViewSmallTop = (ImageView) itemView.findViewById(R.id.imgview_small_top);
            imageViewSmallBottom = (ImageView) itemView.findViewById(R.id.imgview_small_bottom);

        }
    }

}
