package com.example.lenovo.cniao5_shop_master.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.cniao5_shop_master.CartProvider;
import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.bean.Page;
import com.example.lenovo.cniao5_shop_master.bean.ShoppingCart;
import com.example.lenovo.cniao5_shop_master.widget.NumberAddSubView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Iterator;
import java.util.List;

import static com.example.lenovo.cniao5_shop_master.R.string.cart;

/**
 * Created by ${GuoZhaoHui} on 2017/5/9.
 * email:guozhaohui628@gmail.com
 */

public class CartAdapter extends BaseAdapter<ShoppingCart,BaseViewHolder> implements BaseAdapter.onItemClickListener {

    private static final String TAG = "CartAdapter";
    private CheckBox checkBox;
    private TextView tvPrice;
    private List<ShoppingCart> cartList;
    private CartProvider provider;

    public CartAdapter(List<ShoppingCart> datas, Context context, int layoutResid, CheckBox cb, TextView tv) {
        super(datas, context, layoutResid);
        cartList = datas;
        checkBox = cb;
        tvPrice = tv;

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAll_None(checkBox.isChecked());
                showTotalPrice();

            }
        });

        provider = new CartProvider(context);

        setOnItemClickListener(this);

        showTotalPrice();
    }

    public void checkAll_None(Boolean flag){

        if(cartList!=null){
            int i=0;
            for(ShoppingCart cart:cartList){
                cart.setChecked(flag);
                provider.update(cart);

                notifyItemChanged(i);
                i++;
            }
        }
    }


    public void deleteCart(){

//        if(cartList!=null){
//
//            //采取这种办法循环遍历集合删除会报错，特别注意
//            for(ShoppingCart cart:cartList){
//
//                int position = cartList.indexOf(cart);
//                provider.delete(cart);
//                cartList.remove(cart);
//                notifyItemRemoved(position);
//
//            }
//
//        }

        if(cartList!=null){

            for(Iterator iterator = cartList.iterator();iterator.hasNext();){

                ShoppingCart cart = (ShoppingCart) iterator.next();
                if(cart.isChecked()){
                    int position = cartList.indexOf(cart);
                    provider.delete(cart);
                    iterator.remove();
                    notifyItemRemoved(position);

                }
            }

            }

        }





    @Override
    public void bindData(BaseViewHolder holder, final ShoppingCart shoppingCart) {

        holder.getTextView(R.id.text_title).setText(shoppingCart.getName());
        holder.getTextView(R.id.text_price).setText("￥"+shoppingCart.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(shoppingCart.getImgUrl()));

        CheckBox checkBox = (CheckBox) holder.getView(R.id.checkbox);
        checkBox.setChecked(shoppingCart.isChecked());

        NumberAddSubView numAddSubView = (NumberAddSubView) holder.getView(R.id.num_control);
        numAddSubView.setValue(shoppingCart.getCount());

        numAddSubView.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonAddClick(View view, int value) {

                shoppingCart.setCount(value);
                provider.update(shoppingCart);

                showTotalPrice();

            }

            @Override
            public void onButtonSubClick(View view, int value) {

                shoppingCart.setCount(value);
                provider.update(shoppingCart);

                showTotalPrice();

            }
        });

    }


    private float getTotalPrice(){
        float sum = 0 ;
        if(cartList!=null&&cartList.size()>0){
            for(ShoppingCart cart:cartList){
                if(cart.isChecked()){
                    float f = cart.getCount()*cart.getPrice();
                    sum  = sum +f;
                }
            }
        }
        return sum;
    }


    public void showTotalPrice(){
        float total = getTotalPrice();
        tvPrice.setText(Html.fromHtml("合计 ￥<span style='color:#eb4f38'>" + total + "</span>"), TextView.BufferType.SPANNABLE);
    }



    @Override
    public void onClick(View view, int position) {


        ShoppingCart shoppingCart = getItem(position);
        shoppingCart.setChecked(!shoppingCart.isChecked());

        provider.update(shoppingCart);

        notifyItemChanged(position);

        checkListener();

        showTotalPrice();
    }


    public void checkListener(){

        int size = 0;
        int checkCount = 0;
        if(cartList!=null){

            size = cartList.size();
            for(ShoppingCart cart:cartList){

                if(cart.isChecked()==false){
                    checkBox.setChecked(false);
                }else{

                    checkCount = checkCount+1;
                }
            }

            if(size == checkCount){
                checkBox.setChecked(true);
            }
        }
    }


}
