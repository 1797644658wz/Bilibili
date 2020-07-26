package com.example.bilibili.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.bilibili.R;
import com.example.bilibili.bean.Fruit;
import java.util.ArrayList;
import java.util.List;


public class MyBottomFragments02 extends Fragment {

    private Context mContext;
    private View view;
    private List<Fruit> mDatas=new ArrayList<>();



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
            view = inflater.inflate(R.layout.mybottomfragments02, null);
            /*RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
            final GridLayoutManager manager=new GridLayoutManager(mContext,2);
            recyclerView.setLayoutManager(manager);
            View head=LayoutInflater.from(mContext).inflate(R.layout.head_test,recyclerView,false);
            final HeaderTestAdapter myadapter=new HeaderTestAdapter(head,mContext);
            recyclerView.setAdapter(myadapter);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return myadapter.isHeader(position) ? manager.getSpanCount() : 1;
                }
            });*/
       /*     mDatas=Fruit.getGoodsList();
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL);
            MyAdapter adapter= new MyAdapter(mContext,mDatas);
            RecyclerView recyclerView=view.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);*/
        }
        return view;
    }
}
