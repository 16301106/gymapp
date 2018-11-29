package com.example.user.gymapp;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class VideoSource {

    public static String[][] mVideoUrls={{"http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/d68f9d1b40eca8b180b14be7de17eeb5.mp4",
            "http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/0936233b402beb908076688877c51dee.mp4",
            "http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/1971c5df4089e99180b8b51a28de8917.mp4",}};

    public static String[][] mVideoThumbs={{"http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/d464663340f36890808a8967b59e575f.jpg",
            "http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/86030405407ff75980797c2dfcc6f7d2.jpg",
            "http://bmob-cdn-8297.b0.upaiyun.com/2018/11/29/a1e618eb40587a8e80af75dac0bf406b.jpg",}};

    public static String[][] mVideoTitles={{"Twice-Yes or Yes",
    "林俊杰-黑夜问白天",
    "周杰伦-床边故事",}};

    public static void loadData() {
        BmobQuery<VideoAll> query = new BmobQuery<VideoAll>();

        query.findObjects(new FindListener<VideoAll>() {
            @Override
            public void done(List<VideoAll> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        VideoAll temp = list.get(i);
                        mVideoUrls[0][i] = temp.getVideo().getUrl();
                        mVideoThumbs[0][i] = temp.getCover().getUrl();
                        mVideoTitles[0][i] = temp.getTitle();
                    }

                } else {
                    System.out.println(e.getErrorCode());
                }
            }
        });
    }
}

