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
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;


/**
 * Created by lenovo on 2016/9/23.
 */
public class CartFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "CartFragment";
    private View view;


    public static final int ACTION_EDIT = 1;
    public static final int ACTION_COMPLETE = 2;


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

    @ViewInject(R.id.bt_control)
    private Button mBtControl;


    private CartAdapter mAdapter;
    private CartProvider cartProvider;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart,container,false);
        ViewUtils.inject(this,view);

        cartProvider = new CartProvider(getActivity());

        showData();

        mBtControl.setOnClickListener(this);
        mBtControl.setTag(ACTION_EDIT);
        return view;
    }


    private void showData(){

        List<ShoppingCart> carts = cartProvider.getAll();

        mAdapter = new CartAdapter(carts,getActivity(),R.layout.template_cart,mCheckBox,mTextTotal);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL_LIST));

        mAdapter.checkListener();

    }


    public void refreshData(){

        mAdapter.clear();
        List<ShoppingCart> carts = cartProvider.getAll();
        mAdapter.addData(carts);

        mAdapter.checkListener();

        mAdapter.showTotalPrice();


    }

    private void showDelete(){

        mBtControl.setText("完成");
        mBtnOrder.setVisibility(View.GONE);
        mBtnDel.setVisibility(View.VISIBLE);
        mTextTotal.setVisibility(View.GONE);

        mCheckBox.setChecked(false);
        mAdapter.checkAll_None(false);
        mBtControl.setTag(ACTION_COMPLETE);
    }

    private void hideDelete(){

        mBtControl.setText("编辑");
        mBtnOrder.setVisibility(View.VISIBLE);
        mBtnDel.setVisibility(View.GONE);
        mTextTotal.setVisibility(View.VISIBLE);

        mCheckBox.setChecked(true);
        mAdapter.checkAll_None(true);
        mBtControl.setTag(ACTION_EDIT);

    }

    @OnClick(R.id.btn_del)
    public void deleCart(View view){

        mAdapter.deleteCart();

    }

    @Override
    public void onClick(View view) {

        int action = (int) view.getTag();
        if(action==ACTION_EDIT){

            showDelete();

        }else if(action==ACTION_COMPLETE){

            hideDelete();

        }

    }
}
