package dialog;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class VideoAll extends BmobObject{
    private String title;
    private BmobFile video;
    private BmobFile cover;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BmobFile getVideo() {
        return video;
    }

    public void setVideo(BmobFile video) {
        this.video = video;
    }

    public BmobFile getCover() {
        return cover;
    }

    public void setCover(BmobFile cover) {
        this.cover = cover;
    }
}
