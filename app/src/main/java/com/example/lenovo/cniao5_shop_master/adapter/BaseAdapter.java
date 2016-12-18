package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ${GuoZhaoHui} on 2016/12/11.
 * Abstract: fengzhuang Adapter
 */

public abstract class BaseAdapter<T,H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDatas;

    protected LayoutInflater mLayoutInflater;

    protected Context mContext;

    protected int mLayouResid;


    protected onItemClickListener mListener;


    public void setOnItemClickListener(onItemClickListener listener){
        this.mListener = listener;
    }


   public  interface onItemClickListener{
        void onClick(View view,int position);
    }


    public BaseAdapter(List<T> datas,Context context,int layoutResid) {

        this.mContext = context;

        this.mLayoutInflater = LayoutInflater.from(context);

        this.mLayouResid = layoutResid;

        this.mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mLayoutInflater.inflate(mLayouResid,null,false);

        return new BaseViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        T t = getItem(position);

        bindData(holder,t);

    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    /*changyong de fangfa*/

    public void clear(){
//        int itemCount = datas.size();
//        datas.clear();
//        this.notifyItemRangeRemoved(0,itemCount);

        for (Iterator it = mDatas.iterator(); it.hasNext();){

            T t = (T) it.next();
            int position = mDatas.indexOf(t);
            it.remove();
            notifyItemRemoved(position);
        }
    }


    /**
     * 从列表中删除某项
     * @param t
     */
    public  void removeItem(T t){

        int position = mDatas.indexOf(t);
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void addData(List<T> datas){

        addData(0,datas);
    }

    public void addData(int position,List<T> list){

        if(list !=null && list.size()>0) {

            for (T t:list) {
                mDatas.add(position, t);
                notifyItemInserted(position);
            }

        }
    }

    public void refreshData(List<T> list){

        if(list !=null && list.size()>0){

            clear();
            int size = list.size();
            for (int i=0;i<size;i++){
                mDatas.add(i,list.get(i));
                notifyItemInserted(i);
            }

        }
    }

    public void loadMoreData(List<T> list){

        if(list !=null && list.size()>0){

            int size = list.size();
            int begin = mDatas.size();
            for (int i=0;i<size;i++){
                mDatas.add(list.get(i));
                notifyItemInserted(i+begin);
            }

        }

    }



    public abstract void bindData(BaseViewHolder holder,T t);
}
