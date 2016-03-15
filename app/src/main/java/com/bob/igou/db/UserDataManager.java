package com.bob.igou.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bob.igou.bean.UserData;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/3/15.
 */
//数据库管理
public class UserDataManager {
    //数据库版本和名称
    private static final String DB_NAME="user_data";
    private static final int DB_VERSION=1;

    //用户注册表
    public static final String ID="_id";
    public static final String TABLE_NAME="users";
    public static final String USER_NAME="user_name";
    public static final String USER_PWD="user_pwd";



    //创建用户注册表(再写SQL语句时注意：字符串与引用变量之间的空格，连写会导致创建或查询的执行错误，看log日志来修正)
    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key," + USER_NAME +
            " varchar,"
            + USER_PWD + " varchar" + ");";

    //数据库管理帮助类，内部类形式
    private static class DataBaseManagerHelper extends SQLiteOpenHelper{

        //构造函数，创建db
        public DataBaseManagerHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            //创建表
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
            db.execSQL(DB_CREATE);
        }

        //数据库版本升级处理函数
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }



    private Context mContext=null;
    private  DataBaseManagerHelper mDataBaseHelper=null;
    private SQLiteDatabase mSQLiteDatabase=null;

    //管理类构造函数
    public UserDataManager(Context context){
        mContext=context;
    }

    //打开数据库获得db对象
    public void openDataBase(){
        mDataBaseHelper = new DataBaseManagerHelper(mContext);
        mSQLiteDatabase = mDataBaseHelper.getWritableDatabase();
    }

    //关闭数据库
    public void closeDataBase() {
        mSQLiteDatabase.close();
    }

    //插入用户数据
    public long insertUserData(UserData userData){
        String userName = userData.getUserName();
        String userPwd = userData.getUserPwd();
        //封装用户信息
        ContentValues values = new ContentValues();
        values.put(USER_NAME,userName);
        values.put(USER_PWD,userPwd);
        //调用db对象插入用户数据
        return mSQLiteDatabase.insert(TABLE_NAME,ID,values);
    }

    //查找指定用户名和密码
    public int findUserByNameAndPwd(String userName,String pwd){
        int result=0;//标志位
        //查询表，第一个null表示所有项，第三部分位查询条件
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName + " and " + USER_PWD + "=" + pwd, null, null, null, null);
        if (cursor!=null){
            //返回数据数目
            result=cursor.getCount();
            cursor.close();
        }
        return result;
    }


    //注册用户名的比对
    public int findUserByName(String userName){
        int result=0;//标志位
        //查询表，第一个null表示所有项，第三部分位查询条件
        Cursor cursor = mSQLiteDatabase.query(TABLE_NAME, null, USER_NAME + "=" + userName , null, null, null, null);
        if (cursor!=null){
            //返回数据数目
            result=cursor.getCount();
            cursor.close();
        }
        return result;
    }


}
