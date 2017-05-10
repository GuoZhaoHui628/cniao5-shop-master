package com.example.lenovo.cniao5_shop_master;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.lenovo.cniao5_shop_master.bean.ShoppingCart;
import com.example.lenovo.cniao5_shop_master.utils.JSONUtil;
import com.example.lenovo.cniao5_shop_master.utils.PreferencesUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ${GuoZhaoHui} on 2017/5/9.
 * email:guozhaohui628@gmail.com
 * 购物车数据存储器
 */

public class CartProvider {

    private SparseArray<ShoppingCart> datas = null;

    private static final String CART_JSON = "cart_json";

    private Context mContext;

    public CartProvider(Context context){

        mContext = context;
        datas = new SparseArray<>(10);
        listToSpare();
    }

    public void put(ShoppingCart shoppingCart){

        ShoppingCart temp =  datas.get(shoppingCart.getId().intValue());

        if(temp!=null){
            temp.setCount(temp.getCount()+1);
        }else{
            temp = shoppingCart;
            temp.setCount(1);
        }

        datas.put(shoppingCart.getId().intValue(),temp);
        commit();
    }

    private void commit(){
        List<ShoppingCart> carts = spareToList();
        PreferencesUtils.putString(mContext, CART_JSON, JSONUtil.toJSON(carts));
    }


    private List<ShoppingCart> spareToList(){

        int size = datas.size();
        List<ShoppingCart> carts = new ArrayList<>();
        for(int i=0;i<size;i++){
            carts.add(datas.valueAt(i));
        }
        return carts;
    }

    private void listToSpare(){

        List<ShoppingCart> cartList =  getDataFromLocal();
        if(cartList!=null&& cartList.size()>0){
            for(ShoppingCart cart:cartList){
                datas.put(cart.getId().intValue(),cart);
            }
        }
    }

    public void update(ShoppingCart shoppingCart){
        datas.put(shoppingCart.getId().intValue(),shoppingCart);
        commit();
    }


    public void delete(ShoppingCart shoppingCart){
        datas.delete(shoppingCart.getId().intValue());
        commit();
    }


    public List<ShoppingCart> getAll(){
        return getDataFromLocal();
    }


    public List<ShoppingCart> getDataFromLocal(){

        String json = PreferencesUtils.getString(mContext, CART_JSON);
        List<ShoppingCart> carts = new ArrayList<>();
        if(json!=null){
            carts = JSONUtil.fromJson(json, new TypeToken<List<ShoppingCart> >() {
            }.getType());
        }
        return carts;
    }

}
