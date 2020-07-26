package com.example.bilibili.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.bilibili.Adapters.TableLayoutAdapter;
import com.example.bilibili.R;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MyBottomFragments01 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    private Context mContext;
    private View view;
    List<Fragment> fragments=new ArrayList<>();

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
            view = inflater.inflate(R.layout.mybottomfragments01, null);
            initview();

            /*Toolbar toolbar=view.findViewById(R.id.main_toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar=((AppCompatActivity)getActivity()).getSupportActionBar();

            if(actionBar!=null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_format_align_left_black_24dp);
            }*/
            initFragment();

            TableLayoutAdapter adapter=new TableLayoutAdapter(getFragmentManager(),fragments);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);

            /*navigationView.setCheckedItem(R.id.nav_home);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    mDrawLayout.closeDrawers();
                    return true;
                }
            });*/
        }
        return view;
    }

    private void initFragment() {
        fragments.add(new MyTablelayoutFragment01());
        fragments.add(new MyTablelayoutFragment02());
        fragments.add(new MyTablelayoutFragment03());
        fragments.add(new MyTablelayoutFragment04());
        fragments.add(new MyTablelayoutFragment05());
    }

    private void initview() {
        setHasOptionsMenu(true);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
    }

/*    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }*/

}
