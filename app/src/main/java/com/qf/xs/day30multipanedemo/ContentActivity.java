package com.qf.xs.day30multipanedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);

        ContentFragment contentFragment = new ContentFragment();
        contentFragment.setArguments(getIntent().getExtras());
       getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container2,contentFragment).commit();

    }
}
