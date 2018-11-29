package com.example.user.gymapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        List<Course> courseList=new ArrayList<>();
        courseList.add(new Course("swimming","James","18833277319"));
        courseList.add(new Course("basketball","David","18833277319"));
        courseList.add(new Course("running","John","18833277319"));
        courseList.add(new Course("soccer","Mark","18833277319"));
        courseList.add(new Course("tennis","Davis","18833277319"));
        courseList.add(new Course("shooting","Ben","18833277319"));
        InfoBaseAdapter mAdapter=new InfoBaseAdapter(this,courseList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InfoBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                try{
                    TextView textView=(TextView) view.findViewById(R.id.PhoneNum);
                    String phone=textView.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                    startActivity(intent);
                }catch (SecurityException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
