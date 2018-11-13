package com.example.user.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.view.Menu;

public class ContentActivity extends AppCompatActivity {

    private TabHost tabHost;
    private RadioGroup radio;
    private int menuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        radio = (RadioGroup) findViewById(R.id.radiogroup);
        tabHost=(TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("main").setIndicator("main").setContent(R.id.fragment_main));
        tabHost.addTab(tabHost.newTabSpec("course").setIndicator("course").setContent(R.id.fragment_course));
        tabHost.addTab(tabHost.newTabSpec("search").setIndicator("search").setContent(R.id.fragment_search));
        tabHost.addTab(tabHost.newTabSpec("myinfo").setIndicator("myinfo").setContent(R.id.fragment_myinfo));
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                menuid=checkedId;
                int currentTab=tabHost.getCurrentTab();
                switch (checkedId){
                    case R.id.radio_main:
                        tabHost.setCurrentTabByTag("main");
                        getSupportActionBar().setTitle("首页");
                        break;
                    case R.id.radio_course:
                        tabHost.setCurrentTabByTag("course");
                        getSupportActionBar().setTitle("课程");
                        break;
                    case R.id.radio_search:
                        tabHost.setCurrentTabByTag("search");
                        getSupportActionBar().setTitle("发现");
                        break;
                    case R.id.radio_myinfo:
                        tabHost.setCurrentTabByTag("myinfo");
                        getSupportActionBar().setTitle("个人中心");
                }
                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
            }
        });
    }

}
