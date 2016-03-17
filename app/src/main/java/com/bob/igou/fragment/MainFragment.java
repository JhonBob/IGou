package com.bob.igou.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bob.igou.R;
import com.bob.igou.bean.NewsItemInfo;
import com.bob.igou.lib_lyn.HttpUtils;
import com.bob.igou.lib_lyn.volley.VolleyLisener;
import com.bob.igou.utils.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/3/15.
 */
public class MainFragment extends Fragment {
    private View mLayout;
    private ListView mListView;
    private MyListView mAdapter;

    public ArrayList<String> pic;
    public ArrayList<NewsItemInfo> datalist=new ArrayList<>();
    private  NewsItemInfo newsItemInfo;
    private DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();


    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mAdapter=new MyListView();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLayout==null){
            mLayout=inflater.inflate(R.layout.fragment_main,container,false);
            mListView= (ListView) mLayout.findViewById(R.id.lv_main);
            initoption();
            mListView.setAdapter(mAdapter);
            new MyAsyn().execute(Constants.URL.FAVORITE_URL);
//        }else{
//            ViewGroup parent= (ViewGroup) mLayout.getParent();
//            parent.removeView(mLayout);
        }
        return mLayout;
    }



    class MyListView extends BaseAdapter{

        @Override
        public int getCount() {
            return datalist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View inflate;
            if (convertView==null){
                inflate=View.inflate(getActivity(), R.layout.list_item, null);
                holder=new ViewHolder();
                holder.tv_title = (TextView) inflate.findViewById(R.id.tv_title);
                holder.tv_summary = (TextView) inflate.findViewById(R.id.tv_summary);
                holder.tv_price = (TextView) inflate.findViewById(R.id.tv_price);
                holder.img = (ImageView) inflate.findViewById(R.id.imageView1);
                inflate.setTag(holder);
            }else {
                inflate=convertView;
                holder = (ViewHolder) inflate.getTag();
            }

            newsItemInfo = datalist.get(position);
            holder.tv_title.setText(newsItemInfo.title);
            holder.tv_summary.setText(newsItemInfo.str);
            holder.tv_price.setText(""+newsItemInfo.price);
            imageLoader.displayImage(newsItemInfo.pic.get(0), holder.img, options);

            return inflate;
        }
    }

    //使用内部类ViewHolder复用缓存对象，提高效率，优化界面
    static class ViewHolder {
        ImageView img;
        TextView tv_title;
        TextView tv_summary;
        TextView tv_price;
    }


    //访问网络
    class MyAsyn extends AsyncTask<String,Void,Void> implements VolleyLisener{

        @Override
        protected Void doInBackground(String... params) {
            HttpUtils.getVolley(params[0],this);
            return null;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(),"访问失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(String s) {
            parseJson(s);
            System.out.println(s);
        }

        @Override
        protected void onPostExecute(Void result) {
            mAdapter.notifyDataSetChanged();
            super.onPostExecute(result);
        }
    }

    private void parseJson(String s) {
        try {
            JSONObject json=new JSONObject(s);
            JSONArray jsonArray=json.getJSONArray("goodlist");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String product = jsonObject.getString("product");
                String short_title = jsonObject.getString("short_title");
                int price = jsonObject.getInt("price");
                int oldPrice = jsonObject.getInt("value");
                int bought = jsonObject.getInt("bought");

                JSONArray picArray = jsonObject.getJSONArray("images");
                pic=new ArrayList<>();
                for (int j=0; j<picArray.length();j++){
                    JSONObject jsonpic=picArray.getJSONObject(j);
                    String image = jsonpic.getString("image");
                    System.out.println(image);
                    pic.add(image);
                }
                NewsItemInfo Info = new NewsItemInfo(product, short_title, price, pic, oldPrice, bought);
                datalist.add(Info);
                System.out.println(Info.toString());
            }
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
				.displayer(new RoundedBitmapDisplayer(20))// 是否设置图片圆角
//				.displayer(new FadeInBitmapDisplayer(200))// 图片加载好之后渐入的动画时间
                .build();
    }
}
