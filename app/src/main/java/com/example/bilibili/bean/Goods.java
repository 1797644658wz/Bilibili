package com.example.bilibili.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Goods {

    private int imageIid;
    private String name;

    public Goods(int imageIid, String name) {
        this.imageIid = imageIid;
        this.name = name;
    }

    public int getImageIid() {
        return imageIid;
    }

    public String getName() {
        return name;
    }

    public static List<Goods> getGoodsList(Goods[] goods){
        List<Goods> goodsList=new ArrayList<Goods>();
        for (int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(goods.length);
            goodsList.add(goods[index]);
        }
        return goodsList;
    }

}
