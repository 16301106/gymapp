package listAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;
import dialog.VideoAll;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import ui.R;

public class VideoListAdapter extends BaseAdapter{
    private List<VideoAll> list;
    Context context;

    public VideoListAdapter(Context context, List<VideoAll> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.item_listview, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.jcVideoPlayerStandard = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);

        holder.jcVideoPlayerStandard.setUp(
                list.get(position).getVideo().getUrl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                list.get(position).getTitle()
        );
        Log.e("TAG", "setUp" + position);
        Picasso.with(convertView.getContext())
                .load(list.get(position).getCover().getUrl())
                .into(holder.jcVideoPlayerStandard.thumbImageView);


        return convertView;
    }

    class ViewHolder {
        JCVideoPlayerStandard jcVideoPlayerStandard;
    }
}
