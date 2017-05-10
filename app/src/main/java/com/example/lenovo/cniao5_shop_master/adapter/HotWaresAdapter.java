package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.cniao5_shop_master.CartProvider;
import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.ShoppingCart;
import com.example.lenovo.cniao5_shop_master.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class HotWaresAdapter extends RecyclerView.Adapter<HotWaresAdapter.ViewHolder>  {



    private List<Wares> mDatas;

    private LayoutInflater mInflater;

    private Context mContext;

    private CartProvider provider;

    public HotWaresAdapter(List<Wares> wares,Context context){
        mDatas = wares;
        this.mContext = context;

        provider = new CartProvider(context);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.template_hot_wares,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Wares wares = getData(position);

        holder.draweeView.setImageURI(Uri.parse(wares.getImgUrl()));
        holder.textTitle.setText(wares.getName());
        holder.textPrice.setText("￥"+wares.getPrice());
        holder.btShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                provider.put(converData(wares));
                Toast.makeText(mContext,"添加成功",Toast.LENGTH_SHORT).show();
            }
        });



    }

    public ShoppingCart converData(Wares bean){

        ShoppingCart cart = new ShoppingCart();

        cart.setId(bean.getId());
        cart.setDescription(bean.getDescription());
        cart.setImgUrl(bean.getImgUrl());
        cart.setPrice(bean.getPrice());
        cart.setName(bean.getName());

        return cart;

    }


    public Wares getData(int position){

        return mDatas.get(position);
    }


    public List<Wares> getDatas(){

        return  mDatas;
    }
    public void clearData(){

        mDatas.clear();
        notifyItemRangeRemoved(0,mDatas.size());
    }

    public void addData(List<Wares> datas){

        addData(0,datas);
    }

    public void addData(int position,List<Wares> datas){

        if(datas !=null && datas.size()>0) {

            mDatas.addAll(datas);
            notifyItemRangeChanged(position, mDatas.size());
        }

    }


    @Override
    public int getItemCount() {

        if(mDatas!=null && mDatas.size()>0)
            return mDatas.size();

        return 0;
    }



    class ViewHolder extends RecyclerView.ViewHolder{


        SimpleDraweeView draweeView;
        TextView textTitle;
        TextView textPrice;
        Button btShop;


        public ViewHolder(View itemView) {
            super(itemView);

            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.drawee_view);
            textTitle= (TextView) itemView.findViewById(R.id.text_title);
            textPrice= (TextView) itemView.findViewById(R.id.text_price);
            btShop = (Button) itemView.findViewById(R.id.btn_add);
        }
    }
}
