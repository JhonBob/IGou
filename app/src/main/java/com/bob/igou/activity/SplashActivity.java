package com.bob.igou.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.KeyEvent;

import com.bob.igou.R;

public class SplashActivity extends Activity {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                boolean isFirst = sp.getBoolean("isFirst", true);
                if (isFirst) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("isFirst", false);
                    editor.apply();
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 3000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(this);
    }
}
