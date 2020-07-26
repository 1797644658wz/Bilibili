package com.example.bilibili.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.bilibili.R;
import com.example.bilibili.bean.BannerImageLoad;
import com.example.bilibili.bean.Goods;
import com.example.bilibili.widget.FruitActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import java.util.ArrayList;
import java.util.List;

public class Recycler_View_Adapter_main extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Goods> mDatas;
    private Context mContext;
    private final int type_zero = 0;
    private final int type_one = 1;

    public Recycler_View_Adapter_main(List<Goods> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case type_zero:
                if (mContext==null){
                    mContext=parent.getContext();
                }
                View title= LayoutInflater.from(mContext).inflate(R.layout.banner_head,parent,false);
                TitleBanner titleBanner=new TitleBanner(title);
                return titleBanner;
            case type_one:
                if (mContext==null){
                    mContext=parent.getContext();
                }
                View content=LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
                final ContentTest test=new ContentTest(content);
                test.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = test.getAdapterPosition();
                        Goods goods = mDatas.get(position);
                        Intent intent = new Intent(mContext, FruitActivity.class);
                        intent.putExtra(FruitActivity.FRUIT_NAME, goods.getName());
                        intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, goods.getImageIid());
                        mContext.startActivity(intent);
                    }
                });
                return test;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case type_zero:
                TitleBanner titleBanner= (TitleBanner) holder;
                titleBanner.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                //设置图片加载器
                titleBanner.banner.setImageLoader(new BannerImageLoad());
                //设置图片集合
                List<Integer> image = new ArrayList<>();
                image.add(R.drawable.bilipay_common_error_tip);
                image.add(R.drawable.ic_vip_login);
                image.add(R.drawable.img_holder_error_style3);
                image.add(R.drawable.ic_logo_copyright);
                titleBanner.banner.setImages(image);
                //设置banner动画效果
                titleBanner.banner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                /*List<String> titles = new ArrayList<>();
                titles.add("图1");
                titles.add("图2");
                titleBanner.banner.setBannerTitles(titles);*/
                //设置自动轮播，默认为true
                titleBanner.banner.isAutoPlay(true);
                //设置轮播时间
                titleBanner.banner.setDelayTime(3000);
                //设置指示器位置（当banner模式中有指示器时）
                titleBanner.banner.setIndicatorGravity(BannerConfig.CENTER);
                //banner设置方法全部调用完毕时最后调用
                titleBanner.banner.start();
                break;
            case type_one:
                ContentTest contentTest= (ContentTest) holder;
                Goods goods=mDatas.get(position);
                contentTest.im_content.setImageResource(goods.getImageIid());
                contentTest.tv_content.setText(goods.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class TitleBanner extends RecyclerView.ViewHolder{

        Banner banner;
        public TitleBanner(@NonNull View itemView) {
            super(itemView);

            banner=itemView.findViewById(R.id.banner_item);
        }
    }

    public class ContentTest extends RecyclerView.ViewHolder{

        ImageView im_content;
        TextView tv_content;
        CardView cardView;

        public ContentTest(@NonNull View itemView) {
            super(itemView);

            cardView= (CardView) itemView;
            im_content=itemView.findViewById(R.id.im_content);
            tv_content=itemView.findViewById(R.id.tv_content);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return type_zero;
        } else {
            return type_one;
        }
    }
}
