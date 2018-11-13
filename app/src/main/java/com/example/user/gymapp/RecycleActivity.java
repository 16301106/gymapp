package com.example.user.gymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));

        List<Course> courseList=new ArrayList<>();
        courseList.add(new Course("swimming","James"));
        courseList.add(new Course("basketball","David"));
        courseList.add(new Course("running","John"));
        courseList.add(new Course("soccer","Mark"));
        courseList.add(new Course("tennis","Davis"));
        courseList.add(new Course("shooting","Ben"));
        recyclerView.setAdapter(new InfoBaseAdapter(this,courseList));
    }
}
