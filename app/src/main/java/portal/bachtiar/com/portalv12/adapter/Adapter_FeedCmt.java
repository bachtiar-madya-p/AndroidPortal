package portal.bachtiar.com.portalv12.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import portal.bachtiar.com.portalv12.R;
import portal.bachtiar.com.portalv12.model.m_FeedComment;
import portal.bachtiar.com.portalv12.utils.AppController;

/**
 * Created by bachtiar on 26/11/17.
 */

public class Adapter_FeedCmt extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<m_FeedComment> cmtData;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();

    public Adapter_FeedCmt(Activity activity, List<m_FeedComment> cmtData) {
        this.activity = activity;
        this.cmtData = cmtData;
    }

    @Override
    public int getCount() {
        return cmtData.size();
    }

    @Override
    public Object getItem(int location) {
        return cmtData.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.content_feedcomment, null);
        }
        if (imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }

        NetworkImageView cmtUserImg = (NetworkImageView) convertView.findViewById(R.id.cmtUserImg);
        TextView cmtUserName = (TextView) convertView.findViewById(R.id.cmtUserName);
        TextView cmtContent = (TextView) convertView.findViewById(R.id.cmtContent);
        TextView cmtPostDate = (TextView) convertView.findViewById(R.id.cmtPostDate);


        m_FeedComment data = cmtData.get(position);

        cmtUserImg.setImageUrl(data.getImgUser(), imageLoader);
        cmtUserName.setText(data.getName());
        cmtContent.setText(data.getCmtcontent());
        cmtPostDate.setText(data.getCmtposdate());

        return convertView;

    }
}
