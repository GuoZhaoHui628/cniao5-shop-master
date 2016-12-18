package com.example.lenovo.cniao5_shop_master.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.adapter.BaseAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.CategoryAdapter;
import com.example.lenovo.cniao5_shop_master.bean.Category;
import com.example.lenovo.cniao5_shop_master.bean.Page;
import com.example.lenovo.cniao5_shop_master.http.Contants;
import com.example.lenovo.cniao5_shop_master.http.OkHttpHelper;
import com.example.lenovo.cniao5_shop_master.http.SpotsCallBack;
import com.squareup.okhttp.Response;

import java.util.List;

public class CategoryFragment extends Fragment {

    private View view;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private List<Category> mCategoryDatas;
    private CategoryAdapter categoryAdapter;
    private RecyclerView recyclerview_category;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_category, container, false);

        requestRecyCateDatas();

        return view;
    }



    public void requestRecyCateDatas(){

        String url = Contants.API.CATEGORY_LIST;

        okHttpHelper.get(url, new SpotsCallBack<List<Category>>(getActivity()) {

            @Override
            public void onSuccess(Response response, List<Category> categories) {
                mCategoryDatas = categories;
                showRecyCategoryView(mCategoryDatas);
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }


    public void showRecyCategoryView(List<Category> cateDatas){

        recyclerview_category = (RecyclerView) view.findViewById(R.id.recyclerview_category);
        recyclerview_category.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview_category.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new CategoryAdapter(cateDatas, getActivity(), R.layout.template_single_text);
//
//        categoryAdapter.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Toast.makeText(getActivity(),"点你妹",Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerview_category.setAdapter(categoryAdapter);


    }
}



