package com.example.lenovo.cniao5_shop_master.bean;

import java.io.Serializable;

/**
 * Created by ${GuoZhaoHui} on 2017/5/9.
 * email:guozhaohui628@gmail.com
 */

public class ShoppingCart extends Wares implements Serializable {

    private int count;
    private boolean isChecked = true;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
