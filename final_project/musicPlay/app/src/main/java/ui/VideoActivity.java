package ui;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import dialog.VideoAll;
import listAdapter.VideoListAdapter;

public class VideoActivity extends AppCompatActivity{
    private static final String TAG = "VideoActivity";
    private ListView listView;
    private SensorEventListener sensorEventListener;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        listView = (ListView) findViewById(R.id.list_view);
        BmobQuery<VideoAll> query=new BmobQuery<VideoAll>();
        query.findObjects(new FindListener<VideoAll>() {
            @Override
            public void done(List<VideoAll> list, BmobException e) {
                if(e==null){
                    listView.setAdapter(new VideoListAdapter(VideoActivity.this,list));
                }
            }
        });
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
