package com.bob.igou.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bob.igou.R;
import com.bob.igou.fragment.BuyFragment;
import com.bob.igou.fragment.MainFragment;
import com.bob.igou.fragment.PeopleFragment;

public class MainActivity extends FragmentActivity {
    private   FragmentTabHost mTabHost;
    //标题
    String[] title=new String[]{"首页","购物车","个人中心"};
    //标题对应的图片
    int[] selectors=new int[]{
            R.drawable.main,
            R.drawable.buy,
            R.drawable.mycenter
    };
    //标题对应的Fragment
    Class[] fragmentName=new Class[]{
            MainFragment.class,
            BuyFragment.class,
            PeopleFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        //fragment的容器
        mTabHost.setup(this,getSupportFragmentManager(),R.id.realtabcontent);
        LayoutInflater inflater=getLayoutInflater();
        //图片，标题，Fragment 相互对应
        for (int i=0;i<title.length;i++){
            View inflate = inflater.inflate(R.layout.tab_item, null);
            TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
            tv_title.setText(title[i]);

            Drawable top= getResources().getDrawable(selectors[i]);
            tv_title.setCompoundDrawablesWithIntrinsicBounds(null,top,null,null);

            mTabHost.addTab(mTabHost.newTabSpec(title[i]).setIndicator(inflate),fragmentName[i],null);
        }

    }
}
