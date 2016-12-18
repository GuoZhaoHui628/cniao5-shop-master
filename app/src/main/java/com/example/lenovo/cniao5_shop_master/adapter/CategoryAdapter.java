package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.Category;

import java.util.List;

/**
 * Created by ${GuoZhaoHui} on 2016/12/12.
 * Abstract:
 */

public class CategoryAdapter extends BaseAdapter<Category,BaseViewHolder> {

     private Context mContext;

    public CategoryAdapter(List<Category> datas, Context context, int layoutResid) {
        super(datas, context, layoutResid);
        this.mContext = context;
    }

    @Override
    public void bindData(BaseViewHolder holder, final Category category) {

        holder.getTextView(R.id.tv_item_category).setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,category.getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
