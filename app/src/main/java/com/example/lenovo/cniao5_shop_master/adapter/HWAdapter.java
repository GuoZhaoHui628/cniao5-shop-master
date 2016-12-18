package com.example.lenovo.cniao5_shop_master.adapter;


import android.content.Context;
import android.net.Uri;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import static com.example.lenovo.cniao5_shop_master.R.id.drawee_view;

/**
 * Created by ${GuoZhaoHui} on 2016/12/12.
 * Abstract:
 */

public  class HWAdapter extends BaseAdapter<Wares,BaseViewHolder>{

    public HWAdapter(List<Wares> datas, Context context, int layoutResid) {
        super(datas, context, layoutResid);
    }

    @Override
    public void bindData(BaseViewHolder holder, Wares wares) {
        holder.getTextView(R.id.text_title).setText(wares.getName());
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        simpleDraweeView.setImageURI(Uri.parse(wares.getImgUrl()));
        holder.getTextView(R.id.text_price).setText("￥"+wares.getPrice());
    }
}
