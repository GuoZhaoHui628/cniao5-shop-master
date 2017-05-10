package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.net.Uri;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.Wares;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;



/**
 * Created by <a href="http://www.cniao5.com">菜鸟窝</a>
 * 一个专业的Android开发在线教育平台
 */
public class WaresAdapter extends BaseAdapter<Wares,BaseViewHolder> {


    public WaresAdapter(List<Wares> datas, Context context, int layoutResid) {
        super(datas, context, layoutResid);
    }



    @Override
    public void bindData(BaseViewHolder holder, Wares wares) {

        holder.getTextView(R.id.text_title).setText(wares.getName());
        holder.getTextView(R.id.text_price).setText("￥"+wares.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(wares.getImgUrl()));

    }
}
