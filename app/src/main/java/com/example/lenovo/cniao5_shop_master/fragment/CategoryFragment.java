package com.example.lenovo.cniao5_shop_master.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.cniao5_shop_master.R;
import com.example.lenovo.cniao5_shop_master.adapter.BaseAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.CategoryAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.WaresAdapter;
import com.example.lenovo.cniao5_shop_master.adapter.decoration.DividerGridItemDecoration;
import com.example.lenovo.cniao5_shop_master.bean.Category;
import com.example.lenovo.cniao5_shop_master.bean.Page;
import com.example.lenovo.cniao5_shop_master.bean.Wares;
import com.example.lenovo.cniao5_shop_master.http.BaseCallback;
import com.example.lenovo.cniao5_shop_master.http.Contants;
import com.example.lenovo.cniao5_shop_master.http.OkHttpHelper;
import com.example.lenovo.cniao5_shop_master.http.SpotsCallBack;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.List;

import static android.R.attr.description;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.id.list;
import static java.security.AccessController.getContext;

public class CategoryFragment extends Fragment {

    private static final String TAG = "CategoryFragment";

    private View view;
    private OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
    private List<Category> mCategoryDatas;

    //一级分类
    private CategoryAdapter categoryAdapter;

    //二级分类
    private WaresAdapter waresAdapter;

    //一级分类recyclerview
    @ViewInject(R.id.recyclerview_category)
    private RecyclerView recyclerview_category;

    @ViewInject(R.id.recyclerview_sub_cate)
    private RecyclerView recyclerview_sub_cate;

    private int currPage=1;
    private int pageSize=10;
    private int totalPage=1;

    private  static final int STATE_NORMAL=0;
    private  static final int STATE_REFREH=1;
    private  static final int STATE_MORE=2;

    private int state=STATE_NORMAL;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_category, container, false);
        ViewUtils.inject(this,view);

        requestRecyCateDatas();

        return view;
    }


    //获取一级商品列表的数据
    public void requestRecyCateDatas(){

        String url = Contants.API.CATEGORY_LIST;

        okHttpHelper.get(url, new SpotsCallBack<List<Category>>(getActivity()) {

            @Override
            public void onSuccess(Response response, List<Category> categories) {
                mCategoryDatas = categories;
                showRecyCategoryView(mCategoryDatas);

                if(mCategoryDatas!=null&&mCategoryDatas.size()>0){
                    requestRecyTwoDatas(mCategoryDatas.get(0).getId());
                }

            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });

    }

     //展示一级商品列表的数据
    public void showRecyCategoryView(List<Category> cateDatas){

        recyclerview_category.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview_category.setItemAnimator(new DefaultItemAnimator());
        categoryAdapter = new CategoryAdapter(cateDatas, getActivity(), R.layout.template_single_text);

        recyclerview_category.setAdapter(categoryAdapter);

        categoryAdapter.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Category  category =   categoryAdapter.getItem(position);
                requestRecyTwoDatas(category.getId());

            }
        });



    }

    //获取二级商品列表的数据
    public void requestRecyTwoDatas(long categoryId){

        String url = Contants.API.WARES_LIST+"?categoryId="+categoryId+"&curPage="+currPage+"&pageSize="+pageSize;
        okHttpHelper.get(url, new BaseCallback<Page<Wares>>() {
            @Override
            public void onBeforeRequest(Request request) {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onResponse(Response response) {

            }

            @Override
            public void onSuccess(Response response, Page<Wares> waresPage) {


                currPage = waresPage.getCurrentPage();
                totalPage =waresPage.getTotalPage();

                showWaresData(waresPage.getList());

                Logger.t(TAG).d(waresPage);
                /**
                 * ║ Page{currentPage=1, pageSize=10, totalPage=0, totalCount=23,
                 * list=[Wares
                 * {id=12, name='希捷（seagate）Expansion 新睿翼1TB 2.5英寸 USB3.0 移动硬盘 (STEA1000400)', imgUrl='http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5519fdd0N02706f5e.jpg', description='null', price=399.0},
                 * Wares{id=18, name='华硕（ASUS）经典系列X554LP 15.6英寸笔记本 （i5-5200U 4G 500G R5-M230 1G独显 蓝牙 Win8.1 黑色）', imgUrl='http://7mno4h.com2.z0.glb.qiniucdn.com/s_recommend_5604aab9N7a91521b.jpg', description='null', price=2999.0},
                 * Wares{id=29, name='联想（ThinkPad）E550（20DFA00RCD）15.6英寸笔记本电脑（i7-5500U 4G 500GB 2G独显 FHD高分屏 Win8.1）', imgUrl='http://7mno4h.com2.z0.glb.qiniucdn.com/s_rmd55fbcea8N8d520a22.jpg', description='null', price=5499.0},
                 * Wares{id=35, name='索尼 （SONY） G9 48英寸全高清 LED液晶电视 （金色）', imgUrl='http://m.360buyimg.com/n4/jfs/t1465/56/1152971182/175914/b39652b4/55e417fcN7d7f9363.jpg!q70.jpg', description='null', price=3299.0},
                 * Wares{id=45, name='海信（Hisense）LED58EC620UA 58英寸 炫彩4K 14核 VIDAA3智能电视(黑色)', imgUrl='http://m.360buyimg.com/n4/jfs/t2035/113/115726791/214888/5e4203f2/55efc9b5N820416a4.jpg!q70.jpg', description='null', price=5299.0},
                 * Wares{id=48, name='三星（SAMSUNG）UA55JU50SW 55英寸 4K超高清智能电视 黑色', imgUrl='http://m.360buyimg.com/n4/jfs/t2110/267/571221917/150474/a38336aa/56175bc9Nc71a197b.jpg!q70.jpg', description='null', price=6399.0},
                 * Wares{id=49, name='索尼（SONY）KDL-55R580C 55英寸 LED液晶电视（黑色）', imgUrl='http://m.360buyimg.com/n4/jfs/t1801/106/1286076832/162742/3f43a741/55e41806N2c4924d3.jpg!q70.jpg', description='null', price=5299.0},
                 * Wares{id=52, name='华为 Ascend Mate7 月光银 移动联通双4G手机 双卡双待双通', imgUrl='http://m.360buyimg.com/n4/jfs/t2374/194/284133169/106220/86f4da4c/55fb5f44Na4a86b54.jpg!q70.jpg', description='null', price=2599.0},
                 * Wares{id=62, name='【超值套装版】魅族 魅蓝2 16GB 白色 移动4G手机 双卡双待', imgUrl='http://m.360buyimg.com/n4/jfs/t1339/47/803991388/116563/2f48981b/55e03596N65ba23ff.jpg!q70.jpg', description='null', price=768.0},
                 * Wares{id=65, name='华为 荣耀畅玩4C 双卡双待4G手机 白色 移动4G标准版(8G ROM) 标配', imgUrl='http://m.360buyimg.com/n4/jfs/t853/190/1078015459/198985/8802bcba/55729cc0N4bcbd173.jpg!q70.jpg', description='null', price=938.0}]}
                 */

            }


            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });



    }

    private  void showWaresData(List<Wares> wares){

        switch (state){

            case  STATE_NORMAL:


                waresAdapter = new WaresAdapter(wares, getActivity(), R.layout.template_grid_wares);

                recyclerview_sub_cate.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recyclerview_sub_cate.setItemAnimator(new DefaultItemAnimator());
                recyclerview_sub_cate.addItemDecoration(new DividerGridItemDecoration(getContext()));
                recyclerview_sub_cate.setAdapter(waresAdapter);


//                if(mWaresAdatper ==null) {
//                    mWaresAdatper = new WaresAdapter(getContext(), wares);
//                    mWaresAdatper.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(View view, int position) {
//                            Wares wares = mWaresAdatper.getItem(position);
//
//                            Intent intent = new Intent(getActivity(), WareDetailActivity.class);
//
//                            intent.putExtra(Contants.WARE,wares);
//                            startActivity(intent);
//
//                        }
//                    });
//
//                    mRecyclerviewWares.setAdapter(mWaresAdatper);

//                }
//                else{
//                    mWaresAdatper.clear();
//                    mWaresAdatper.addData(wares);
//                }




                break;

            case STATE_REFREH:
//                mWaresAdatper.clear();
//                mWaresAdatper.addData(wares);
//
//                mRecyclerviewWares.scrollToPosition(0);
//                mRefreshLaout.finishRefresh();
                break;

            case STATE_MORE:
//                mWaresAdatper.addData(mWaresAdatper.getDatas().size(),wares);
//                mRecyclerviewWares.scrollToPosition(mWaresAdatper.getDatas().size());
//                mRefreshLaout.finishRefreshLoadMore();
                break;


        }



    }



}



