package com.example.lenovo.cniao5_shop_master;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.lenovo.cniao5_shop_master.bean.Tab;
import com.example.lenovo.cniao5_shop_master.fragment.CartFragment;
import com.example.lenovo.cniao5_shop_master.fragment.CategoryFragment;
import com.example.lenovo.cniao5_shop_master.fragment.HomeFragment;
import com.example.lenovo.cniao5_shop_master.fragment.HotFragment;
import com.example.lenovo.cniao5_shop_master.fragment.MineFragment;
import com.example.lenovo.cniao5_shop_master.widget.FragmentTabHost;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater mInflater;
    private List<Tab> mTabs = new ArrayList<Tab>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {

        this.mTabhost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        Tab tab_home = new Tab(R.string.home,R.drawable.selector_icon_home,HomeFragment.class);
        Tab tab_hot = new Tab(R.string.hot,R.drawable.selector_icon_hot,HotFragment.class);
        Tab tab_category = new Tab(R.string.catagory,R.drawable.selector_icon_category,CategoryFragment.class);
        Tab tab_cart = new Tab(R.string.cart,R.drawable.selector_icon_cart,CartFragment.class);
        Tab tab_mine = new Tab(R.string.mine,R.drawable.selector_icon_mine,MineFragment.class);
                                           //这里的封装很有用 要学会
        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);

        for(Tab tab:mTabs){
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(getIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }

        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    private View getIndicator(Tab tab) {
        mInflater = LayoutInflater.from(this);
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getResIcon());
        text.setText(getString(tab.getTitle()));
        return view;
    }
}
