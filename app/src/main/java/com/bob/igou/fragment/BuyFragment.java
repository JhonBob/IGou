package com.bob.igou.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.igou.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class BuyFragment extends Fragment {
    private View mLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLayout==null){
            mLayout=inflater.inflate(R.layout.fragment_buy,container,false);
//        }else{
//            ViewGroup parent= (ViewGroup) mLayout.getParent();
//            parent.removeView(mLayout);

        }
        return mLayout;
    }
}
