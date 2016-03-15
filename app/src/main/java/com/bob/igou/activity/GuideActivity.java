package com.bob.igou.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bob.igou.R;
import com.bob.igou.activity.MainActivity;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager pager;
    private int[] imgRes=new int[]{
            R.drawable.img_guide_background_1,
            R.drawable.img_guide_background_2,
            R.drawable.img_guide_background_3,
            R.drawable.img_guide_background_4
    };

    private ArrayList<View> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //布局管理器
        LayoutInflater inflate=getLayoutInflater();

        for (int i=0;i<imgRes.length;i++){
            View view = inflate.inflate(R.layout.pager_item, null);
            ImageView iv_guide = (ImageView) view.findViewById(R.id.iv_guide);
            iv_guide.setBackgroundResource(imgRes[i]);
            if(i==imgRes.length-1){
                ImageButton ibtn_guide= (ImageButton) view.findViewById(R.id.ibtn_guide);
                ibtn_guide.setVisibility(View.VISIBLE);
                ibtn_guide.setOnClickListener(this);
            }
            mList.add(view);

        }
        pager= (ViewPager) findViewById(R.id.viewpager_guide);
        //设置预加载
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(new MyViewPage());
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.ibtn_guide){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    class MyViewPage extends PagerAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;//官方建议
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           // super.destroyItem(container, position, object);
        }

        //与getView同等
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position);
            pager.addView(view);
            return view;
        }
    }
}
