package com.bob.igou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.igou.R;
import com.bob.igou.bean.UserData;
import com.bob.igou.db.UserDataManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mAcount;
    private EditText mPwd;
    private TextView mRegisterButton;
    private Button mRegButton;
    private Button mCancleButton;
    private UserDataManager mUserDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAcount= (EditText) findViewById(R.id.login_edit_account);
        mPwd= (EditText) findViewById(R.id.login_edit_pwd);
        mRegisterButton= (TextView) findViewById(R.id.login_btn_register);
        mRegButton= (Button) findViewById(R.id.login_btn_reg);
        mCancleButton= (Button) findViewById(R.id.login_btn_cancle);

        mRegButton.setOnClickListener(this);
        mCancleButton.setOnClickListener(this);

        mRegButton.setText("注册");
        mRegisterButton.setVisibility(View.GONE);

        if (mUserDataManager==null){
            mUserDataManager=new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_reg:
                register();
                break;
            case R.id.login_btn_cancle:
                cancle();
                break;
        }
    }



    private void cancle() {
        finish();
    }

    //注册
    public void register(){
        if (isUserNameAndPwdVaild()){
            String userName=mAcount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            int result = mUserDataManager.findUserByName(userName);
            if (result>0){
                Toast.makeText(this, "该用户名已被注册", Toast.LENGTH_SHORT).show();
                return;
            }else if(result==0){
                UserData userData=new UserData(userName,userPwd);
                mUserDataManager.openDataBase();
                long flags = mUserDataManager.insertUserData(userData);
                if (flags==-1){
                    Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    //判读是否为空
    public boolean isUserNameAndPwdVaild(){
        if (mAcount.getText().toString().trim().equals("")){
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (mPwd.getText().toString().trim().equals("")){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
