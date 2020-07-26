package com.example.bilibili.bean;

import android.widget.ImageView;

import com.example.bilibili.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class Fruit {

    private String content_name;

    public Fruit(String content_name) {
        this.content_name = content_name;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public static List<Fruit> getGoodsList(){
        List<Fruit> Datas=new ArrayList<Fruit>();
        for (int i=0;i<50;i++){
            Fruit apple=new Fruit("apple");
            Datas.add(apple);
        }
        return Datas;
    }
}
