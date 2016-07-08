package com.qf.xs.day30multipanedemo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by asus on 2016/5/17.
 */
public class ContentFragment extends Fragment {

    private TextView contentText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_layout,null);

        contentText = (TextView) view.findViewById(R.id.content_text);


        //获得应该显示的文件的文件名，读取文件的内容，显示给contentText控件
        String fileName = getArguments().getString("fileName");

        //读文件内容
        AssetManager assetManager = getActivity().getAssets();
        try {
            //通过原生资源管理器打开文件流对象
            InputStream ips = assetManager.open("movies/" + fileName);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(ips));
            StringBuffer sbuf = new StringBuffer();
            while (true){
                String line = bfr.readLine();
                if (line == null)
                    break;
                sbuf.append(line+"\n");

            }

            contentText.setText(sbuf.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }



        return view;
    }
}
