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
import portal.bachtiar.com.portalv12.model.m_NewsFeed;
import portal.bachtiar.com.portalv12.utils.AppController;

/**
 * Created by Bachtiar M Permadi on 08/11/2017.
 */

public class Adapter_NewsFeed extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<m_NewsFeed> feedData;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoaderFeed();
    public Adapter_NewsFeed(Activity activity, List<m_NewsFeed> feedData) {
        this.activity = activity;
        this.feedData = feedData;
    }

    @Override
    public int getCount() {
        return feedData.size();
    }

    @Override
    public Object getItem(int location) {
        return feedData.get(location);
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
            convertView = inflater.inflate(R.layout.list_row_newsfeed, null);
        }
        if (imageLoader == null) {
            imageLoader = AppController.getInstance().getImageLoader();
        }
        NetworkImageView authImg = (NetworkImageView) convertView.findViewById(R.id.AutImg);
        NetworkImageView feedImg = (NetworkImageView) convertView.findViewById(R.id.feedImg);
        TextView authName = (TextView) convertView.findViewById(R.id.feedAuthor);
        TextView feedContent = (TextView) convertView.findViewById(R.id.feedContent);
        TextView feedTime = (TextView) convertView.findViewById(R.id.feedTime);
        TextView cmtCount = (TextView) convertView.findViewById(R.id.cmtCount);



        m_NewsFeed data = feedData.get(position);


        authImg.setImageUrl(data.getImgUser(), imageLoader);
        feedImg.setImageUrl(data.getImgContent(), imageLoader);
        authName.setText(data.getName());
        feedContent.setText(data.getContent());
        feedTime.setText(data.getPosdate());
        cmtCount.setText(data.getCount());


        return convertView;

    }
}
