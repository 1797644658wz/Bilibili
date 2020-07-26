package com.example.bilibili.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.bilibili.Adapters.MyRecyclerviewAdapter;
import com.example.bilibili.Adapters.Recycler_View_Adapter_main;
import com.example.bilibili.R;
import com.example.bilibili.bean.Goods;

import java.util.ArrayList;
import java.util.List;


public class MyTablelayoutFragment01 extends Fragment /*implements BannerPager.BannerClickListener */{

    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private View view;
    private List<Goods> mDatas=new ArrayList<>();
    private Goods[] good_image={new Goods(R.drawable.ic_vip_login,"ic_vip_login"),
            new Goods(R.drawable.img_holder_error_style3,"img_holder_error_style3")};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mContext = getActivity();
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(R.layout.mytallayoutfragment01, null);

            mDatas=Goods.getGoodsList(good_image);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL);
            Recycler_View_Adapter_main adapter= new Recycler_View_Adapter_main(mDatas,mContext);
            RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);

            swipeRefreshLayout=view.findViewById(R.id.swip_refresh);
            swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshFruits();
                }
            });
        }
        return view;
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        mDatas=Goods.getGoodsList(good_image);
                        RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
                        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
                        recyclerView.setLayoutManager(layoutManager);
                        MyRecyclerviewAdapter adapter=new MyRecyclerviewAdapter(mDatas);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(mContext, "刷新成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
