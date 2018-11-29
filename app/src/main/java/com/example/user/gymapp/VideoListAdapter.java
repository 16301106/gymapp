package com.example.user.gymapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;

import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoListAdapter extends BaseAdapter{
    int[] VideoIndex = {0,1,2};
    Context context;
    int mPager=-1;
    VideoSource videoSource;

    public VideoListAdapter(Context context){
        this.context=context;
        videoSource.loadData();
    }

    public VideoListAdapter(Context context,int pager){
        this.context=context;
        this.mPager=pager;
    }

    @Override
    public int getCount(){
        return mPager == -1? VideoIndex.length:3;
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(null==convertView){
            holder=new ViewHolder();
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.item_listview,null);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.jcVideoPlayerStandard=(JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
        if(mPager==-1){
            holder.jcVideoPlayerStandard.setUp(
                    videoSource.mVideoUrls[0][position],JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    videoSource.mVideoTitles[0][position]
            );
            Log.e("TAG","setUp"+position);
            Picasso.with(convertView.getContext())
                    .load(videoSource.mVideoThumbs[0][position])
                    .into(holder.jcVideoPlayerStandard.thumbImageView);
        }
        else {
            holder.jcVideoPlayerStandard.setUp(
                    videoSource.mVideoUrls[mPager][position],JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    videoSource.mVideoTitles[mPager][position]
            );
            Picasso.with(convertView.getContext())
                    .load(videoSource.mVideoThumbs[mPager][position])
                    .into(holder.jcVideoPlayerStandard.thumbImageView);
        }
        return convertView;
    }

    class ViewHolder{
         JCVideoPlayerStandard jcVideoPlayerStandard;
    }
}
