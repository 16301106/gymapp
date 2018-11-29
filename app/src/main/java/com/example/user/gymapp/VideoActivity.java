package com.example.user.gymapp;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class VideoActivity extends AppCompatActivity {
    private static final String TAG = "VideoActivity";
    private ListView listView;
    private VideoListAdapter adapter;
    private SensorEventListener sensorEventListener;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        listView = (ListView) findViewById(R.id.list_view);
        adapter=new VideoListAdapter(this);
        listView.setAdapter(adapter);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }

    @Override
    public void onBackPressed(){
        if(JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onResume(){
        super.onResume();
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        Log.e(TAG,"onResum");
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
        Log.e(TAG,"onPause");
    }
}
