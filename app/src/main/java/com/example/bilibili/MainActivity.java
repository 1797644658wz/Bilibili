package com.example.bilibili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bilibili.Fragments.MyBottomFragments01;
import com.example.bilibili.Fragments.MyBottomFragments02;
import com.example.bilibili.Fragments.MyBottomFragments03;
import com.example.bilibili.Fragments.MyBottomFragments04;
import com.example.bilibili.util.StatusBarUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    /*ViewPager viewPager;*/
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    MyBottomFragments01 myBottomFragments01;
    MyBottomFragments02 myBottomFragments02;
    MyBottomFragments03 myBottomFragments03;
    MyBottomFragments04 myBottomFragments04;
    DrawerLayout mDrawLayout;
    NavigationView navigationView;
    /*List<Fragment> fragments=new ArrayList<>();
    MenuItem menuItem;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        StatusBarUtil.fullScreen(this);

        Toolbar toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_format_align_left_black_24dp);
        }

        navigationView.setCheckedItem(R.id.nav_home);

        fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        myBottomFragments01=new MyBottomFragments01();
        transaction.add(R.id.frame_layout,myBottomFragments01).commit();

        /*initfragment();
        ViewPagerFragmentAdapter adapter=new ViewPagerFragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);*/
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Hidefragment();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.first:
                        transaction.show(myBottomFragments01).commit();
                        /*viewPager.setCurrentItem(0);*/
                        return true;
                    case R.id.second:
                        /*viewPager.setCurrentItem(1);*/
                        if (myBottomFragments02==null){
                            myBottomFragments02=new MyBottomFragments02();
                            transaction.add(R.id.frame_layout,myBottomFragments02).commit();
                        }else {
                            transaction.show(myBottomFragments02).commit();
                        }
                        return true;
                    case R.id.third:
                        /*viewPager.setCurrentItem(2);*/
                        if (myBottomFragments03==null){
                            myBottomFragments03=new MyBottomFragments03();
                            transaction.add(R.id.frame_layout,myBottomFragments03).commit();
                        }else {
                            transaction.show(myBottomFragments03).commit();
                        }
                        return true;
                    case R.id.forth:
                        /*viewPager.setCurrentItem(3);*/
                        if (myBottomFragments04==null){
                            myBottomFragments04=new MyBottomFragments04();
                            transaction.add(R.id.frame_layout,myBottomFragments04).commit();
                        }else {
                            transaction.show(myBottomFragments04).commit();
                        }
                        return true;
                }
                return false;
            }
        });
        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem==null){
                    menuItem=bottomNavigationView.getMenu().getItem(0);
                }
                menuItem.setChecked(false);
                menuItem=bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

    }
    /*private void initfragment() {
        fragments.add(new MyBottomFragments01());
        fragments.add(new MyBottomFragments02());
        fragments.add(new MyBottomFragments03());
        fragments.add(new MyBottomFragments04());
    }*/

    private void initview() {
        /*viewPager=findViewById(R.id.view_pager);*/
        bottomNavigationView=findViewById(R.id.bottom_navigation_view);
        mDrawLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
    }
    private void Hidefragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (myBottomFragments01 != null && myBottomFragments01.isAdded()) {
            transaction.hide(myBottomFragments01);
        }
        if (myBottomFragments02 != null && myBottomFragments02.isAdded()) {
            transaction.hide(myBottomFragments02);
        }
        if (myBottomFragments03 != null && myBottomFragments03.isAdded()) {
            transaction.hide(myBottomFragments03);
        }
        if (myBottomFragments04 != null && myBottomFragments04.isAdded()) {
            transaction.hide(myBottomFragments04);
        }
        //提交
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.game_toolbar:
                Toast.makeText(this, "你点击了game", Toast.LENGTH_SHORT).show();
                break;
            case R.id.download_toolbar:
                Toast.makeText(this, "你点击了download", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addition_toolbar:
                Toast.makeText(this, "你点击了addition_toolbar", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_head,menu);
        return true;
    }

}
