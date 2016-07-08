package com.qf.xs.day30multipanedemo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = new Bundle();
        bundle.putString("fileName","movie0.txt");
        //第一次进入应用，需要判断是否是横屏，如果是，加载第一部电影的信息到内容容器中
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_container,contentFragment).commit();

        }
    }
}
