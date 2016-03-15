package com.bob.igou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.igou.R;
import com.bob.igou.db.UserDataManager;

//登录界面
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mAcount;
    private EditText mPwd;
    private TextView mRegisterButton;
    private Button mLoginButton;
    private Button mCancleButton;
    private UserDataManager mUserDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAcount= (EditText) findViewById(R.id.login_edit_account);
        mPwd= (EditText) findViewById(R.id.login_edit_pwd);
        mRegisterButton= (TextView) findViewById(R.id.login_btn_register);
        mLoginButton= (Button) findViewById(R.id.login_btn_login);
        mCancleButton= (Button) findViewById(R.id.login_btn_cancle);

        mRegisterButton.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
        mCancleButton.setOnClickListener(this);

        if (mUserDataManager==null){
            mUserDataManager=new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_login:
                login();
                break;
            case R.id.login_btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_btn_cancle:
                cancle();
                break;
        }
    }


    //登录
    private void login() {
        if (isUserNameAndPwdVaild()){
            String userName=mAcount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            int result=mUserDataManager.findUserByNameAndPwd(userName,userPwd);
            System.out.println(result);
            if (result>0){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                //登录成功，设置回传数据
                Intent intent = getIntent();
                intent.putExtra("name",userName);
                setResult(1,intent);
                finish();
            }else if(result==0){
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //取消
    private void cancle() {
        finish();
    }



    //判断是否为空
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
