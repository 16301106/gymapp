package com.example.user.gymapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class RecycleActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        final RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        if(!NetworkUtil.isNetWorkAvailable(this)){
            List<Course> courseList=new ArrayList<Course>();
            MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"Course.db",null,1);
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Cursor cursor=db.query("Course",null,null,null,null,null,null);

            if(cursor.moveToFirst()){
                do{
                    String name=cursor.getString(cursor.getColumnIndex("name"));
                    String couch=cursor.getString(cursor.getColumnIndex("couch"));
                    String phone=cursor.getString(cursor.getColumnIndex("phone"));
                    Course temp=new Course(name,couch,phone);
                    courseList.add(temp);
                }while (cursor.moveToNext());
            }

            recyclerView.setAdapter(new InfoBaseAdapter(this,courseList));
        }
        else {
            BmobQuery<Course> query = new BmobQuery<Course>();
            query.findObjects(new FindListener<Course>() {
                @Override
                public void done(List<Course> list, BmobException e) {
                    if (e == null) {
                        recyclerView.setAdapter(new InfoBaseAdapter(getBaseContext(), list));
                        saveInDatabase(list);
                    }
                }
            });
        }
    }

    private void saveInDatabase(List<Course> list){
        MyDatabaseHelper dbHelper=new MyDatabaseHelper(this,"Course.db",null,1);
        ContentValues values=new ContentValues();
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        for(int i=0;i<list.size();i++){
            values.put("name",list.get(i).getName());
            values.put("couch",list.get(i).getCouch());
            values.put("phone",list.get(i).getPhone());
            try {
                db.insert("Course",null,values);
            }catch (SQLiteException e){
                e.printStackTrace();
            }

            values.clear();
        }
    }
}
