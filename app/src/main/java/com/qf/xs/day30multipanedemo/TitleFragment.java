package com.qf.xs.day30multipanedemo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;

/**
 * Created by asus on 2016/5/17.
 * ListFragment 自带布局，布局中自带一个listview
 */
public class TitleFragment extends ListFragment {

    private String[] movieTitles;
    private String[] fileNames;//保存详情内容的文件的文件名

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //准备数据
        movieTitles = new String[]{"绣春刀","变形金刚4","后会无期","北京遇上西雅图"};
        //获得对应的文件名数组
        //得到原生资源管理器---用于管理assets目录下的资源
        AssetManager assets = getActivity().getAssets();
        try {
            //获得assets目录下的指定目录的所有文件
            fileNames = assets.list("movies");
            Log.e("print",fileNames.length+"...."+fileNames[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置listview的数据适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,movieTitles);
        setListAdapter(adapter);//找到默认提供的listview,设置数据适配器
    }

    //重写item的点击回调方法
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("fileName",fileNames[position]);
        //1.如果是横屏，添加一个内容Fragment，传递电影文件名
        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container,contentFragment).commit();

        }else {
            //2.如果是竖屏，跳转到一个内容的Activity.传递电影文件名
            Intent intent = new Intent(getActivity(), ContentActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }


    }
}
