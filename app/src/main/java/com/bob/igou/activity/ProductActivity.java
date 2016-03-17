package com.bob.igou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bob.igou.R;
import com.bob.igou.db.UserDataManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener{

    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    private Button back;
    private Button btn_buy,btn_add;
    private String title;

    private UserDataManager mUserDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        initoption();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String short_title = intent.getStringExtra("short_title");
        int price = intent.getIntExtra("price", 0);
        String img_url = intent.getStringExtra("img_URL");
        TextView tv_decs = (TextView) findViewById(R.id.tv_decs);
        TextView tv_decs2 = (TextView) findViewById(R.id.tv_decs2);
        TextView tv_price = (TextView) findViewById(R.id.tv_price);

        tv_decs.setText(title);
        tv_decs2.setText(short_title);
        tv_price.setText(""+price);
        ImageView product_image_head= (ImageView) findViewById(R.id.product_image_head);
        imageLoader.displayImage(img_url,product_image_head,options);


        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_buy= (Button) findViewById(R.id.btn_buy);
        btn_buy.setOnClickListener(this);


    }

    private void initoption() {
        //建造者模式
        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_launcher)
//                        // 设置图片在下载期间显示的默认图片
//                .showImageForEmptyUri(R.drawable.ic_launcher)
//                        // 设置URI错误或者为空的时候显示的图片
//                .showImageOnFail(R.drawable.ic_launcher)
//                        // 设置图片加载或者解析出现错误的时候设置的图片
                .cacheInMemory(true)
                        // 是否内存缓存
                .cacheOnDisk(true)
                        // 是否外存缓存
                .considerExifParams(true)
//                .displayer(new RoundedBitmapDisplayer(20))// 是否设置图片圆角
//				.displayer(new FadeInBitmapDisplayer(200))// 图片加载好之后渐入的动画时间
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.btn_add:
                int result=mUserDataManager.findGoodsByName(title);
                if (result<=0){
                    mUserDataManager.insertGoodsData(title);
                    Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"该商品以添加，请勿重复添加！",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_buy:
                break;
        }
    }
}
