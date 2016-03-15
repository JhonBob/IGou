package com.bob.igou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bob.igou.activity.LoginActivity;
import com.bob.igou.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class PeopleFragment extends Fragment implements View.OnClickListener{
    private View mLayout;
    private TextView tv_username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLayout==null){
            mLayout=inflater.inflate(R.layout.fragment_people,container,false);
            mLayout.findViewById(R.id.layout_login).setOnClickListener(this);
            tv_username= (TextView) mLayout.findViewById(R.id.tv_username);
//        }else{
//            ViewGroup parent= (ViewGroup) mLayout.getParent();
//            parent.removeView(mLayout);
        }
        return mLayout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_login:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), 0);
                break;
            default:
                break;
        }
    }


    //回传数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==1){
            String userName = data.getStringExtra("name");
            System.out.println(userName);
            tv_username.setText(userName);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
