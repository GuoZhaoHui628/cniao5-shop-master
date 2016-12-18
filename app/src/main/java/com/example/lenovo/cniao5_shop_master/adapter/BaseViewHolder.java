package com.example.lenovo.cniao5_shop_master.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by ${GuoZhaoHui} on 2016/12/11.
 * Abstract:
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    private BaseAdapter.onItemClickListener mListener;

    private SparseArray<View> views;

    public BaseViewHolder(View itemView, BaseAdapter.onItemClickListener listener) {
        super(itemView);

        views = new SparseArray<>();

        this.mListener = listener;
        itemView.setOnClickListener(this);
    }

    public View getView(int id){
        return findView(id);
    }

    public TextView getTextView(int viewId) {
        return findView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return findView(viewId);
    }


    public Button getButton(int viewId) {
        return findView(viewId);
    }


    private <T extends View> T findView(int id){

        View view =  views.get(id);

        if(view == null){
            view = itemView.findViewById(id);
            views.append(id,view);
        }

        return (T) view;

    }

    @Override
    public void onClick(View view) {

        if(mListener != null){
            mListener.onClick(view,getLayoutPosition());
        }

    }
}
