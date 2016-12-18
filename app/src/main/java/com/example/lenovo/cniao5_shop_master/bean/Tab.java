package com.example.lenovo.cniao5_shop_master.bean;

/**
 * Created by lenovo on 2016/9/23.
 */
public class Tab {
    private int title;
    private int resIcon;
    private Class fragment;

    public Tab(int title, int resIcon, Class fragment) {
        this.title = title;
        this.resIcon = resIcon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
