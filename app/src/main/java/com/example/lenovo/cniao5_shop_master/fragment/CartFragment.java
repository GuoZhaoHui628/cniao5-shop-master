package com.example.lenovo.cniao5_shop_master.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.cniao5_shop_master.CartProvider;
import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.adapter.CartAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.decoration.DividerItemDecoration;
import com.example.lenovo.cniao5_shop_master.bean.ShoppingCart;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.annotation.Check;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;


/**
 * Created by lenovo on 2016/9/23.
 */
public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";
    private View view;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.checkbox_all)
    private CheckBox mCheckBox;

    @ViewInject(R.id.txt_total)
    private TextView mTextTotal;

    @ViewInject(R.id.btn_order)
    private Button mBtnOrder;

    @ViewInject(R.id.btn_del)
    private Button mBtnDel;


    private CartAdapter mAdapter;
    private CartProvider cartProvider;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart,container,false);
        ViewUtils.inject(this,view);

        cartProvider = new CartProvider(getActivity());

        showData();

        mBtnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }


    private void showData(){

        List<ShoppingCart> carts = cartProvider.getAll();

        mAdapter = new CartAdapter(carts,getActivity(),R.layout.template_cart,mCheckBox,mTextTotal);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));

    }


    public void refreshData(){

        mAdapter.clear();
        List<ShoppingCart> carts = cartProvider.getAll();
        mAdapter.addData(carts);

        mAdapter.showTotalPrice();

    }

}
